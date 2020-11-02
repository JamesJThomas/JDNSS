@echo off
cd jdnss
cd src
cd main
cd java
cd dnsMessageBuilder
path=%path%;"C:\apache-maven-3.6.3-bin\apache-maven-3.6.3\bin"
path=%path%;"C:\astyle\AStyle\bin"
astyle --style=java *.java > c:\users\jjt91\dump.txt
rm -f *.orig > c:\users\jjt91\dump.txt
cd c:\users\jjt91\jdnss
mvn %1 > c:\users\jjt91\dump.txt
