import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JDialog {
    private JTextField tfEmail;
    private JTextField pfPassword;
    private JButton btnOK;
    private JButton btnCalncel;
    private JPanel loginPanel;


    public LoginForm(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(900,600));
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            btnOK.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String username = tfEmail.getText();
                    String password = new String(pfPassword.getText());


                    dispose();
                    new MenuForm();
                }
            });




//        btnOK.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                String username = tfEmail.getText();
//                String password;
//                password = new String(pfPassword.getpassword());
//
//
//
//                // Autenticar o usuário usando o nome de usuário e senha fornecidos
//                User user = getAuthenticatedUser(username, password);
//
//                if (user != null) {
//                    // Se as credenciais estiverem corretas, feche o diálogo de login
//                    dispose();
//
//                    // Abra o formulário do menu
//                    new MenuForm();
//                } else {
//                    // Se as credenciais estiverem incorretas, exiba uma mensagem de erro
//                    JOptionPane.showMessageDialog(LoginForm.this,
//                            "Email or Password Invalid",
//                            "Try again",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });

        btnCalncel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
    public User user;


    private User getAuthenticatedUser(String email, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost/mystore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


    // Código da classe LoginForm
// ...

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
        User user = loginForm.user;
        if (user != null) {
            System.out.println("Successful Authentication of: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Phone: " + user.getPhone()); // Corrigido para imprimir o telefone
            System.out.println("Address: " + user.getAddress());
        } else {
            System.out.println("Authentication canceled");
        }
    }



}

