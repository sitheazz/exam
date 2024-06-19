import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LockClass extends JFrame implements ActionListener {

    private JTextField passwordField;
    private JButton clearButton, enterButton;
    private String password;
    private boolean passwordSet = false;

    public LockClass() {
        super("Lock Class");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        // Create the title panel
        JPanel titlePanel = new JPanel(new FlowLayout());
        JLabel titleLabel = new JLabel("Lock Class");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        contentPane.add(titlePanel, BorderLayout.NORTH);

        // Create the keypad panel
        JPanel keypadPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(this);
            keypadPanel.add(button);
        }
        contentPane.add(keypadPanel, BorderLayout.CENTER);

        // Create the input panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        passwordField = new JTextField(10);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        enterButton = new JButton("Enter");
        enterButton.addActionListener(this);
        inputPanel.add(clearButton);
        inputPanel.add(passwordField);
        inputPanel.add(enterButton);
        contentPane.add(inputPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Clear")) {
                passwordField.setText("");
            } else if (button.getText().equals("Enter")) {
                if (!passwordSet) {
                    // Set password for the first time
                    password = passwordField.getText();
                    passwordSet = true;
                    JOptionPane.showMessageDialog(this, "Password Set!");
                } else {
                    // Verify password
                    String enteredPassword = passwordField.getText();
                    if (enteredPassword.equals(password)) {
                        JOptionPane.showMessageDialog(this, "Correct Password!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Incorrect Password!");
                    }
                }
                passwordField.setText("");
            } else {
                passwordField.setText(passwordField.getText() + button.getText());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LockClass());
    }
}