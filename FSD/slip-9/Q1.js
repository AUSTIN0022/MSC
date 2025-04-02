
const http = require('http');
const url = require('url');

const server = http.createServer((req, res) => {
    if (req.method === "GET" && req.url === "/") {
        res.writeHead(200, { "Content-Type": "text/html" });

        res.write(`<h1>Concatenate Two Strings</h1>
                    <form action='/submit' method='GET'> 
                        String 1:<input type='text' name='string1'/>
                        String 2:<input type='text' name='string2'/>
                        <button type='submit'>Submit</button>
                    </form>
        `);
        res.end();
    } else if (req.method === "GET" && req.url.startsWith("/submit")) {
        const query =  url.parse(req.url, true).query;

        const string1 = query.string1 || "";
        const string2 = query.string2 || "";
        const concatenatedString = string1 + " " + string2; 

        res.writeHead(200, { "Content-Type": "text/html" });
        res.write(`<h1>Concatenated Result</h1>
                    <h2> ${concatenatedString}</h2>
                    <a href='/'>Go Back</a>
        `);
        res.end();
    } else {
        res.writeHead(404, { "Content-Type": "text/html" });
        res.write("<h1>404 Not Found</h1>");
        res.end();
    }
});
server.listen(3000, () => {
    console.log("Server is running at http://localhost:3000");
});