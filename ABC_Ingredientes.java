package jdbc_ejemplo1;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ABC_Ingredientes extends FormularioPadreABC {
    public JTextField txtID, txtNombre, txtTipo, txtIDReceta;

    public ABC_Ingredientes() {
        super("ABC Ingredientes");
        panelSuperior.setLayout(new GridLayout(4, 2));
        txtID = new JTextField(); txtNombre = new JTextField(); txtTipo = new JTextField(); txtIDReceta = new JTextField();
        panelSuperior.add(new JLabel("IDIngrediente:")); panelSuperior.add(txtID);
        panelSuperior.add(new JLabel("Nombre:")); panelSuperior.add(txtNombre);
        panelSuperior.add(new JLabel("Tipo:")); panelSuperior.add(txtTipo);
        panelSuperior.add(new JLabel("IDReceta:")); panelSuperior.add(txtIDReceta);
        this.setSize(400, 180);
    }

    @Override
    public void agregar() {
        try {
            String sql = "INSERT INTO Ingredientes VALUES (" + txtID.getText() + ", '" + txtNombre.getText() + "', '" + txtTipo.getText() + "', " + txtIDReceta.getText() + ")";
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Ingrediente Agregado");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error: " + ex.toString()); }
    }

    @Override
    public void modificar() {
        try {
            String sql = "UPDATE Ingredientes SET Nombre = '" + txtNombre.getText() + "', Tipo = '" + txtTipo.getText() + "', IDReceta = " + txtIDReceta.getText() + " WHERE IDIngrediente = " + txtID.getText();
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Ingrediente Modificado");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error: " + ex.toString()); }
    }

    @Override
    public void borrar() {
        try {
            String sql = "DELETE FROM Ingredientes WHERE IDIngrediente = " + txtID.getText();
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Ingrediente Eliminado");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error: " + ex.toString()); }
    }

    @Override
    public void consultar() {
        try {
            String sql = "SELECT * FROM Ingredientes WHERE IDIngrediente = " + txtID.getText();
            ResultSet rs = con.createStatement().executeQuery(sql);
            if (rs.next()) {
                txtNombre.setText(rs.getString("Nombre"));
                txtTipo.setText(rs.getString("Tipo"));
                txtIDReceta.setText(rs.getString("IDReceta"));
            } else { JOptionPane.showMessageDialog(null, "Ingrediente no encontrado."); }
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error: " + ex.toString()); }
    }
}
