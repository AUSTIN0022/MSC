import math

from flask import Flask, render_template, request

app = Flask(__name__)

numbers = []

@app.route('/', methods=['GET', 'POST'])
def index():
    message = ""

    if request.method == 'POST':
        number = request.form.get('number')
        if 'add' in request.form and number:
            try:
                numbers.append(float(number))
                message = f"Number {number} added successfully."
            except ValueError:
                message = "Please enter a valid number."
        elif 'clear' in request.form:
            numbers.clear()
            message = "All numbers cleared."
    
    return render_template('index.html', numbers=numbers, message=message)



@app.route('/max', methods=['GET'])
def max_route():
    if numbers:
        max_num = max(numbers)  # Use a different variable name
        return f"The maximum number is: {max_num}"
    return "No numbers available."



@app.route('/sum', methods=['GET'])
def sum_route():
    total = sum(numbers) if numbers else 0
    return f"The sum of all numbers is: {total}"



@app.route('/average', methods=['GET'])
def average_route():
    if numbers:
        avg = sum(numbers) / len(numbers)  # Only divide if there are numbers
    else:
        avg = 0
    return f"The average of the numbers is: {avg}"



@app.route('/factorial', methods=['POST'])
def factorial_route():
    number = request.form.get('number')
    if number:
        try:
            result = math.factorial(int(number))
        except (ValueError, OverflowError):
            return "Invalid or too large number for factorial."
        return f"The factorial of {number} is: {result}"
    return "No number provided for factorial calculation."



if __name__ == '__main__':
    app.run(debug=True)
