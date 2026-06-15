# ATM Interface (Java)

## Description
A console-based ATM Interface built in Java as part of the **Oasis Infobyte Internship Program (OIBSIP)**. The application allows a user to log in with a User ID and PIN, then perform basic banking operations such as checking transaction history, withdrawing, depositing, and transferring money to another account.

## Features
- User authentication using User ID and PIN
- View transaction history
- Withdraw money (with balance check)
- Deposit money
- Transfer money to another account
- Quit option to exit the application

## Classes
- `ATMInterface.java`: Main class handling login and menu-driven operations.
- `Account.java`: Represents a bank account with balance, PIN, and transaction history.

## How to Run
1. Make sure Java JDK is installed.
2. Compile the program: `javac ATMInterface.java Account.java`
3. Run the program: `java ATMInterface`

## Test Accounts
- User ID: `user1`, PIN: `1234`, Balance: Rs. 5000
- User ID: `user2`, PIN: `5678`, Balance: Rs. 3000

## Technologies Used
- Java (Core Java, OOP concepts, java.util.Scanner, java.util.ArrayList)

## Author
Bhumika Srivastava