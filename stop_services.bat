@echo off
echo Stopping all Java processes (Microservices)...
taskkill /F /IM java.exe
echo Done.
pause