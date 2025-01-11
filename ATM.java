import java.util.Scanner;

public class ATM {
    private double balance;
    private String userID;
    private String userPIN;

    // Constructor to initialize user details
    public ATM(String userID, String userPIN, double initialBalance) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.balance = initialBalance;
    }

    // Method to authenticate the user
    public boolean authenticate(String enteredID, String enteredPIN) {
        return enteredID.equals(userID) && enteredPIN.equals(userPIN);
    }

    // Method to display the current balance
    public void displayBalance() {
        System.out.printf("Your current balance is: $%.2f%n", balance);
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited $%.2f%n", amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.printf("Successfully withdrew $%.2f%n", amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    // Main method
    public static void main(String[] args) {
        // Initialize an ATM object with sample data
        ATM myATM = new ATM("user123", "1234", 1000.00);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");

        // Authentication loop
        boolean authenticated = false;
        for (int attempts = 3; attempts > 0; attempts--) {
            System.out.print("Enter User ID: ");
            String enteredID = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String enteredPIN = scanner.nextLine();

            if (myATM.authenticate(enteredID, enteredPIN)) {
                authenticated = true;
                break;
            } else {
                System.out.printf("Incorrect ID or PIN. You have %d attempts left.%n", attempts - 1);
            }
        }

        if (!authenticated) {
            System.out.println("Too many failed attempts. Exiting...");
            return;
        }

        // Menu loop
        while (true) {
            System.out.println("\nATM Main Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    myATM.displayBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    myATM.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    myATM.withdraw(withdrawalAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
