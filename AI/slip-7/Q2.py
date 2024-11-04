import random


def chatbot():
    print("Welcome to the College Information Chatbot! Type 'exit' to end.")
    
    responses = {
        "hi": "Hello! How can I assist you?",
        "application deadline": "The application deadline is August 31.",
        "admission requirements": "Admission requirements include SSC, HSG 2 TY Marksheet.",
        "application status": "Application results will be available in September.",
        "scholarships": "Various scholarships, grants, and work-study options are available.",
        "bye": "Goodbye!"
    }
    
    default_responses = [
        "I'm not sure about that. Can you ask something else?",
        "I don't have information on that topic. Is there anything else I can help with?",
        "That's beyond my knowledge. Can I assist you with something else?"
    ]
    
    while True:
        user_input = input("You: ").lower().strip()
        
        if user_input == 'exit':
            print("Goodbye!")
            break
        elif user_input in responses:
            print(f"Bot: {responses[user_input]}")
        else:
            print(f"Bot: {random.choice(default_responses)}")

if __name__ == "__main__":
    chatbot()
