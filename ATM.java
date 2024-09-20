import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class BankAccount {

    static void register() {
        Scanner sc = new Scanner(System.in);

        System.out.println("---------------------------");
        System.out.print("Enter your name: ");
        ATM.name = sc.nextLine();

        System.out.print("Enter username: ");
        String user = sc.nextLine();

        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        System.out.print("Enter your Account number: ");
        ATM.accNumber = sc.nextLine();

        System.out.println("REGISTRATION SUCCESSFUL!");
        System.out.println("---------------------------");

        ATM.prompt();

        while (true) {
            display(ATM.name);

            try {
                int choice = sc.nextInt();
                if (choice == 1) {
                    login(user, pass);
                    break;
                } else if (choice == 2) {
                    System.exit(0);
                } else {
                    System.out.println("Invalid value! Enter again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear invalid input
            }
        }
    }

    static void display(String name) {
        System.out.println("Welcome, " + name + "! Choose an option:");
        System.out.println("1. Login");
        System.out.println("2. Exit");
    }

    static void login(String user, String pass) {
        // Simple login logic for demonstration; can be expanded.
        System.out.println("Login successful!");
    }
}

class Transaction {

    static void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------");
        System.out.print("Enter amount to withdraw: ");
        
        try {
            int amount = sc.nextInt();
            if (amount <= ATM.balance && amount > 0) {
                ATM.balance -= amount;
                ATM.history.add(amount + " Withdraw");

                System.out.println("Amount Rs " + amount + "/- withdrawn successfully");
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid amount.");
            sc.next(); // Clear invalid input
        }
        System.out.println("---------------------------");

        ATM.prompt();
    }

    static void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------");
        System.out.print("Enter amount to deposit: ");
        
        try {
            int amount = sc.nextInt();
            if (amount > 0) {
                ATM.updateBalance(amount);
                ATM.history.add(amount + " Deposit");
                System.out.println("Amount Rs " + amount + "/- deposited successfully");
            } else {
                System.out.println("Invalid deposit amount.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid amount.");
            sc.next(); // Clear invalid input
        }
        System.out.println("---------------------------");

        ATM.prompt();
    }

    static void transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the receiving body: ");
        String receiver = sc.nextLine();

        System.out.print("Enter the account number of the receiving body: ");
        String accountNumber = sc.nextLine();

        System.out.print("Enter the amount to be transferred: ");
        
        try {
            int amount = sc.nextInt();
            if (amount <= ATM.balance && amount > 0) {
                ATM.balance -= amount;
                ATM.history.add(amount + " Transferred to " + receiver + " (Account: " + accountNumber + ")");

                System.out.println("Amount Rs " + amount + "/- transferred successfully");
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid amount.");
            sc.next(); // Clear invalid input
        }
        System.out.println("---------------------------");

        ATM.prompt();
    }
}

class Check {

    static void checkBalance() {
        System.out.println("------------------");
        System.out.println("The available balance in the bank account:");
        ATM.showBalance();
        System.out.println("---------------------------");

        ATM.prompt();
    }
}

class History {

    static void transactionHistory() {
        System.out.println("---------------------");
        System.out.println("Transaction History:");

        if (ATM.history.isEmpty()) {
            System.out.println("No transactions found");
        } else {
            for (String record : ATM.history) {
                System.out.println(record);
                System.out.println("---------------------");
            }
        }

        ATM.prompt();
    }
}

public class ATM {

    public static String name;
    public static int balance = 0;
    public static String accNumber;
    public static ArrayList<String> history = new ArrayList<>();

    static void updateBalance(int amount) {
        balance += amount;
    }

    static void showBalance() {
        System.out.println("Rs " + balance + "/-");
    }

    static void prompt() {
        Scanner sc = new Scanner(System.in);

        System.out.println("WELCOME " + name + "! TO ATM SYSTEM");
        System.out.println("---------------------");
        System.out.println("Select option:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Transfer");
        System.out.println("4. Check balance");
        System.out.println("5. Transaction History");
        System.out.println("6. Exit");

        System.out.print("Enter your choice: ");
        try {
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Transaction.withdraw();
                    break;
                case 2:
                    Transaction.deposit();
                    break;
                case 3:
                    Transaction.transfer();
                    break;
                case 4:
                    Check.checkBalance();
                    break;
                case 5:
                    History.transactionHistory();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    prompt();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.next(); // Clear invalid input
            prompt();
        }
    }

    public static void homepage() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\033[H\033[2J");
        System.out.println("WELCOME TO ATM INTERFACE");
        System.out.println("--------------------------");
        System.out.println("Select option:");
        System.out.println("1. Register");
        System.out.println("2. Exit");

        System.out.print("Enter choice: ");
        try {
            int choice = sc.nextInt();

            if (choice == 1) {
                BankAccount.register();
            } else if (choice == 2) {
                System.exit(0);
            } else {
                System.out.println("Invalid choice! Please select from the given options:");
                homepage();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.next(); // Clear invalid input
            homepage();
        }
    }

    public static void main(String[] args) {
        homepage();
    }
}