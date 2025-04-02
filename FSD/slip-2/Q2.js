const http = require('http');
const fs = require('fs');
const path = require('path');
const url = require('url');
const querystring = require('querystring');

const server = http.createServer((req, res) => {
  const parsedUrl = url.parse(req.url);
  const pathname = parsedUrl.pathname;

  if (req.url === '/') {
    res.writeHead(200, { 'Content-Type': 'text/html' });
    res.write(`
            <h1>Append File Contents</h1>
            <form action="/append" method="post">
                <label for="file1">File 1:</label>
                <input type="text" id="file1" name="file1" required><br><br>

                <label for="file2">File 2:</label>
                <input type="text" id="file2" name="file2" required><br><br>

                <button type="submit">Append</button>
            </form>
    `);
    res.end(); // Important: signal the end of the response
  } else if (req.url === '/append') {
      if (req.method === 'POST') {
            let body = '';
            req.on('data', chunk => {
                body += chunk;
            });

            req.on('end', () => {
                const formData = querystring.parse(body);
                const file1 = formData.file1;
                const file2 = formData.file2;


                if (!file1 || !file2) {
                    return res.end('Please provide both file names.');
                }

                const filePath1 = path.join(__dirname, file1);
                const filePath2 = path.join(__dirname, file2);

                if (!fs.existsSync(filePath1)) {
                    return res.end(`File ${file1} not found.`);
                }
                if (!fs.existsSync(filePath2)) {
                    return res.end(`File ${file2} not found.`);
                }


                fs.readFile(filePath1, 'utf8', (err, data1) => {
                    if (err) {
                        return res.end(`Error reading ${file1}: ${err.message}`);
                    }

                    fs.appendFile(filePath2, data1, 'utf8', (err) => {
                        if (err) {
                        return res.end(`Error appending to ${file2}: ${err.message}`);
                        }

                        res.writeHead(200, { 'Content-Type': 'text/html' });
                        res.end(`Contents of ${file1} appended to ${file2} successfully!`);
                    });
                });
            });
        }
  } else {
    res.writeHead(404, { 'Content-Type': 'text/html' });
    res.end('Not Found');
  }
});

server.listen(3000, () => {
  console.log("Server listening at http://localhost:3000");
});