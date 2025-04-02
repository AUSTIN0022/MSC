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

connection.query('CREATE DATABASE IF NOT EXISTS student', (err, result) => {
    if (err) {
        console.error('Error creating database:', err.message);
        return;
    }
    console.log('Database "student" is ready.');
});

connection.changeUser({ database: 'student' }, (err) => {
    if (err) {
        console.error('Error switching to student database:', err.message);
        return;
    }
});

const createTableQuery = `
    CREATE TABLE IF NOT EXISTS students (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        age INT NOT NULL,
        grade VARCHAR(10) NOT NULL
    )
`;

connection.query(createTableQuery, (err, result) => {
    if (err) {
        console.error('Error creating table:', err.message);
        return;
    }   
    console.log('Table "students" is ready.');
});

connection.end();
