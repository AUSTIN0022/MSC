const mysql = require('mysql2');

const connection = mysql.createConnection({
    host: 'localhost',  
    user: 'root',       
    password: '@demo22',
});

connection.connect(err => {
    if (err) {
        console.error('Error connecting to MySQL:', err.message);
        return;
    }
    console.log('Connected to MySQL server.');
});

connection.query('CREATE DATABASE IF NOT EXISTS movie', (err, result) => {
    if (err) {
        console.error('Error creating database:', err.message);
        return;
    }
    console.log('Database "movie" is ready.');
});

connection.changeUser({ database: 'movie' }, (err) => {
    if (err) {
        console.error('Error switching to movie database:', err.message);
        return;
    }
});

const createTableQuery = `
    CREATE TABLE IF NOT EXISTS movies (
        id INT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(100) NOT NULL,
        director VARCHAR(100) NOT NULL,
        release_year INT NOT NULL,
        genre VARCHAR(50)
    )
`;

connection.query(createTableQuery, (err, result) => {
    if (err) {
        console.error('Error creating table:', err.message);
        return;
    }   
    console.log('Table "movies" is ready.');
});

connection.end();
