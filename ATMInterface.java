import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ATM Interface
 * -------------
 * A console-based ATM system that allows a user to log in with a
 * User ID and PIN, then perform basic banking operations:
 * 1. Transaction History
 * 2. Withdraw
 * 3. Deposit
 * 4. Transfer
 * 5. Quit
 *
 * Author: Bhumika Srivastava
 * Task: OIBSIP - ATM Interface
 */
public class ATMInterface {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pre-registered accounts (User ID, PIN, Balance)
        Account account1 = new Account("user1", "1234", 5000.0);
        Account account2 = new Account("user2", "5678", 3000.0);

        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);

        System.out.println("=========================================");
        System.out.println("           WELCOME TO JAVA ATM           ");
        System.out.println("=========================================");

        System.out.print("Enter User ID: ");
        String userId = scanner.next();

        System.out.print("Enter PIN: ");
        String pin = scanner.next();

        Account loggedInAccount = authenticate(accounts, userId, pin);

        if (loggedInAccount == null) {
            System.out.println("\nInvalid User ID or PIN. Exiting...");
            scanner.close();
            return;
        }

        System.out.println("\nLogin successful! Welcome, " + loggedInAccount.getUserId() + "!");

        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Choose an option (1-5): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.\n");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    loggedInAccount.printTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    loggedInAccount.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    loggedInAccount.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient User ID: ");
                    String recipientId = scanner.next();
                    Account recipient = findAccount(accounts, recipientId);

                    if (recipient == null) {
                        System.out.println("Recipient account not found.");
                    } else if (recipient == loggedInAccount) {
                        System.out.println("You cannot transfer money to your own account.");
                    } else {
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        loggedInAccount.transfer(recipient, transferAmount);
                    }
                    break;
                case 5:
                    System.out.println("\nThank you for using Java ATM. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 5.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static Account authenticate(List<Account> accounts, String userId, String pin) {
        for (Account acc : accounts) {
            if (acc.getUserId().equals(userId) && acc.checkPin(pin)) {
                return acc;
            }
        }
        return null;
    }

    private static Account findAccount(List<Account> accounts, String userId) {
        for (Account acc : accounts) {
            if (acc.getUserId().equals(userId)) {
                return acc;
            }
        }
        return null;
    }

    private static void printMenu() {
        System.out.println("-----------------------------------------");
        System.out.println("1. Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.println("-----------------------------------------");
    }
}