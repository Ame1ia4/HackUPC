#!/bin/bash

echo "🟢 Starting photocard-backend (port 8080)..."
cd Backend/photocard-backend
./mvnw spring-boot:run &

echo "🟢 Starting registration backend (port 8081)..."
cd ../../Registration
./mvnw spring-boot:run &

echo "✅ Both backends are now running!"