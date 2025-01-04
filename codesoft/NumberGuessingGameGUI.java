import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class NumberGuessingGameGUI {


    private JFrame frame;
    private JTextField guessInput;
    private JButton guessButton, playAgainButton;
    private JLabel messageLabel, attemptsLabel, scoreLabel, instructionLabel;
    private int randomNumber, attemptsLeft, totalScore;
    private Random random;


    public NumberGuessingGameGUI() {
        random = new Random();
        totalScore = 0;
        setupUI();
        startNewGame();
    }


    private void setupUI() {
        frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());


        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(2, 1));


        instructionLabel = new JLabel("Guess the number between 1 and 100", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerPanel.add(instructionLabel);


        messageLabel = new JLabel("Welcome to the Number Guessing Game!", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        headerPanel.add(messageLabel);


        frame.add(headerPanel, BorderLayout.NORTH);


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1, 10, 10));


        attemptsLabel = new JLabel("", SwingConstants.CENTER);
        attemptsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        centerPanel.add(attemptsLabel);


        guessInput = new JTextField();
        guessInput.setFont(new Font("Arial", Font.PLAIN, 14));
        guessInput.setHorizontalAlignment(JTextField.CENTER);
        centerPanel.add(guessInput);


        guessButton = new JButton("Submit Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 14));
        centerPanel.add(guessButton);


        frame.add(centerPanel, BorderLayout.CENTER);


        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new GridLayout(2, 1, 5, 5));


        playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Arial", Font.BOLD, 14));
        playAgainButton.setEnabled(false);
        footerPanel.add(playAgainButton);


        scoreLabel = new JLabel("Total Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        footerPanel.add(scoreLabel);


        frame.add(footerPanel, BorderLayout.SOUTH);


        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });


        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });


        frame.setVisible(true);
    }


    private void startNewGame() {
        randomNumber = random.nextInt(100) + 1;
        attemptsLeft = 7;
        messageLabel.setText("A new number has been generated. Start guessing!");
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
        guessInput.setText("");
        guessInput.setEnabled(true);
        guessButton.setEnabled(true);
        playAgainButton.setEnabled(false);
        instructionLabel.setText("Guess the number between 1 and 100");
    }


    private void handleGuess() {
        String guessText = guessInput.getText();
        try {
            int guess = Integer.parseInt(guessText);
            attemptsLeft--;


            if (guess == randomNumber) {
                messageLabel.setText("Congratulations! You guessed the correct number.");
                totalScore += attemptsLeft + 1;
                endGame(true);
            } else if (guess < randomNumber) {
                messageLabel.setText("Too low! Try again.");
            } else {
                messageLabel.setText("Too high! Try again.");
            }


            if (attemptsLeft > 0) {
                attemptsLabel.setText("Attempts left: " + attemptsLeft);
            } else {
                messageLabel.setText("You've run out of attempts. The correct number was " + randomNumber + ".");
                endGame(false);
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number.");
        }
    }


    private void endGame(boolean won) {
        guessInput.setEnabled(false);
        guessButton.setEnabled(false);
        playAgainButton.setEnabled(true);
        if (won) {
            scoreLabel.setText("Total Score: " + totalScore);
            instructionLabel.setText("Well done! Play again to improve your score.");
        } else {
            instructionLabel.setText("Better luck next time! Try again.");
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGuessingGameGUI::new);
    }
}
