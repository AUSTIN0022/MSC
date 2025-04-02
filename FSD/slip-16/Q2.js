const EventEmitter = require('events');
const myEmitter = new EventEmitter();

console.log('Starting event loop demonstration...');

// Set up event listener first
myEmitter.on('userAction', (phase, data) => {
    console.log(`Callback executed: ${phase} - ${data}`);
});

// Check phase (runs before timers)
setImmediate(() => {
    console.log('Check phase: Immediate execution');
    myEmitter.emit('userAction', 'Check phase', 'immediate callback');
});

// Timer phase (1 second)
setTimeout(() => {
    console.log('Timer phase: 1 second timer');
    myEmitter.emit('userAction', 'Timer phase', 'timer callback');
}, 1000);

// Interval (2 seconds)
const interval = setInterval(() => {
    myEmitter.emit('userAction', 'Timer phase', 'interval callback');
}, 2000);

// Cleanup
setTimeout(() => {
    clearInterval(interval);
    console.log('Demonstration complete');
    process.exit();
}, 5000);

console.log('Main program ended - Event loop running...');