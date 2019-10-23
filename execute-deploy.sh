#!/bin/bash
sh /home/ubuntu/MoHae-Server/travis/deploy.sh > /dev/null 2> /dev/null < /dev/null &
java -jar /home/ubuntu/MoHae-Server/travis/jar/MoHae-Server-0.0.1-SNAPSHOT.jar