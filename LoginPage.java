import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField usernameField; // Text field for username input
    private JPasswordField passwordField; // Password field for password input
    private static String username; // Static variable to store the logged-in username

    public LoginPage() {
        super("Pharmacy Management System Login");
        setFont(new Font("Arial", Font.PLAIN, 12));
        addGuiComponents(); // Add GUI components
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 650);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // Set JFrame visible after all components are added
    }

    private void addGuiComponents() {
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(SystemColor.activeCaption);
        loginPanel.setLayout(null);
        loginPanel.setBounds(0, 0, 836, 613);

        JLabel UsernameLabel = new JLabel("Username:");
        UsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        UsernameLabel.setBounds(209, 328, 87, 35);
        loginPanel.add(UsernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(306, 329, 231, 35);
        loginPanel.add(usernameField);

        JLabel PasswordLabel = new JLabel("Password:");
        PasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        PasswordLabel.setBounds(209, 404, 87, 35);
        loginPanel.add(PasswordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(306, 405, 231, 35);
        loginPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        loginButton.setBounds(319, 476, 179, 35);

     
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usernameText = usernameField.getText();
                char[] password = passwordField.getPassword();
                if (usernameText.isEmpty() || password.length == 0) {
                    JOptionPane.showMessageDialog(null, "Username and password cannot be empty");
                    passwordField.setText(""); // Clear password field for security
                } else if (usernameText.equals("Admin") && new String(password).equals("12345678")) {
                    username = usernameText;
                    dispose(); // Close the login window
                    MainPage mainPage = new MainPage(username);
                    mainPage.setVisible(true); // Open the main page
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                    passwordField.setText(""); // Clear password field after failed login
                }
            }
        });

        loginPanel.add(loginButton);

        getContentPane().setLayout(null);
        getContentPane().add(loginPanel);
        
        JLabel ImageLabel = new JLabel("");
        ImageLabel.setIcon(new ImageIcon("C:\\Users\\elmiz\\Downloads\\Logo.jpg"));
        ImageLabel.setBounds(342, 165, 122, 105);
        loginPanel.add(ImageLabel);
    }

    public static String getUsername() {
        return username;
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
