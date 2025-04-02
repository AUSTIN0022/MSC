const mysql = require('mysql2');

// MySQL Connection
const connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '@demo22',
    database: 'college' // Change if using a different database
});

connection.connect(err => {
    if (err) return console.error('MySQL Connection Error:', err.message);
    console.log('Connected to MySQL.');
});

// Query to select all teachers
const sql = 'SELECT * FROM Teacher WHERE salary > 20000';

connection.query(sql, (err, results) => {
    if (err) return console.error('Query Error:', err.message);

    console.log('Teachers with salary > 20,000:', results);
    connection.end();
});
