# This is a description page of ImPulse
On this page I would publish my thoughts about the overall architecture of this project.<br>
More importantly, this project is a ticket for a job in IT.

## Description
A spiritual successor to MiniDLNA. It's like MiniDLNA, but it lets you host your music globally and stream your songs between devices

## Backstory
Or simply "why audio streaming?"

Well, I like listening to music, and I mean I can spend days listening to different albums. From jazz to metal and to dubstep (don't ask me why). So firstly I did a local program using the C++/Qt/PortAudio stack (it actually has more than that, but that's not the case) which is called [PerfectAudioWorks](https://github.com/CapitanMurasa/PerfectAudioWorks.git). Why? Because I found no decent alternatives to Winamp, and Winamp itself (version 2.95 especially) cannot process FLAC files without an external plugin, which I spent decades trying to find. So I thought "if there's no alternative, create it by yourself" and here we have it (although it doesn't look like Winamp).
That gave me a lesson in processing and playing audio files, breaking my head resolving segfaults and C++ related problems with race conditions, etc. (Thanks to an AI I can debug it much faster). But what if I want to listen to my music collection outside my house? Of course I can drop files to an SD card and put it into my 2nd phone and listen there (or buy a used iPod). But syncing it is another problem: you always need to connect and drop files if you download a new album, which is annoying. So creating my streaming service would fix that problem. Simply, the ImPulse web application or another player like VLC or even my PerfectAudioWorks player (in the future) would reach the ImPulse server API and stream file(s) from S3 storage to your client! You can host it yourself, or maybe it would be hosted globally if this project would get fame (I doubt it).

## Tech stack that this app would use

**Java 21:**<br>
**why?:** if we're speaking about job opportunities. In Ukraine Java developers are in big demand (as in the rest of Europe), it's simply an industry standard in the enterprise IT market.
If we're speaking architecturally, it's actually a convenient language for backend work, and it's independent of the platform you're running it on (Linux, Windows, ARM, x86 or even RISC-V)

**Spring Boot:** <br>
**why?:** Simplified framework on top of Spring that doesn't require you to write tons of configuration files and, on top of that, to configure a web server manually and gather it.

**PostgreSQL:**<br>
**why?:** an industry standard, it's free, fully ACID so it ensures data stability and has many powerful plugins.

**FFmpeg** <br>
**why?:** It's a powerful tool that getting the hang of it would greatly help to optimize traffic and understand the encoding/decoding flow, if you dare to create some project like this.

**Redis:**<br>
**why?:** Imagine 50 users uploading files at the same time, or someone spamming the login page with guesses. Redis works as a traffic cop, so abuse can't eat the server's resources. It's planned as an optional component after the MVP, a single instance could use an in-memory counter, but Redis keeps counts shared across restarts and instances, and I also want to learn it.