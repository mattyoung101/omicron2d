package com.omicron.simulation2d

import com.github.robocup_atan.atan.model.ActionsPlayer
import com.github.robocup_atan.atan.model.ControllerPlayer
import com.github.robocup_atan.atan.model.enums.*
import org.pmw.tinylog.Logger
import java.util.HashMap

class GeneralAgent : ControllerPlayer {
    private var actionsPlayer: ActionsPlayer? = null
    private var type_ = "GeneralAgent"

    override fun infoPlayerParam(allowMultDefaultType: Double, dashPowerRateDeltaMax: Double, dashPowerRateDeltaMin: Double,
                                 effortMaxDeltaFactor: Double, effortMinDeltaFactor: Double, extraStaminaDeltaMax: Double,
                                 extraStaminaDeltaMin: Double, inertiaMomentDeltaFactor: Double, kickRandDeltaFactor: Double,
                                 kickableMarginDeltaMax: Double, kickableMarginDeltaMin: Double, newDashPowerRateDeltaMax: Double,
                                 newDashPowerRateDeltaMin: Double, newStaminaIncMaxDeltaFactor: Double, playerDecayDeltaMax: Double,
                                 playerDecayDeltaMin: Double, playerTypes: Double, ptMax: Double, randomSeed: Double,
                                 staminaIncMaxDeltaFactor: Double, subsMax: Double) {
        // not implemented
    }

    override fun infoHearPlayMode(playMode: PlayMode?) {// not implemented
    }

    override fun infoSeeBall(distance: Double, direction: Double, distChange: Double, dirChange: Double,
                             bodyFacingDirection: Double, headFacingDirection: Double) {
        // not implemented
    }

    override fun infoServerParam(info: HashMap<ServerParams, Any>?) {
        // not implemented
    }

    override fun infoHearWarning(warning: Warning) {
        Logger.info("Received warning: $warning")
    }

    override fun infoSeeFlagCenter(flag: Flag?, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                   bodyFacingDirection: Double, headFacingDirection: Double) {
        // not implemented
    }

    override fun infoSeePlayerOwn(number: Int, goalie: Boolean, distance: Double, direction: Double, distChange: Double,
                                  dirChange: Double, bodyFacingDirection: Double, headFacingDirection: Double) {
        // not implemented
    }

    override fun infoCPTOther(unum: Int) {
        // not implemented
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

    override fun infoHearOk(ok: Ok?) {
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

    override fun infoHearError(error: Errors) {
        Logger.error("Received error: $error")
    }

    override fun infoSeeLine(line: Line?, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                             bodyFacingDirection: Double, headFacingDirection: Double){
        // not implemented
    }

    override fun getType(): String {
        return type_
    }

    override fun setType(newType: String) {
        type_ = newType
    }

    override fun infoSeePlayerOther(number: Int, goalie: Boolean, distance: Double, direction: Double, distChange: Double,
                                    dirChange: Double, bodyFacingDirection: Double, headFacingDirection: Double) {
        // not implemented
    }

    override fun infoHearReferee(refereeMessage: RefereeMessage) {
        Logger.info("Received ref message: $refereeMessage")
    }

    override fun infoSeeFlagPenaltyOther(flag: Flag?, distance: Double, direction: Double,distChange: Double, dirChange: Double,
                                         bodyFacingDirection: Double, headFacingDirection: Double) {
        // not implemented
    }

    override fun infoSeeFlagGoalOther(flag: Flag?, distance: Double, direction: Double, distChange: Double, dirChange: Double,
                                      bodyFacingDirection: Double, headFacingDirection: Double) {
        // not implemented
    }

    override fun infoHearPlayer(direction: Double, message: String?) {
        // TODO decode message here (Kryo deserialisation)
    }

    override fun preInfo() {
        // not implemented
    }

    override fun postInfo() {
        // TODO parse and process inputs here
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

    override fun infoCPTOwn(unum: Int, type: Int) {
        // not implemented
    }

    override fun getPlayer(): ActionsPlayer? {
        return actionsPlayer
    }

    override fun setPlayer(c: ActionsPlayer?) {
        actionsPlayer = c
    }
}