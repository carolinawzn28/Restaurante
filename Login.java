package jdbc_ejemplo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    public JTextField txtUser;
    public JPasswordField txtPass;
    public JButton btnEntrar;
    public Connection con;

    public Login() {
        super("Módulo de Login");
        setLayout(new GridLayout(3, 2, 5, 5));

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Restaurante;user=sa;password=jffp1234;encrypt=true;trustServerCertificate=true;");
        } catch (Exception e) {}

        txtUser = new JTextField();
        txtPass = new JPasswordField();
        btnEntrar = new JButton("Garantizar Acceso");
        btnEntrar.addActionListener(this);

        add(new JLabel(" ID de Empleado:")); add(txtUser);
        add(new JLabel(" Contraseña:")); add(txtPass);
        add(new JLabel("")); add(btnEntrar);

        this.setSize(350, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEntrar) {
            try {
                String id = txtUser.getText();
                String p = new String(txtPass.getPassword());
                
              
                String sqlBase = "SELECT * FROM Empleados WHERE IDEmpleado=" + id + " AND Telefono='" + p + "'";
                ResultSet rs = con.createStatement().executeQuery(sqlBase);
                
                if (rs.next()) {
                    String puestoDetectado = "Ninguno";
                    Statement stmt = con.createStatement();
                    
                    if (stmt.executeQuery("SELECT * FROM Admin WHERE IDAdmin = " + id).next()) {
                        puestoDetectado = "Admin";
                    } else if (stmt.executeQuery("SELECT * FROM Chef WHERE IDChef = " + id).next()) {
                        puestoDetectado = "Chef";
                    } else if (stmt.executeQuery("SELECT * FROM Host WHERE IDHost = " + id).next()) {
                        puestoDetectado = "Host";
                    } else if (stmt.executeQuery("SELECT * FROM Mesero WHERE IDMesero = " + id).next()) {
                        puestoDetectado = "Mesero";
                    }
                    
                    JOptionPane.showMessageDialog(null, "Acceso Autorizado. Puesto: " + puestoDetectado);
                    
                    MenuPrincipal menu = new MenuPrincipal(puestoDetectado);
                    menu.setVisible(true);
                    this.dispose();
                    
                } else {
                    JOptionPane.showMessageDialog(null, "ID de empleado o contraseña incorrectos.");
                }
            } catch (Exception ex) { 
                JOptionPane.showMessageDialog(null, "Error en Login: " + ex.toString()); 
            }
        }
    }
}
