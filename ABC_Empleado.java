package jdbc_ejemplo1;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ABC_Empleado extends FormularioPadreABC {
    public JTextField txtID, txtNombre, txtTelefono, txtSalario;
    public JComboBox<String> cmbRol; 

    public ABC_Empleado() {
        super("ABC Empleados");
       
        panelSuperior.setLayout(new GridLayout(5, 2, 5, 5));

        txtID = new JTextField(); 
        txtNombre = new JTextField();
        txtTelefono = new JTextField(); 
        txtSalario = new JTextField();
        
        
        String[] opcionesRoles = {"Admin", "Chef", "Host", "Mesero"};
        cmbRol = new JComboBox<>(opcionesRoles);

   
        panelSuperior.add(new JLabel("IDEmpleado:")); panelSuperior.add(txtID);
        panelSuperior.add(new JLabel("Nombre Empleado:")); panelSuperior.add(txtNombre);
        panelSuperior.add(new JLabel("Contraseña:")); panelSuperior.add(txtTelefono);
        panelSuperior.add(new JLabel("Salario Base:")); panelSuperior.add(txtSalario);
        panelSuperior.add(new JLabel("Puesto:")); panelSuperior.add(cmbRol);

        this.setSize(400, 260);
        this.validate();
        this.repaint();
    }

    @Override
    public void agregar() {
        try {
            String id = txtID.getText();
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();
            String salario = txtSalario.getText();
            
            Statement stmt = con.createStatement();

           
            String sqlPadre = "INSERT INTO Empleados VALUES (" + id + ", '" + nombre + "', '" + telefono + "', " + salario + ")";
            stmt.executeUpdate(sqlPadre);

            
            String rolSeleccionado = cmbRol.getSelectedItem().toString();
            String sqlHija = "";

            
            if (rolSeleccionado.equals("Admin")) {
                sqlHija = "INSERT INTO Admin (IDAdmin) VALUES (" + id + ")";
                
            } else if (rolSeleccionado.equals("Chef")) {
        
                sqlHija = "INSERT INTO Chef (IDChef, IDTipoReceta) VALUES (" + id + ", 1)";
                
            } else if (rolSeleccionado.equals("Host")) {
                sqlHija = "INSERT INTO Host (IDHost) VALUES (" + id + ")";
                
            } else if (rolSeleccionado.equals("Mesero")) {
                sqlHija = "INSERT INTO Mesero (IDMesero) VALUES (" + id + ")";
            }

       
            stmt.executeUpdate(sqlHija);
            
            JOptionPane.showMessageDialog(null, "¡Empleado registrado con éxito como: " + rolSeleccionado + "!");
            
        } catch (Exception ex) { 
            JOptionPane.showMessageDialog(null, "Error al guardar el empleado: " + ex.toString()); 
        }
    }

    @Override
    public void modificar() {
        try {
            String sql = "UPDATE Empleados SET Nombre='" + txtNombre.getText() + "', Telefono='" + txtTelefono.getText() + "', Salario=" + txtSalario.getText() + " WHERE IDEmpleado=" + txtID.getText();
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Datos generales del empleado actualizados.");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }

    @Override
    public void borrar() {
        try {
            String id = txtID.getText();
            Statement stmt = con.createStatement();
            
          
            stmt.executeUpdate("DELETE FROM Admin WHERE IDAdmin = " + id);
            stmt.executeUpdate("DELETE FROM Chef WHERE IDChef = " + id);
            stmt.executeUpdate("DELETE FROM Host WHERE IDHost = " + id);
            stmt.executeUpdate("DELETE FROM Mesero WHERE IDMesero = " + id);
            
          
            String sqlPadre = "DELETE FROM Empleados WHERE IDEmpleado = " + id;
            int rows = stmt.executeUpdate(sqlPadre);
            
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Empleado y sus roles eliminados del sistema.");
            } else {
                JOptionPane.showMessageDialog(null, "El ID no existe.");
            }
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error al borrar: " + ex.toString()); }
    }

    @Override
    public void consultar() {
        try {
            String id = txtID.getText();
            String sql = "SELECT * FROM Empleados WHERE IDEmpleado=" + id;
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            if (rs.next()) {
                txtNombre.setText(rs.getString("Nombre"));
                txtTelefono.setText(rs.getString("Telefono"));
                txtSalario.setText(rs.getString("Salario"));

                Statement stmt = con.createStatement();
                if (stmt.executeQuery("SELECT * FROM Admin WHERE IDAdmin = " + id).next()) {
                    cmbRol.setSelectedItem("Admin");
                } else if (stmt.executeQuery("SELECT * FROM Chef WHERE IDChef = " + id).next()) {
                    cmbRol.setSelectedItem("Chef");
                } else if (stmt.executeQuery("SELECT * FROM Host WHERE IDHost = " + id).next()) {
                    cmbRol.setSelectedItem("Host");
                } else if (stmt.executeQuery("SELECT * FROM Mesero WHERE IDMesero = " + id).next()) {
                    cmbRol.setSelectedItem("Mesero");
                }
            } else { 
                JOptionPane.showMessageDialog(null, "ID de Empleado no registrado."); 
            }
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }
}
