package io.github.omicron2d.agents

import com.esotericsoftware.kryo.Kryo
import com.github.robocup_atan.atan.model.ActionsPlayer
import com.github.robocup_atan.atan.model.ControllerPlayer
import com.github.robocup_atan.atan.model.enums.*
import com.google.common.base.CaseFormat
import io.github.omicron2d.AgentMessage
import io.github.omicron2d.AgentMessages
import io.github.omicron2d.PlayerRoles
import io.github.omicron2d.ai.Blackboard
import io.github.omicron2d.ai.ConnectionManager
import io.github.omicron2d.ai.FormationLoader
import io.github.omicron2d.ai.ParticleFilterLocaliser
import mikera.vectorz.Vector2
import org.tinylog.kotlin.Logger
import java.util.HashMap
import kotlin.math.roundToInt

/**
 * The main class of the agent in the simulation. This is is responsible for both the goalie and all normal agents
 * (the only difference between them is different planners).
 * @param agentId the ID of the agent, 0-10
 */
class PlayerAgent(private val agentId: Int) : ControllerPlayer {
    private var actions: ActionsPlayer? = null
    private var agentType = "PlayerAgent"
    // as no write access is done to Kryo we can keep one instance per agent thread as it's quite expensive
    private val kryo = Kryo().apply {
        register(Array<Vector2>::class.java)
        register(Vector2::class.java)
        register(AgentMessage::class.java)
        register(AgentMessages::class.java)
    }
    private val role = PlayerRoles.values()[agentId]
    private val connectionManager = ConnectionManager(kryo)
    // TODO load this only once in the whole application
    private val startingFormation = FormationLoader("formations/starting433.formation", kryo)
    /** last heard play mode from ref **/
    private var playMode = PlayMode.BEFORE_KICK_OFF
    private val localiser = ParticleFilterLocaliser()
    private val blackboard = Blackboard.localBlackboards.get()

    ////////////////// GAMEPLAY RESPONSES //////////////////
    override fun preInfo() {}

    override fun postInfo() {
        val newPos = localiser.updateLocalisation()
        blackboard.agentPos.set(newPos)
    }

    override fun infoPlayerParam(allowMultDefaultType: Double, dashPowerRateDeltaMax: Double, dashPowerRateDeltaMin: Double,
                                 effortMaxDeltaFactor: Double, effortMinDeltaFactor: Double, extraStaminaDeltaMax: Double,
                                 extraStaminaDeltaMin: Double, inertiaMomentDeltaFactor: Double, kickRandDeltaFactor: Double,
                                 kickableMarginDeltaMax: Double, kickableMarginDeltaMin: Double, newDashPowerRateDeltaMax: Double,
                                 newDashPowerRateDeltaMin: Double, newStaminaIncMaxDeltaFactor: Double, playerDecayDeltaMax: Double,
                                 playerDecayDeltaMin: Double, playerTypes: Double, ptMax: Double, randomSeed: Double,
                                 staminaIncMaxDeltaFactor: Double, subsMax: Double) {
        // not implemented
    }

    override fun infoHearPlayMode(playMode: PlayMode) {
        when (playMode) {
            PlayMode.BEFORE_KICK_OFF -> {
                Logger.trace("($agentId) BEFORE_KICK_OFF Positioning agent: " +
                        "team direction=${if (actions?.isTeamEast!!) "right" else "left"}, id=${actions?.number!!}, " +
                        "role=$role")
                val pos = startingFormation.getPosition(agentId)
                actions?.move(pos.x.roundToInt(), pos.y.roundToInt())
                localiser.setInitialEstimateLocation(pos)
            }

            PlayMode.KICK_OFF_OWN -> {
                // we won't use GOAP for this, maybe something simpler like a really basic FSM to move into position
                // and then kick, only if we're one of the striker players near the ball
                // also we gotta decide where to pick to
                // if we're one of the other striker guys we need to move into position ready to take the ball from
                // a pass
                // actually we could use GOAP and have it decide to plan to kick off?? and that could help pass
                Logger.trace("($agentId) KICK_OFF_OWN")
                this.playMode = PlayMode.KICK_OFF_OWN

                if (role == PlayerRoles.CENTRE_MID_CENTRE){
                    Logger.debug("Role is $role, navigating to ball")
                } else if (role in listOf(PlayerRoles.STRIKER, PlayerRoles.CENTRE_MID_LEFT, PlayerRoles.CENTRE_MID_RIGHT)){
                    Logger.debug("Role is $role, preparing to accept pass")
                }
            }

            PlayMode.PLAY_ON -> {
                // load up main planner here
            }

            else -> {
                Logger.warn("Unregistered play mode detected: $playMode")
            }
        }
    }

