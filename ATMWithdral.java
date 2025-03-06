import java.util.Scanner;

class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class ATMWithdrawalSystem {
    private static final String CORRECT_PIN = "1234"; // Predefined correct PIN
    private static double balance = 3000; // Initial balance

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();

        try {
            // Check if the entered PIN is correct
            if (!enteredPin.equals(CORRECT_PIN)) {
                throw new InvalidPinException("Error: Invalid PIN.");
            }

            // Ask for withdrawal amount
            System.out.print("Withdraw Amount: ");
            double withdrawAmount = scanner.nextDouble();

            // Check if the balance is sufficient
            if (withdrawAmount > balance) {
                throw new InsufficientBalanceException("Error: Insufficient balance. Current Balance: " + balance);
            }

            // Process the withdrawal
            balance -= withdrawAmount;
            System.out.println("Withdrawal successful. Remaining Balance: " + balance);

        } catch (InvalidPinException e) {
            System.out.println(e.getMessage());
            System.out.println("Current Balance: " + balance);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Always display the remaining balance
            System.out.println("Final Balance: " + balance);
            // Close the scanner
            scanner.close();
        }
    }
}
