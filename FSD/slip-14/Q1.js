//  Create a Simple Web Server using node js (slip-8,14)
const http = require('http');

const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader('Content-Type', 'text/html'); 

  // Different ways to handle requests based on URL:
  if (req.url === '/') {
    res.end(`<h1>Hello, World! This is the home page </h1>`);
  } else if (req.url === '/about') {
    res.end(`<h1>This is the about page. </h1>`);
  } else if (req.url === '/contact') {
    res.end(`<h1>Contact us at example@email.com</h2>`);
  } else {
    res.statusCode = 404; // Not Found
    res.end('Page Not Found');
  }
});

server.listen(3000, () => {
  console.log('Server running at http://127.0.0.1:3000/....');
});