    ////////////////// HEARING //////////////////
    override fun infoHearReferee(refereeMessage: RefereeMessage) {
        Logger.info("Received ref message: $refereeMessage")
    }

    override fun infoHearPlayer(direction: Double, message: String) {
//        Logger.trace("Received player message: $message")
//        val conn = connectionManager.receive(message)
//        if (conn != null){
//            val msg = conn.received.last()
//            Logger.trace("Received valid message: $msg")
//        }
        // FIXME Atan's hear parser is bugged, we should fix this in our own version of it (atan2)
//        println("Heard message: $message in direction $direction")
    }

    override fun infoHearWarning(warning: Warning) {
        Logger.warn("Received warning: $warning")
        // in this call we've done something wrong internally, seems to be mostly coach related so we should be right
    }

    override fun infoHearError(error: Errors) {
        Logger.error("Received error: $error")
        // same as above mostly, not much we can do except save this for debugging
    }

    override fun infoHearOk(ok: Ok) {
        // looks like the thing we tried to ask for earlier has been approved - we don't ask for anything rn so no need
    }


    ////////////////// SEEING (FOR LOCALISATION) //////////////////
    // TODO for all of these, depending on the order they are called, we might an outdated player pos?
    // because player localisation is done in postInfo(), so we almost need a localisation queue which is done afterwards
    // idk see how inaccurate it is then decide if it's worth implementing the queue (not too hard just lazy)

    override fun infoSeeBall(distance: Double, direction: Double, distChange: Double, dirChange: Double,
                             bodyFacingDirection: Double, headFacingDirection: Double) {
        val seenBallPos = localiser.localiseObject(blackboard.agentPos, direction, distance)
        blackboard.ballPos.set(seenBallPos)
    }

    override fun infoSeePlayerOwn(number: Int, goalie: Boolean, distance: Double, direction: Double, distChange: Double,
                                  dirChange: Double, bodyFacingDirection: Double, headFacingDirection: Double) {
        // we see one of our teammates I assume, maybe useful for localisation?
        // definitely useful for movement planning, especially passing!! we should ask them for their orientation
        // TODO in this and infoSeePlayerOther() what does bodyFacingDirection and headFacingDirection do? can we use it?
//        val seenPlayerPos = localiser.localiseObject(blackboard.agentPos, direction, distance)
//        blackboard.teammatePositions[number].set(seenPlayerPos)
    }

    override fun infoSeePlayerOther(number: Int, goalie: Boolean, distance: Double, direction: Double, distChange: Double,
                                    dirChange: Double, bodyFacingDirection: Double, headFacingDirection: Double) {
        val seenPlayerPos = localiser.localiseObject(blackboard.agentPos, direction, distance)
        blackboard.opponentPositions[number].set(seenPlayerPos)
    }

