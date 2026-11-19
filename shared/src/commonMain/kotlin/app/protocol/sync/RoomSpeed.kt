package app.protocol.sync

import app.protocol.wire.PlaystateData

internal val roomSpeedRange = 0.25..4.0

data class RoomSpeed(val rate: Double, val enabled: Boolean, val revision: Long) {
    companion object {
        internal fun from(state: PlaystateData?): RoomSpeed? {
            state ?: return null
            val rate = state.speed?.takeIf { it in roomSpeedRange } ?: return null
            val enabled = state.speedEnabled ?: return null
            val revision = state.speedRevision?.takeIf { it >= 0 } ?: return null
            return RoomSpeed(if (enabled) rate else 1.0, enabled, revision)
        }
    }
}
