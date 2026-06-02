package jdbc_ejemplo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CalculoSalario extends JFrame implements ActionListener {
    public Connection con;
    public JTextField txtID, txtVariable; 
    public JButton btnConsultar, btnCalcular;
    public JLabel lblNombre, lblResultado, lblInstruccion;
    
    public int idBuscado;
    public double salarioBase;
    public String nombreBuscado, telBuscado;

    public CalculoSalario() {
        super("Detalle de Salario Neto");
        setLayout(new GridLayout(5, 2, 5, 5));

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Restaurante;user=sa;password=jffp1234;encrypt=true;trustServerCertificate=true;");
        } catch (Exception e) {}

        txtID = new JTextField();
        btnConsultar = new JButton("Buscar Empleado");
        btnConsultar.addActionListener(this);

        lblNombre = new JLabel("-");
        lblInstruccion = new JLabel("Variable Extra:");
        txtVariable = new JTextField("0");
        txtVariable.setEnabled(false);
        
        btnCalcular = new JButton("Calcular Sueldo Neto");
        btnCalcular.addActionListener(this);
        lblResultado = new JLabel("$0.00");

        add(new JLabel(" ID Empleado:")); add(txtID);
        add(new JLabel("")); add(btnConsultar);
        add(new JLabel(" Nombre:")); add(lblNombre);
        add(lblInstruccion); add(txtVariable);
        add(btnCalcular); add(lblResultado);

        this.setSize(400, 250);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConsultar) {
            try {
                String sql = "SELECT * FROM Empleados WHERE IDEmpleado = " + txtID.getText();
                ResultSet rs = con.createStatement().executeQuery(sql);
                if (rs.next()) {
                    idBuscado = rs.getInt("IDEmpleado");
                    nombreBuscado = rs.getString("Nombre");
                    telBuscado = rs.getString("Telefono");
                    salarioBase = rs.getDouble("Salario");

                    lblNombre.setText(nombreBuscado);
                    txtVariable.setEnabled(true);
                    lblInstruccion.setText("Propinas/Clientes:");
                } else { JOptionPane.showMessageDialog(null, "No existe ese ID"); }
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
        }

    
        if (e.getSource() == btnConsultar) {
            try {
                String id = txtID.getText();
                String sql = "SELECT * FROM Empleados WHERE IDEmpleado = " + id;
                ResultSet rs = con.createStatement().executeQuery(sql);
                if (rs.next()) {
                    idBuscado = rs.getInt("IDEmpleado");
                    nombreBuscado = rs.getString("Nombre");
                    salarioBase = rs.getDouble("Salario");

                    lblNombre.setText(nombreBuscado);
                    txtVariable.setEnabled(true);
                    
                    Statement stmt = con.createStatement();
                   
                    if (stmt.executeQuery("SELECT * FROM Admin WHERE IDAdmin = " + id).next()) {
                        lblInstruccion.setText("Administrador (Base):");
                        txtVariable.setText("0");
                        txtVariable.setEnabled(false);
                    } else if (stmt.executeQuery("SELECT * FROM Chef WHERE IDChef = " + id).next()) {
                        lblInstruccion.setText("Chef (Base):");
                        txtVariable.setText("0");
                        txtVariable.setEnabled(false);
                    } else if (stmt.executeQuery("SELECT * FROM Host WHERE IDHost = " + id).next()) {
                        lblInstruccion.setText("Clientes del Mes:");
                    } else if (stmt.executeQuery("SELECT * FROM Mesero WHERE IDMesero = " + id).next()) {
                        lblInstruccion.setText("Total Propinas Día:");
                    }
                }
            } catch (Exception ex) {}
        }
    }
}
