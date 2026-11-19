package app.protocol.sync

import app.protocol.WireMessage
import app.protocol.syncplayJson
import app.protocol.wire.PlaystateData
import app.protocol.wire.PlaybackSpeedData
import app.protocol.wire.SetData
import app.utils.ProtocolApi
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

@OptIn(ProtocolApi::class)
class RoomSpeedTest {
    @Test
    fun readsAuthoritativeRateAndLegacyLock() {
        assertEquals(RoomSpeed(1.5, true, 7), RoomSpeed.from(
            PlaystateData(speed = 1.5, speedEnabled = true, speedRevision = 7)
        ))
        assertEquals(RoomSpeed(1.0, false, 8), RoomSpeed.from(
            PlaystateData(speed = 1.5, speedEnabled = false, speedRevision = 8)
        ))
    }

    @Test
    fun ignoresAbsentAndInvalidRateSamples() {
        assertNull(RoomSpeed.from(PlaystateData(position = 20.0, paused = false)))
        assertNull(RoomSpeed.from(PlaystateData(speed = 1.5, speedEnabled = true)))
        assertNull(RoomSpeed.from(PlaystateData(speed = 5.0, speedEnabled = true, speedRevision = 1)))
    }

    @Test
    fun includesRevisionInTheSpeedRequest() {
        val request = WireMessage.Set(SetData(playbackSpeed = PlaybackSpeedData(25, 1.5)))
        assertEquals(
            "{\"Set\":{\"playbackSpeed\":{\"speedRevision\":25,\"speed\":1.5}}}",
            syncplayJson.encodeToString(request),
        )
    }
}
