@echo off
SET "PROJECT_ROOT=%~dp0"

echo ====================================================
echo Starting WebCom Microservices Environment
echo ====================================================

:: 1. Start Eureka Server
echo [1/3] Starting Eureka Server...
start "Eureka Server" cmd /k "cd /d %PROJECT_ROOT%eureka_server && mvnw spring-boot:run"

:: Wait for Eureka to initialize (25 seconds)
echo Waiting 25 seconds for Eureka to initialize...
timeout /t 25 /nobreak

:: 2. Start Core Services
echo [2/3] Starting Core Microservices...
start "Customer Service" cmd /k "cd /d %PROJECT_ROOT%customer_service && mvnw spring-boot:run"
start "Order Service"    cmd /k "cd /d %PROJECT_ROOT%order_service && mvnw spring-boot:run"
start "Payment Service"  cmd /k "cd /d %PROJECT_ROOT%payment_service && mvnw spring-boot:run"
start "Product Service"  cmd /k "cd /d %PROJECT_ROOT%product_service && mvnw spring-boot:run"
start "Shipping Service" cmd /k "cd /d %PROJECT_ROOT%shipping_service && mvnw spring-boot:run"

:: Wait for services to register with Eureka
echo Waiting 15 seconds for services to spin up...
timeout /t 15 /nobreak

:: 3. Start API Gateway
echo [3/3] Starting API Gateway...
start "API Gateway" cmd /k "cd /d %PROJECT_ROOT%api_gateway && mvnw spring-boot:run"

echo ====================================================
echo All services triggered. Check individual windows for logs.
echo ====================================================