const { exec } = require('child_process');

const isWindows = process.platform === 'win32';

console.log('Stopping all Java processes (Microservices)...');

const command = isWindows ? 'taskkill /F /IM java.exe' : 'pkill -f java';

exec(command, (error, stdout, stderr) => {
    // Both taskkill and pkill exit with a non-zero code if no process is found.
    // This is expected and not a "real" error in this context.
    if (error && error.code !== 0) {
        if ((isWindows && error.message.includes('not found')) || (!isWindows && error.code === 1)) {
            console.log('No running Java processes found to stop.');
        } else {
            console.error(`Error stopping processes: ${stderr || error.message}`);
            return;
        }
    }
    console.log('Done.');
});