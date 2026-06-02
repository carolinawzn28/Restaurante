package jdbc_ejemplo1;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ABC_Proveedores extends FormularioPadreABC {
    public JTextField txtID, txtNombre, txtTelefono;

    public ABC_Proveedores() {
        super("ABC Proveedores");
        panelSuperior.setLayout(new GridLayout(3, 2));
        txtID = new JTextField(); txtNombre = new JTextField(); txtTelefono = new JTextField();
        panelSuperior.add(new JLabel("IDProveedor:")); panelSuperior.add(txtID);
        panelSuperior.add(new JLabel("Nombre:")); panelSuperior.add(txtNombre);
        panelSuperior.add(new JLabel("Telefono:")); panelSuperior.add(txtTelefono);
        this.setSize(400,150 );
    }

    @Override
    public void agregar() {
        try {
            String sql = "INSERT INTO Proveedores VALUES (" + txtID.getText() + ", '" + txtNombre.getText() + "', '" + txtTelefono.getText() + "')";
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Proveedor Agregado");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error: " + ex.toString()); }
    }

    @Override
    public void modificar() {
        try {
            String sql = "UPDATE Proveedores SET Nombre = '" + txtNombre.getText() + "', Telefono = '" + txtTelefono.getText() + "' WHERE IDProveedor = " + txtID.getText();
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Proveedor Modificado");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error: " + ex.toString()); }
    }

    @Override
    public void borrar() {
        try {
            String sql = "DELETE FROM Proveedores WHERE IDProveedor = " + txtID.getText();
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Proveedor Eliminado");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error: " + ex.toString()); }
    }

    @Override
    public void consultar() {
        try {
            String sql = "SELECT * FROM Proveedores WHERE IDProveedor = " + txtID.getText();
            ResultSet rs = con.createStatement().executeQuery(sql);
            if (rs.next()) {
                txtNombre.setText(rs.getString("Nombre"));
                txtTelefono.setText(rs.getString("Telefono"));
            } else { JOptionPane.showMessageDialog(null, "Proveedor no encontrado."); }
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error: " + ex.toString()); }
    }
}
