const http = require('http');
const fs = require('fs');
const path = require('path');
const querystring = require('querystring');

const server = http.createServer((req, res) => {
    if (req.method === 'GET') {
        // Serve the HTML form
        res.writeHead(200, { 'Content-Type': 'text/html' });
        res.end(fs.readFileSync(path.join(__dirname, 'index.html')));
    } else if (req.method === 'POST') {
        let body = '';

        req.on('data', chunk => {
            body += chunk.toString();
        });

        req.on('end', () => {
            const formData = querystring.parse(body);

            // Simple validation
            if (!formData.name || formData.name.length < 3) {
                res.writeHead(400, { 'Content-Type': 'text/html' });
                res.end('<h3>Name must be at least 3 characters</h3> <a href="/">Go Back</a>');
                return;
            }
            if (!formData.email || !formData.email.includes('@')) {
                res.writeHead(400, { 'Content-Type': 'text/html' });
                res.end('<h3>Invalid email</h3> <a href="/">Go Back</a>');
                return;
            }
            if (!formData.age || isNaN(formData.age) || formData.age < 18) {
                res.writeHead(400, { 'Content-Type': 'text/html' });
                res.end('<h3>Age must be 18 or above</h3> <a href="/">Go Back</a>');
                return;
            }

            // Store valid employee data
            const employeeData = `${formData.name}, ${formData.email}, ${formData.age}\n`;
            fs.appendFileSync('employees.txt', employeeData);

            res.writeHead(200, { 'Content-Type': 'text/html' });
            res.end('<h3>Employee Registered Successfully!</h3> <a href="/">Register Another</a>');
        });
    }
});

server.listen(3000, () => {
    console.log('Server running at http://localhost:3000');
});
