package jdbc_ejemplo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    public JTextField txtUser;
    public JPasswordField txtPass;
    public JButton btnLogin;
    public Connection con;

    public Login() {
        super("Login");
        setLayout(new GridLayout(3, 2));
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Restaurante;user=sa;password=jffp1234;encrypt=true; trustServerCertificate=true;");
        } catch (Exception e) {}

        txtUser = new JTextField(); txtPass = new JPasswordField(); btnLogin = new JButton("Entrar");
        btnLogin.addActionListener(this);

        add(new JLabel("ID Empleado:")); add(txtUser);
        add(new JLabel("Contrasena:")); add(txtPass);
        add(new JLabel("")); add(btnLogin);
        this.setSize(300, 150);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String pass = new String(txtPass.getPassword());
            String sql = "SELECT IDRol FROM Empleado WHERE IDEmpleado=" + txtUser.getText() + " AND Contrasena='" + pass + "'";
            ResultSet rs = con.createStatement().executeQuery(sql);
            if (rs.next()) {
                new MenuPrincipal(rs.getInt("IDRol")).setVisible(true);
                this.dispose();
            } else { JOptionPane.showMessageDialog(null, "Error"); }
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }
}
