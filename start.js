const { spawn } = require('child_process');
const path = require('path');

const projectRoot = __dirname;
const isWindows = process.platform === 'win32';

function runService(serviceName, directory) {
    console.log(`Starting ${serviceName}...`);
    const servicePath = path.join(projectRoot, directory);

    if (isWindows) {
        // On Windows, use `start` to open a new cmd window.
        const command = `cd /d ${servicePath} && .\\mvnw.cmd spring-boot:run`;
        spawn('cmd.exe', ['/c', 'start', `"${serviceName}"`, 'cmd.exe', '/k', command], {
            detached: true,
            stdio: 'ignore'
        }).unref();
    } else {
        // On macOS/Linux, use osascript to tell the Terminal app to run a script.
        const command = `cd '${servicePath}' && ./mvnw spring-boot:run`;
        const appleScript = `tell application "Terminal" to do script "${command.replace(/"/g, '\\"')}"`;
        spawn('osascript', ['-e', appleScript], {
            detached: true,
            stdio: 'ignore'
        }).unref();
    }
}

const sleep = (ms) => new Promise(resolve => setTimeout(resolve, ms));

async function startAll() {
    console.log('====================================================');
    console.log('Starting WebCom Microservices Environment');
    console.log('====================================================');

    console.log('[1/3] Starting Eureka Server...');
    runService('Eureka Server', 'eureka_server');

    console.log('Waiting 25 seconds for Eureka to initialize...');
    await sleep(25000);

    console.log('[2/3] Starting Core Microservices...');
    runService('Customer Service', 'customer_service');
    runService('Order Service', 'order_service');
    runService('Payment Service', 'payment_service');
    runService('Product Service', 'product_service');
    runService('Shipping Service', 'shipping_service');

    console.log('Waiting 15 seconds for services to spin up...');
    await sleep(15000);

    console.log('[3/3] Starting API Gateway...');
    runService('API Gateway', 'api_gateway');

    console.log('\nAll services triggered. Check individual windows for logs.');
}

startAll().catch(console.error);