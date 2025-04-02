const http = require('http');
const url = require('url');
const fs = require('fs');

const server = http.createServer((req, res) => {
    const q = url.parse(req.url, true);
    const filename = decodeURIComponent(q.pathname);

    fs.readFile(filename , (err, data) => {
        if (err) {
            console.error(err.message);
            res.writeHead(404, {'Content-Type': 'text/html'});
            return res.end("404 File not Found");
        }

        res.writeHead(200, {'Content-Type': 'text/html'});
        res.write(data);
        return res.end();
    })
});

server.listen(3000, () => {
    console.log('Server is running at http://localhost:3000....');
})