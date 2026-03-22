#!/bin/bash
echo "Stopping all Java processes (Microservices)..."
# pkill -f java kills all java processes, similar to taskkill /IM java.exe
pkill -f java
echo "Done."