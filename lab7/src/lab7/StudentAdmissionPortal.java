package lab7;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentAdmissionPortal {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student";
    private static final String USER = "root";
    private static final String PASS = "livewire";

    // Main Method
    public static void main(String[] args) {
        // Create and configure the main frame
        JFrame frame = new JFrame("Student Admission Portal");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // UI Components
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JLabel titleLabel = new JLabel("STUDENT ADMISSION PORTAL", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        // Add components to the panel
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // Placeholder
        loginPanel.add(loginButton);

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(loginPanel, BorderLayout.CENTER);

        // Action Listener for Login Button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                login(username, password);
            }
        });

        // Show the frame
        frame.setVisible(true);
    }

    // Login Method
    private static void login(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Successful login, open admin page
                        openAdminPage();
                    } else {
                        // Invalid credentials, show error message
                        JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while processing your request", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Admin Page
    private static void openAdminPage() {
        // Implementation of Admin Page UI and functionalities
        JOptionPane.showMessageDialog(null, "Welcome Admin!", "Admin Page", JOptionPane.INFORMATION_MESSAGE);
    }
}
