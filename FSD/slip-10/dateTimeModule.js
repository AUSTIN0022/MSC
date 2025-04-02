// dateTimeModule.mjs - Custom module with ES6 exports

// Get full date and time
export function getDateTime() {
    return new Date().toLocaleString();
}

// Get only the date
export function getDate() {
    return new Date().toLocaleDateString();
}

// Get only the time
export function getTime() {
    return new Date().toLocaleTimeString();
}