    override fun infoSeeFlagCenter(flag: Flag, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                   bodyFacingDirection: Double, headFacingDirection: Double) {
        val name = "Centre" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, flag.name)
        localiser.updateLandmark(name, distance)
    }

    override fun infoSeeFlagCornerOwn(flag: Flag, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                      bodyFacingDirection: Double, headFacingDirection: Double) {
        val facingDir = if (actions!!.isTeamEast) "Left" else "Right"
        val name = "Corner$facingDir" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, flag.name)
        localiser.updateLandmark(name, distance)
    }

    override fun infoSeeFlagPenaltyOwn(flag: Flag, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                       bodyFacingDirection: Double, headFacingDirection: Double) {
        val name = "PenaltyOwn" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, flag.name)
        localiser.updateLandmark(name, distance)
    }

    override fun infoSeeFlagOwn(flag: Flag, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                bodyFacingDirection: Double, headFacingDirection: Double){
        val name = "Own" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, flag.name)
        localiser.updateLandmark(name, distance)
    }

    override fun infoPlayerType(id: Int, playerSpeedMax: Double, staminaIncMax: Double, playerDecay: Double, inertiaMoment: Double,
                                dashPowerRate: Double, playerSize: Double, kickableMargin: Double, kickRand: Double,
                                extraStamina: Double, effortMax: Double, effortMin: Double) {
        // not implemented
    }

    override fun infoSeeFlagRight(flag: Flag, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                  bodyFacingDirection: Double, headFacingDirection: Double) {
        val name = "Right" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, flag.name)
        localiser.updateLandmark(name, distance)
    }

    override fun infoSeeFlagLeft(flag: Flag, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                 bodyFacingDirection: Double, headFacingDirection: Double) {
        val name = "Left" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, flag.name)
        localiser.updateLandmark(name, distance)
    }

    override fun infoSeeFlagCornerOther(flag: Flag, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                        bodyFacingDirection: Double, headFacingDirection: Double) {
        val facingDir = if (actions!!.isTeamEast) "Right" else "Left" // invert since it's the other
        val name = "Corner$facingDir" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, flag.name)
        localiser.updateLandmark(name, distance)
    }

    override fun infoSeeFlagOther(flag: Flag, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                  bodyFacingDirection: Double, headFacingDirection: Double) {
        val name = "Other" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, flag.name)
        localiser.updateLandmark(name, distance)
    }

    override fun infoSeeLine(line: Line, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                             bodyFacingDirection: Double, headFacingDirection: Double){
        // should we give this to localisation?
    }

    override fun infoSeeFlagPenaltyOther(flag: Flag, distance: Double, direction: Double,distChange: Double, dirChange: Double,
                                         bodyFacingDirection: Double, headFacingDirection: Double) {
        val name = "PenaltyOther" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, flag.name)
        localiser.updateLandmark(name, distance)
    }

    override fun infoSeeFlagGoalOther(flag: Flag, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                      bodyFacingDirection: Double, headFacingDirection: Double) {
        val name = "GoalOther" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, flag.name)
        localiser.updateLandmark(name, distance)
    }

    override fun infoSeeFlagGoalOwn(flag: Flag, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                    bodyFacingDirection: Double, headFacingDirection: Double) {
        val name = "GoalOwn" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, flag.name)
        localiser.updateLandmark(name, distance)
    }

    override fun infoSenseBody(viewQuality: ViewQuality?, viewAngle: ViewAngle?, stamina: Double, unknown: Double, effort: Double,
                               speedAmount: Double, speedDirection: Double, headAngle: Double, kickCount: Int, dashCount: Int,
                               turnCount: Int, sayCount: Int, turnNeckCount: Int, catchCount: Int, moveCount: Int,
                               changeViewCount: Int) {
        // not implemented
    }

    ////////////////// USELESS/NOT IMPLEMENTED STUFF //////////////////
    override fun infoCPTOwn(unum: Int, type: Int) {
        // not implemented
    }
    override fun getPlayer(): ActionsPlayer? {
        return actions
    }
    override fun setPlayer(c: ActionsPlayer?) {
        actions = c
    }
    override fun getType(): String {
        return agentType
    }
    override fun setType(newType: String) {
        agentType = newType
    }
    override fun infoServerParam(info: HashMap<ServerParams, Any>?) {
        // no idea what this is called for?
    }
    override fun infoCPTOther(unum: Int) {
        // not implemented
    }
}