package jdbc_ejemplo1;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ABC_Ingredientes extends FormularioPadreABC {
    public JTextField txtID, txtNombre, txtCantidad, txtIDReceta, txtIDChef;

    public ABC_Ingredientes() {
        super("ABC Ingredientes");
        panelSuperior.setLayout(new GridLayout(5, 2, 5, 5));

        txtID = new JTextField(); txtNombre = new JTextField();
        txtCantidad = new JTextField(); txtIDReceta = new JTextField(); txtIDChef = new JTextField();

        panelSuperior.add(new JLabel("IDIngrediente:")); panelSuperior.add(txtID);
        panelSuperior.add(new JLabel("Nombre:")); panelSuperior.add(txtNombre);
        panelSuperior.add(new JLabel("Stock:")); panelSuperior.add(txtCantidad);
        panelSuperior.add(new JLabel("IDReceta:")); panelSuperior.add(txtIDReceta);
        panelSuperior.add(new JLabel("Chef Asignado:")); panelSuperior.add(txtIDChef);

        this.setSize(400, 250);
    }

    @Override
    public void agregar() {
        try {
            String sql = "INSERT INTO Ingredientes VALUES (" + txtID.getText() + ", '" + txtNombre.getText() + "', " + txtCantidad.getText() + ", " + txtIDReceta.getText() + ", " + txtIDChef.getText() + ")";
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Ingrediente Registrado");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }

    @Override
    public void modificar() {
        try {
            String sql = "UPDATE Ingredientes SET Nombre='" + txtNombre.getText() + "', Cantidad=" + txtCantidad.getText() + ", IDReceta=" + txtIDReceta.getText() + ", IDChef=" + txtIDChef.getText() + " WHERE IDIngrediente=" + txtID.getText();
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Ingrediente Modificado");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }

    @Override
    public void borrar() {
        try {
            String sql = "DELETE FROM Ingredientes WHERE IDIngrediente=" + txtID.getText();
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Ingrediente Eliminado");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }

    @Override
    public void consultar() {
        try {
            String sql = "SELECT * FROM Ingredientes WHERE IDIngrediente=" + txtID.getText();
            ResultSet rs = con.createStatement().executeQuery(sql);
            if (rs.next()) {
                txtNombre.setText(rs.getString("Nombre"));
                txtCantidad.setText(rs.getString("Cantidad"));
                txtIDReceta.setText(rs.getString("IDReceta"));
                txtIDChef.setText(rs.getString("IDChef"));
            } else { JOptionPane.showMessageDialog(null, "No encontrado"); }
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }
}
