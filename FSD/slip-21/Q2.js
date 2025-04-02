const express = require('express');
const path = require('path');
const fs = require('fs');

const app = express();

// Serve static files (for HTML)
app.use(express.static(__dirname));

// File download route with file existence check
app.get('/download/:filename', (req, res) => {
    const filename = req.params.filename;
    const filePath = path.join(__dirname, 'files', filename);

    fs.access(filePath, fs.constants.F_OK, (err) => {
        if (err) {
            console.error('File not found:', filename);
            return res.status(404).send('File not found.');
        }

        res.download(filePath, (err) => {
            if (err) {
                console.error('Error sending file:', err);
                res.status(500).send('Error downloading file.');
            }
        });
    });
});

app.listen(3000, () => {
    console.log(`Server running at http://localhost:3000`);
});
