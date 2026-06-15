import java.util.ArrayList;
import java.util.List;

/**
 * Account
 * -------
 * Represents a single bank account with a User ID, PIN, balance,
 * and transaction history. Provides methods for deposit, withdraw,
 * transfer, and viewing transaction history.
 *
 * Author: Bhumika Srivastava
 * Task: OIBSIP - ATM Interface
 */
public class Account {

    private String userId;
    private String pin;
    private double balance;
    private List<String> transactionHistory;

    public Account(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public boolean checkPin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }
        balance += amount;
        transactionHistory.add("Deposited: Rs. " + amount);
        System.out.println("Deposit successful. New balance: Rs. " + balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance. Current balance: Rs. " + balance);
            return;
        }
        balance -= amount;
        transactionHistory.add("Withdrew: Rs. " + amount);
        System.out.println("Withdrawal successful. New balance: Rs. " + balance);
    }

    public void transfer(Account recipient, double amount) {
        if (amount <= 0) {
            System.out.println("Transfer amount must be greater than zero.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance. Current balance: Rs. " + balance);
            return;
        }
        balance -= amount;
        recipient.balance += amount;

        transactionHistory.add("Transferred: Rs. " + amount + " to " + recipient.getUserId());
        recipient.transactionHistory.add("Received: Rs. " + amount + " from " + this.getUserId());

        System.out.println("Transfer successful. New balance: Rs. " + balance);
    }

    public void printTransactionHistory() {
        System.out.println("\n----- Transaction History -----");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String entry : transactionHistory) {
                System.out.println(entry);
            }
        }
        System.out.println("Current Balance: Rs. " + balance);
        System.out.println("--------------------------------");
    }
}