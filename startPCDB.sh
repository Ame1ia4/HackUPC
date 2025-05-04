#!/bin/bash

echo "🟢 Starting Registration backend (port 8081)..."
cd Backend/Registration
chmod +x mvnw
./mvnw spring-boot:run &

echo "🟢 Starting Photocard backend (port 8080)..."
cd ../photocard-backend
chmod +x mvnw
./mvnw spring-boot:run &

echo "🌐 Starting frontend on port 8000..."
cd ../../Frontend
python3 -m http.server 8000 &

echo ""
echo "✅ All systems running!"
echo "🔗 Photocard backend:     http://localhost:8080"
echo "🔗 Registration backend:  http://localhost:8081"
echo "🔗 Frontend:              http://localhost:8000"