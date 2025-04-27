import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypingSpeedTestGUI {
    private JFrame frame;
    private JTextArea textArea;
    private JTextField inputField;
    private JLabel wpmLabel, accuracyLabel, timeLabel;
    private JButton startButton;
    private String sentence = "The quick brown fox jumps over the lazy dog";
    private long startTime;
    
    public TypingSpeedTestGUI() {
        frame = new JFrame("Typing Speed Test");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        JPanel topPanel = new JPanel();
        textArea = new JTextArea(sentence);
        textArea.setFont(new Font("Arial", Font.BOLD, 16));
        textArea.setEditable(false);
        topPanel.add(textArea);
        
        inputField = new JTextField(40);
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputField.setEnabled(false);
        
        startButton = new JButton("Start Typing");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTest();
            }
        });
        
        JPanel centerPanel = new JPanel();
        centerPanel.add(inputField);
        
        JPanel bottomPanel = new JPanel();
        wpmLabel = new JLabel("WPM: ");
        accuracyLabel = new JLabel("Accuracy: ");
        timeLabel = new JLabel("Time: ");
        
        bottomPanel.add(wpmLabel);
        bottomPanel.add(accuracyLabel);
        bottomPanel.add(timeLabel);
        
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(startButton, BorderLayout.WEST);
        
        frame.setVisible(true);
    }
    
    private void startTest() {
        inputField.setEnabled(true);
        inputField.setText("");
        startButton.setEnabled(false);
        startTime = System.currentTimeMillis();
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endTest();
            }
        });
    }
    
    private void endTest() {
        long endTime = System.currentTimeMillis();
        double elapsedTime = (endTime - startTime) / 1000.0;
        
        String typedText = inputField.getText();
        double wordCount = sentence.split(" ").length;
        double wpm = (wordCount / elapsedTime) * 60;
        
        double accuracy = calculateAccuracy(sentence, typedText);
        
        wpmLabel.setText("WPM: " + String.format("%.2f", wpm));
        accuracyLabel.setText("Accuracy: " + String.format("%.2f", accuracy) + "%");
        timeLabel.setText("Time: " + String.format("%.2f", elapsedTime) + " seconds");
        
        inputField.setEnabled(false);
        startButton.setEnabled(true);
    }
    
    private double calculateAccuracy(String original, String typed) {
        int correctChars = 0;
        int minLength = Math.min(original.length(), typed.length());
        
        for (int i = 0; i < minLength; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                correctChars++;
            }
        }
        
        return ((double) correctChars / original.length()) * 100;
    }
    
    public static void main(String[] args) {
        new TypingSpeedTestGUI();
    }
}
