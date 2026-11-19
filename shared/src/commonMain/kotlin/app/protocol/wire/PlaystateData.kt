package app.protocol.wire

import kotlinx.serialization.Serializable

/**
 * Playback state inside a `State` message.
 *
 * @property setBy Username of the user who initiated this state change. Server-set only.
 */
@Serializable
data class PlaystateData(
    val doSeek: Boolean? = null,
    val position: Double? = null,
    val setBy: String? = null,
    val paused: Boolean? = null,
    val speed: Double? = null,
    val speedEnabled: Boolean? = null,
    val speedRevision: Long? = null,
    val localSpeed: Double? = null
)
