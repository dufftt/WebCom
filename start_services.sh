#!/bin/bash
# Get the absolute path of the script's directory
PROJECT_ROOT="$(cd "$(dirname "$0")" && pwd)"

echo "===================================================="
echo "Starting WebCom Microservices Environment (macOS)"
echo "===================================================="

# Function to open a new Terminal window and run the maven command
run_service() {
    local service_name=$1
    local dir_name=$2
    echo "Starting $service_name..."
    # This command tells the Terminal app to run the script in a new window
    osascript -e "tell application \"Terminal\" to do script \"cd '$PROJECT_ROOT/$dir_name' && ./mvnw spring-boot:run\""
}

# 1. Start Eureka Server
echo "[1/3] Starting Eureka Server..."
run_service "Eureka Server" "eureka_server"

# Wait for Eureka
echo "Waiting 25 seconds for Eureka to initialize..."
sleep 25

# 2. Start Core Services
echo "[2/3] Starting Core Microservices..."
run_service "Customer Service" "customer_service"
run_service "Order Service" "order_service"
run_service "Payment Service" "payment_service"
run_service "Product Service" "product_service"
run_service "Shipping Service" "shipping_service"

# Wait for services
echo "Waiting 15 seconds for services to spin up..."
sleep 15

# 3. Start API Gateway
echo "[3/3] Starting API Gateway..."
run_service "API Gateway" "api_gateway"

echo "===================================================="
echo "All services triggered."
echo "===================================================="