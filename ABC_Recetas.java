package jdbc_ejemplo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.FileWriter;

public class ABC_Recetas extends FormularioPadreABC {
    public JTextField txtID, txtNombre, txtDescripcion, txtIDChef, txtIDTipo;
    public JTextArea txtAreaIngredientes;
    public JButton btnAnadirReceta;

    public ABC_Recetas() {
        super("ABC Recetas");
        panelSuperior.setLayout(new GridLayout(6, 2, 5, 5));

        txtID = new JTextField(); txtNombre = new JTextField();
        txtDescripcion = new JTextField(); txtIDChef = new JTextField();
        txtIDTipo = new JTextField(); 

        txtAreaIngredientes = new JTextArea();
        txtAreaIngredientes.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtAreaIngredientes);

        btnAnadirReceta = new JButton("Añadir Receta a TXT");
        btnAnadirReceta.addActionListener(this);

        panelSuperior.add(new JLabel("IDReceta:")); panelSuperior.add(txtID);
        panelSuperior.add(new JLabel("Nombre Receta:")); panelSuperior.add(txtNombre);
        panelSuperior.add(new JLabel("Descripción:")); panelSuperior.add(txtDescripcion);
        panelSuperior.add(new JLabel("IDChef Responsable:")); panelSuperior.add(txtIDChef);
        panelSuperior.add(new JLabel("IDTipoReceta:")); panelSuperior.add(txtIDTipo);
        panelSuperior.add(btnAnadirReceta); panelSuperior.add(scroll);

        this.setSize(550, 480);
        this.validate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAnadirReceta) {
            try {
                String file = "Receta_" + txtNombre.getText().replace(" ", "_") + ".txt";
                FileWriter w = new FileWriter(file, true);
                w.write("ID: " + txtID.getText() + " | Nombre: " + txtNombre.getText() + "\n");
                w.write("Descripción: " + txtDescripcion.getText() + "\n");
                w.write("----------------------------------------\n");
                w.close();
                JOptionPane.showMessageDialog(null, "Guardado en " + file);
            } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
        } else {
            super.actionPerformed(e);
        }
    }

   
    public void verDetalleReceta(String extraInfo) {
        String baseInfo = "--- DETALLE DE LA RECETA ---\n" +
                          "ID: " + txtID.getText() + "\n" +
                          "Nombre: " + txtNombre.getText() + "\n" +
                          extraInfo;
        JOptionPane.showMessageDialog(null, baseInfo);
    }

    @Override
    public void agregar() {
        try {
            String sql = "INSERT INTO Recetas VALUES (" + txtID.getText() + ", '" + txtNombre.getText() + "', '" + txtDescripcion.getText() + "', " + txtIDChef.getText() + ", " + txtIDTipo.getText() + ")";
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Receta Agregada");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }

    @Override
    public void modificar() {
        try {
            String sql = "UPDATE Recetas SET Nombre='" + txtNombre.getText() + "', Descripcion='" + txtDescripcion.getText() + "', IDChef=" + txtIDChef.getText() + ", IDTipoReceta=" + txtIDTipo.getText() + " WHERE IDReceta=" + txtID.getText();
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Receta Modificada");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }

    @Override
    public void borrar() {
        try {
            String sql = "DELETE FROM Recetas WHERE IDReceta=" + txtID.getText();
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Receta Eliminada");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }

    @Override
    public void consultar() {
        try {
            String sql = "SELECT * FROM Recetas WHERE IDReceta=" + txtID.getText();
            ResultSet rs = con.createStatement().executeQuery(sql);
            if (rs.next()) {
                txtNombre.setText(rs.getString("Nombre"));
                txtDescripcion.setText(rs.getString("Descripcion"));
                txtIDChef.setText(rs.getString("IDChef"));
                txtIDTipo.setText(rs.getString("IDTipoReceta"));

                
                txtAreaIngredientes.setText("--- INGREDIENTES Y EXISTENCIAS ---\n");
                String sqlIng = "SELECT Nombre, Cantidad FROM Ingredientes WHERE IDReceta = " + txtID.getText();
                ResultSet rsIng = con.createStatement().executeQuery(sqlIng);
                while(rsIng.next()) {
                    String n = rsIng.getString("Nombre");
                    int cant = rsIng.getInt("Cantidad"); 
                    double costoSimulado = cant * 3.50; 
                    txtAreaIngredientes.append("- " + n + " | Stock: " + cant + " | Costo Unitario: $" + costoSimulado + "\n");
                }
            } else { JOptionPane.showMessageDialog(null, "No encontrado"); }
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }
}
