# This is a description page of this project
In this page i would publish my thoughts about overall architecture of this project.<br>
More important this project is a ticket for a job in IT. Because it covers almost 80% what production standards require.
## Backstory
Or simply "why audio streaming?"

well i like listening to music, an i mean i can spend days listening to different albums. from jazz to metal and to dubstep (don't ask me why XD). So firsly i did local program using C++/Qt/Portaudio stack (it actually has more that that, but that's not the case) which is called [PerfectAudioWorks](https://github.com/CapitanMurasa/PerfectAudioWorks.git). Why? Because i found no decent alternatives to winamp, and winamp itself (version 2.95 espescially) can not process flac files without an external plugins which i spend decades to find one. So i've thought "if there's no alternative, create it by yourself" and here we have it (altough it doesn't look like an winamp).
That gave me lesson in processing and playing audio files, breaking my head resolving segfaults and C++ related problems with race conditions and etc (Thanks to an AI i can debug it much faster). But what if i want to listen to my music collection outside my house? Of course i can drop files to a sd card and put into my 2nd phone and listen there (or buy a used ipod). But syncing it is another problem, you always need to connect and drop files if you download new album, which is annoying. So creating my streaming service would fix that problem. Simply Ampere web application or another player like VLC or even my PerfetctAudioWorks player (in the future) would reach ampere server API and stream file(s) from S3 storage to your client! You can host it by self or maybe it would be hosted globally if this projects would get a fame (I doubt it).

## Tech stack that this app would use

**Java 21:**<br>
**why?:** if we're speaking about job opportunity.In Ukraine java developers are in big demand (as in rest of the europe), it's simply an industrial standard in enterprise IT market.
if we're speaking architectually. It's actually a convenient language for a backend work, it's independent to platform that you're running in (linux, windows, arm, x86 or even RISC-V) 

**Spring Boot:** <br>
**why?:** Simplified version of Spring (which is #1 in popularity backend framework for java) that doesn't require to write tons of configuration files and on top of that configure manually web server, and gather it.

**Postgresql:**<br>
**why?:** an industry standard, it's free, fully ACID so it ensures data stability and has many powerfull plugins.

**Data storage (S3) MinIO**<br>
S3 compatible object storage system that i can self-host. Perfect for me because i'm simply poor and can't afford amazon's S3 storage. And it also allows me to "toy" with it.

**FFmpeg** <br>
**why?:** It's a powerful tool that getting hang of it would greatly help to optimize traffic and understand encoding/decoding flow, if you dare to create some project like this.

**Redis:**<br>
**why?:** Imagine 50 users uploading files to S3 storage simultaneously. It would transcode a file for one user but other users would wait very long, causing request timed out (408).
So redis fixes that problem allowing to hold jobs so there wouldn't be a timeout problem.