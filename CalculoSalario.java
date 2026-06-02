package jdbc_ejemplo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CalculoSalario extends JFrame implements ActionListener {
    public Connection con;
    public JTextField txtID, txtVariable; 
    public JButton btnConsultar, btnCalcular;
    public JLabel lblNombre, lblRol, lblResultado, lblInstruccion;
    

    public int idBuscado, salarioBase, idRol;
    public String nombreBuscado;

    public CalculoSalario() {
        super("Detalle de Salarios por empleado");
        setLayout(new GridLayout(6, 2, 5, 5));


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Restaurante;user=sa;password=jffp1234;encrypt=true;trustServerCertificate=true;";
            con = DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }

        txtID = new JTextField();
        btnConsultar = new JButton("Buscar por ID");
        btnConsultar.addActionListener(this);

        lblNombre = new JLabel("-");
        lblRol = new JLabel("-");
        lblInstruccion = new JLabel("Dato extra requerido:");
        txtVariable = new JTextField("0");
        txtVariable.setEnabled(false); 
        
        btnCalcular = new JButton("Calcular Sueldo Neto");
        btnCalcular.addActionListener(this);
        lblResultado = new JLabel("$0.00");

        add(new JLabel(" ID Empleado:")); add(txtID);
        add(new JLabel(" Operación:")); add(btnConsultar);
        add(new JLabel(" Nombre Empleado:")); add(lblNombre);
        add(new JLabel(" Puesto en Sistema:")); add(lblRol);
        add(lblInstruccion); add(txtVariable);
        add(btnCalcular); add(lblResultado);

        this.setSize(420, 320);
        this.setLocationRelativeTo(null); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (e.getSource() == btnConsultar) {
            try {
                String sql = "SELECT * FROM Empleado WHERE IDEmpleado = " + txtID.getText();
                ResultSet rs = con.createStatement().executeQuery(sql);
                
                if (rs.next()) {
                    idBuscado = rs.getInt("IDEmpleado");
                    nombreBuscado = rs.getString("Nombre");
                    salarioBase = rs.getInt("Salario");
                    idRol = rs.getInt("IDRol");

                    lblNombre.setText(nombreBuscado);
                    txtVariable.setEnabled(false);
                    txtVariable.setText("0");

           
                    if (idRol == 1) {
                        lblRol.setText("Administrador");
                        lblInstruccion.setText("No requiere datos extras:");
                    }
                    if (idRol == 2) {
                        lblRol.setText("Chef");
                        lblInstruccion.setText("No requiere datos extras:");
                    }
                    if (idRol == 3) {
                        lblRol.setText("Host");
                        lblInstruccion.setText("Clientes registrados en el Mes:");
                        txtVariable.setEnabled(true); 
                    }
                    if (idRol == 4) {
                        lblRol.setText("Mesero");
                        lblInstruccion.setText("Total de propinas del día:");
                        txtVariable.setEnabled(true); 
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El ID de Empleado no existe.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.toString());
            }
        }

  
        if (e.getSource() == btnCalcular) {
            if (nombreBuscado == null) {
                JOptionPane.showMessageDialog(null, "Por favor busca un ID primero.");
                return;
            }

          
            if (idRol == 4) {
               
                double propinas = Double.parseDouble(txtVariable.getText());
                Mesero me = new Mesero(idBuscado, nombreBuscado, salarioBase, propinas, 3);
                lblResultado.setText("$" + me.calcularSueldo()); 
                
            } else if (idRol == 3) {
              
                int clientes = Integer.parseInt(txtVariable.getText());
                Host ho = new Host(idBuscado, nombreBuscado, salarioBase, clientes);
                lblResultado.setText("$" + ho.calcularSueldo()); 
                
            } else if (idRol == 2) {
               
                Chef ch = new Chef(idBuscado, nombreBuscado, salarioBase);
                lblResultado.setText("$" + ch.calcularSueldo()); 
                
            } else if (idRol == 1) {
               
                Admin ad = new Admin(idBuscado, nombreBuscado, salarioBase);
                lblResultado.setText("$" + ad.calcularSueldo()); 
            }
        }
    }
}
