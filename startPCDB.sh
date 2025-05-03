#!/bin/bash

echo "🟢 Starting Spring Boot backend..."
cd Backend/photocard-backend
./mvnw spring-boot:run &

sleep 4

echo "🟢 Starting frontend server on http://localhost:8000"
cd ../../Frontend
python3 -m http.server