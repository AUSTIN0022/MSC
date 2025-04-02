const express = require('express');
const app = express();
const bcrypt = require("bcrypt");

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

const users = [];

app.get("/login", (req, res) => {
    res.sendFile(__dirname + "/login.html");
});

app.post("/login", async (req, res) => {
    const { email, password } = req.body;
    try {
        if (!email || !password) {
            return res.status(400).json({ message: "All fields are required" });
        }
        
        const user = users.find(user => user.email === email);
        if (!user) {
            return res.status(400).json({ message: "Invalid email or password" });
        }
        
        const isMatch = await bcrypt.compare(password, user.password);
        if (!isMatch) {
            return res.status(400).json({ message: "Invalid email or password" });
        }

        return res.json({ message: "Login successful" });

    } catch(err) {
        return res.status(500).json({
            message: "Error logging in",
            error: err.message
        });
    }
});

app.get("/register", (req, res) => {
    res.sendFile(__dirname + "/register.html");
});

app.post('/register', async (req, res) => {
    const { username, email, password } = req.body;
    try {
        if (!username || !email || !password) {
            return res.status(400).json({ message: "All fields are required" });
        }
        
        // email already exists
        if (users.some(user => user.email === email)) {
            return res.status(400).json({ message: "Email already exists" });
        }
        
        // username already exists
        if (users.some(user => user.username === username)) {
            return res.status(400).json({ message: "Username already exists" });
        }
        
        const hashedPassword = await bcrypt.hash(password, 10);
        
        const user = {
            username,
            email,
            password: hashedPassword,
        };
    
        users.push(user);
        
        return res.json({ message: `User ${username} registered successfully` });

    } catch(err) {
        return res.status(500).json({
            message: "Error registering user",
            error: err.message
        });
    }
});

const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});