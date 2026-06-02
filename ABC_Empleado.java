package jdbc_ejemplo1;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ABC_Empleado extends FormularioPadreABC {
    public JTextField txtID, txtNombre, txtSalario, txtTelefono, txtIDRol, txtContrasena;

    public ABC_Empleado() {
        super("ABC Empleados");
        panelSuperior.setLayout(new GridLayout(6, 2));

        txtID = new JTextField(); txtNombre = new JTextField();
        txtSalario = new JTextField(); txtTelefono = new JTextField();
        txtIDRol = new JTextField(); txtContrasena = new JTextField();

        panelSuperior.add(new JLabel("IDEmpleado:")); panelSuperior.add(txtID);
        panelSuperior.add(new JLabel("Nombre:")); panelSuperior.add(txtNombre);
        panelSuperior.add(new JLabel("Salario:")); panelSuperior.add(txtSalario);
        panelSuperior.add(new JLabel("Telefono:")); panelSuperior.add(txtTelefono);
        panelSuperior.add(new JLabel("IDRol:")); panelSuperior.add(txtIDRol);
        panelSuperior.add(new JLabel("Contrasena:")); panelSuperior.add(txtContrasena);

        this.setSize(400, 250);
    }

    @Override
    public void agregar() {
        try {
            String sql = "INSERT INTO Empleado VALUES (" + txtID.getText() + ", '" + txtNombre.getText() + "', " + txtSalario.getText() + ", '" + txtTelefono.getText() + "', " + txtIDRol.getText() + ", '" + txtContrasena.getText() + "')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Empleado Agregado");
        } catch (SQLException ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }

    @Override
    public void modificar() {
        try {
            String sql = "UPDATE Empleado SET Nombre='" + txtNombre.getText() + "', Salario=" + txtSalario.getText() + ", Telefono='" + txtTelefono.getText() + "', IDRol=" + txtIDRol.getText() + ", Contrasena='" + txtContrasena.getText() + "' WHERE IDEmpleado=" + txtID.getText();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Empleado Modificado");
        } catch (SQLException ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }

    @Override
    public void borrar() {
        try {
            String sql = "DELETE FROM Empleado WHERE IDEmpleado=" + txtID.getText();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Empleado Borrado");
        } catch (SQLException ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }

    @Override
    public void consultar() {
        try {
            String sql = "SELECT * FROM Empleado WHERE IDEmpleado=" + txtID.getText();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                txtNombre.setText(rs.getString("Nombre"));
                txtSalario.setText(rs.getString("Salario"));
                txtTelefono.setText(rs.getString("Telefono"));
                txtIDRol.setText(rs.getString("IDRol"));
                txtContrasena.setText(rs.getString("Contrasena"));
                
   
                Empleado e = new Empleado(rs.getInt("IDEmpleado"), rs.getString("Nombre"), rs.getInt("Salario"), rs.getInt("IDRol"));
                JOptionPane.showMessageDialog(null, "Consultando a: " + e.nombre);
            }
        } catch (SQLException ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }
}
