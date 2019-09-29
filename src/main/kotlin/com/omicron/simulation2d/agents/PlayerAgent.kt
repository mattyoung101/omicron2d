package com.omicron.simulation2d.agents

import com.esotericsoftware.kryo.Kryo
import com.github.robocup_atan.atan.model.ActionsPlayer
import com.github.robocup_atan.atan.model.ControllerPlayer
import com.github.robocup_atan.atan.model.enums.*
import com.omicron.simulation2d.Message
import com.omicron.simulation2d.Messages
import com.omicron.simulation2d.PlayerRoles
import mikera.vectorz.Vector2
import org.tinylog.kotlin.Logger
import java.util.HashMap

/**
 * @param id the ID of the agent, 0-10
 */
class PlayerAgent(id: Int) : ControllerPlayer {
    private var actions: ActionsPlayer? = null
    private var agentType = "GeneralAgent"
    private val kryo = Kryo().apply {
        register(Array<Vector2>::class.java)
        register(Message::class.java)
        register(Messages::class.java)
    }
    private val role = PlayerRoles.values()[id]
//    private val encoder = MessageEncoder(kryo)

    ////////////////// GAMEPLAY RESPONSES //////////////////

    override fun preInfo() {
        // not implemented
    }

    override fun postInfo() {
        // TODO parse and process inputs here
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
                Logger.trace("BEFORE_KICK_OFF Positioning agent: " +
                        "team direction=${if (actions?.isTeamEast!!) "right" else "left"}, id=${actions?.number!!}, " +
                        "role=$role")
                // load starting formation and position agents, also select role based on ID
            }
            else -> {
                Logger.warn("Unregistered play mode detected: $playMode")
            }
        }
    }

    override fun infoHearReferee(refereeMessage: RefereeMessage) {
        Logger.info("Received ref message: $refereeMessage")
    }

    override fun infoHearPlayer(direction: Double, message: String?) {
        // in this step, we'll decode message here (Kryo deserialisation)
        // if an error occurs in deserialisation, ignore it because it was a malformed message from another team
    }

    override fun infoSeeBall(distance: Double, direction: Double, distChange: Double, dirChange: Double,
                             bodyFacingDirection: Double, headFacingDirection: Double) {
        // in this step, we will use our previous localisation data to calculate the absolute position of the ball
    }

    ////////////////// HEARING //////////////////

    override fun infoHearWarning(warning: Warning) {
        Logger.info("Received warning: $warning")
        // in this call we've done something wrong internally, seems to be mostly coach related so we should be right
    }

    override fun infoHearError(error: Errors) {
        Logger.error("Received error: $error")
    }

    override fun infoHearOk(ok: Ok) {
        // looks like the thing we tried to ask for earlier has been approved
    }


    ////////////////// SEEING (FOR LOCALISATION) //////////////////

    override fun infoSeeFlagCenter(flag: Flag?, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                   bodyFacingDirection: Double, headFacingDirection: Double) {
        // provide info to localisation algorithm
    }

    override fun infoSeePlayerOwn(number: Int, goalie: Boolean, distance: Double, direction: Double, distChange: Double,
                                  dirChange: Double, bodyFacingDirection: Double, headFacingDirection: Double) {
        // we see one of our teammates I assume, maybe useful for localisation?
        // definitely useful for movement planning, especially passing!! we should ask them for their orientation
    }

    override fun infoSeeFlagCornerOwn(flag: Flag?, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                      bodyFacingDirection: Double, headFacingDirection: Double) {
        // not implemented
    }

    override fun infoSeeFlagPenaltyOwn(flag: Flag?, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                       bodyFacingDirection: Double, headFacingDirection: Double) {
        // not implemented
    }

    override fun infoSeeFlagOwn(flag: Flag?, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                bodyFacingDirection: Double, headFacingDirection: Double){
        // not implemented
    }

    override fun infoPlayerType(id: Int, playerSpeedMax: Double, staminaIncMax: Double, playerDecay: Double, inertiaMoment: Double,
                                dashPowerRate: Double, playerSize: Double, kickableMargin: Double, kickRand: Double,
                                extraStamina: Double, effortMax: Double, effortMin: Double) {
        // not implemented
    }

    override fun infoSeeFlagRight(flag: Flag?, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                  bodyFacingDirection: Double, headFacingDirection: Double) {
        // not implemented
    }

    override fun infoSeeFlagLeft(flag: Flag?, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                 bodyFacingDirection: Double, headFacingDirection: Double) {
        // not implemented
    }

    override fun infoSeeFlagCornerOther(flag: Flag?, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                        bodyFacingDirection: Double, headFacingDirection: Double) {
        // not implemented
    }

    override fun infoSeeFlagOther(flag: Flag?, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                  bodyFacingDirection: Double, headFacingDirection: Double) {
        // not implemented
    }

    override fun infoSeeLine(line: Line?, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                             bodyFacingDirection: Double, headFacingDirection: Double){
        // not implemented
    }

    override fun infoSeePlayerOther(number: Int, goalie: Boolean, distance: Double, direction: Double, distChange: Double,
                                    dirChange: Double, bodyFacingDirection: Double, headFacingDirection: Double) {
        // not implemented
    }

    override fun infoSeeFlagPenaltyOther(flag: Flag?, distance: Double, direction: Double,distChange: Double, dirChange: Double,
                                         bodyFacingDirection: Double, headFacingDirection: Double) {
        // not implemented
    }

    override fun infoSeeFlagGoalOther(flag: Flag?, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                      bodyFacingDirection: Double, headFacingDirection: Double) {
        // not implemented
    }

    override fun infoSeeFlagGoalOwn(flag: Flag?, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                    bodyFacingDirection: Double, headFacingDirection: Double) {
        // not implemented
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