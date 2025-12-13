import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField tfUser;
    private JPasswordField pfKey;
    private JButton btnMasuk;

    public LoginFrame() {
        super("User Login");

        JPanel container = new JPanel(new GridLayout(3, 2, 10, 10));

        container.add(new JLabel("Username:"));
        tfUser = new JTextField();
        container.add(tfUser);

        container.add(new JLabel("Password:"));
        pfKey = new JPasswordField();
        container.add(pfKey);

        btnMasuk = new JButton("Login");
        container.add(btnMasuk);

        btnMasuk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputUser = tfUser.getText().trim();
                String inputPass = String.valueOf(pfKey.getPassword());

                boolean valid = "12345".equals(inputPass) && "admin".equalsIgnoreCase(inputUser);

                if (valid) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login Berhasil!");
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login Gagal.");
                }
            }
        });

        add(container);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}