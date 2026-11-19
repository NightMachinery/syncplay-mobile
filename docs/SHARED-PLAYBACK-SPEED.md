# Shared playback speed on Android

Install the full SyncPlayU APK from the [Android releases](https://github.com/NightMachinery/syncplay-mobile/releases/latest). It uses a separate application ID, so it can install alongside Synkplay. Room settings and media permissions need to be set up in the new app.

Choose ExoPlayer or mpv from the player picker and join a room on our server, `syncplay.pinky.lilf.ir:8999`, or another server running the [SyncPlayU Night 3 server source](https://github.com/NightMachinery/syncplay/blob/v1.7.7-night.3/docs/shared-playback-speed.md#server-upgrade). Open a video, then open **Control panel → Room playback speed**. Choose a rate from 0.25× to 4× and tap **Apply speed**. Changes from the desktop mpv controls also reach Android.

In a managed room, a room operator can change the rate. If a client joins without shared-speed support, the room returns to 1× until everyone supports it again. Other Android engines and upstream servers use ordinary playback synchronization.

The app opts in during Hello, sends a revisioned request only after a deliberate UI action, and applies the server's authoritative state. Room clocks use the shared rate, while local drift correction uses that rate as its baseline. A reconnect or room switch clears the last revision.
