# media-remote-control-server

This is a simple server written in Java, which lets you control volume and media playback on your Linux PC!
It works via a simple socket connection, so there's minimal overhead!

REQUIREMENTS:

- Linux with ALSA Drivers
- amixer installed to support volume control (get it with this command: "sudo apt-get install amixer")
- xdotool installed to support media control (get it with this command: "sudo apt-get install xdotool")

HOW TO:

- The server listens at port 4444 by default
- A different port can be passed as an argument
- There can always be only one client at a time to prevent confusions when controlling your computer

COMMANDS:

Send these commands as plain text to the socket (values without quotation marks):

"pp"    - Play/Pause - This invokes the system-wide play/pause functionality affecting whatever active media
"next"  - Play the next track/video
"prev"  - Play the previous track/video

"x%+"   - Increase Volume by x percent
"x%-"   - Decrease Volume by x percent
"x%"    - Set absolute volume to x
