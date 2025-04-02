const http = require('http');
const { IncomingForm } = require('formidable');
const fs = require('fs');

const server = http.createServer((req, res) => {
    if (req.method === 'GET') {
        res.writeHead(200, { 'Content-Type': 'text/html' });
        res.end(`
            <form action="/" method="post" enctype="multipart/form-data">
                <input type="file" name="file">
                <button type="submit">Upload</button>
            </form>
        `);
    } 
    else if (req.method === 'POST') {
        const form = new IncomingForm({
            uploadDir: './',
            keepExtensions: true,
        });

        form.parse(req, (err, fields, files) => {
            if (err) {
                res.end('Upload failed');
                return;
            }

            const file = files.file[0];
            fs.renameSync(file.filepath, `./${file.originalFilename}`);
            
            res.end(`
                <script>
                    alert("File uploaded");
                    window.location.href = "/";
                </script>
            `);
        });
    }
});

server.listen(3000, () => {
    console.log('Server running at http://localhost:3000');
});
