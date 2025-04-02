import http from 'http';
import { getDate, getDateTime, getTime } from './dateTimeModule.js'; // Import functions

// Create an HTTP server
const server = http.createServer((req, res) => {
    res.writeHead(200, { 'Content-Type': 'text/html' });
    res.write(`<h1>Welcome to My Node.js Server</h1>`);
    res.write(`<p>Current Date and Time: ${getDateTime()}</p>`);
    res.write(`<p>Today's Date: ${getDate()}</p>`);
    res.write(`<p>Current Time: ${getTime()}</p>`);
    res.end();
});

// Start server on port 3000
server.listen(3000, () => {
    console.log('Server running at http://localhost:3000/');
});
