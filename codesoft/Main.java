import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            JOptionPane.showMessageDialog(null, "Successfully deposited: $" + amount);
        } else {
            JOptionPane.showMessageDialog(null, "Deposit amount must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            JOptionPane.showMessageDialog(null, "Successfully withdrew: $" + amount);
        } else if (amount > balance) {
            JOptionPane.showMessageDialog(null, "Insufficient balance. Transaction failed.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Withdrawal amount must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
        showMenu();
    }

    public void showMenu() {
        JFrame frame = new JFrame("ATM Machine");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBounds(50, 50, 150, 30);
        panel.add(checkBalanceButton);

        JButton depositButton = new JButton("Deposit Money");
        depositButton.setBounds(50, 100, 150, 30);
        panel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw Money");
        withdrawButton.setBounds(50, 150, 150, 30);
        panel.add(withdrawButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(50, 200, 150, 30);
        panel.add(exitButton);

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Your current balance is: $" + account.getBalance());
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter the amount to deposit:");
                try {
                    double amount = Double.parseDouble(input);
                    account.deposit(amount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter the amount to withdraw:");
                try {
                    double amount = Double.parseDouble(input);
                    account.withdraw(amount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Thank you for using the ATM. Goodbye!");
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}

public class Main{
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(500.0); // Initial balance
        new ATM(userAccount);
    }
}
