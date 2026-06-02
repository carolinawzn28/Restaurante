package jdbc_ejemplo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.sql.*;
import java.io.FileWriter; 
import java.io.IOException;

public class ABC_Recetas extends FormularioPadreABC {
    public JTextField txtID, txtNombre, txtTipoPlatillo, txtPasos;
    public JTextArea txtAreaIngredientes; 
    public JButton btnAnadirTxt; 

    public ABC_Recetas() {
        super("ABC Recetas");
        
        
        panelSuperior.setLayout(new GridLayout(5, 2, 5, 5));
        
        txtID = new JTextField(); 
        txtNombre = new JTextField(); 
        txtTipoPlatillo = new JTextField();
        txtPasos = new JTextField(); 
        
        txtAreaIngredientes = new JTextArea();
        txtAreaIngredientes.setEditable(false); 
        JScrollPane scrollIngredientes = new JScrollPane(txtAreaIngredientes); 

    
        btnAnadirTxt = new JButton("Añadir Pasos a TXT");
        btnAnadirTxt.addActionListener(this); 


        panelSuperior.add(new JLabel("IDReceta:")); panelSuperior.add(txtID);
        panelSuperior.add(new JLabel("Nombre:")); panelSuperior.add(txtNombre);
        panelSuperior.add(new JLabel("Tipo platillo:")); panelSuperior.add(txtTipoPlatillo);
        panelSuperior.add(new JLabel("Pasos de la preparación:")); panelSuperior.add(txtPasos);
        panelSuperior.add(btnAnadirTxt); panelSuperior.add(scrollIngredientes);

 
        this.setSize(500, 450);
        this.validate();
        this.repaint();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
      
        if (e.getSource() == btnAnadirTxt) {
            if (txtID.getText().isEmpty() || txtNombre.getText().isEmpty() || txtPasos.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor llena el ID, Nombre y Pasos antes de guardar.");
                return;
            }
            
           
            try {
                String nombreArchivo = "Receta_" + txtNombre.getText().replace(" ", "_") + ".txt";
                FileWriter escritor = new FileWriter(nombreArchivo, true); 
                escritor.write("ID Receta: " + txtID.getText() + "\n");
                escritor.write("Nombre: " + txtNombre.getText() + "\n");
                escritor.write("Pasos de preparación:\n" + txtPasos.getText() + "\n");
                escritor.write("----------------------------------------\n");
                escritor.close();
                
                JOptionPane.showMessageDialog(null, "Pasos guardados en el archivo: " + nombreArchivo + "!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al generar el archivo TXT: " + ex.toString());
            }
        } else {
    
            super.actionPerformed(e);
        }
    }

    @Override
    public void agregar() {
        try {
            String sql = "INSERT INTO Recetas VALUES (" + txtID.getText() + ", '" + txtNombre.getText() + "', '" + txtTipoPlatillo.getText() + "')";
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Receta Agregada");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error: " + ex.toString()); }
    }

    @Override
    public void modificar() {
        try {
            String sql = "UPDATE Recetas SET Nombre = '" + txtNombre.getText() + "', TipoPlatillo = '" + txtTipoPlatillo.getText() + "' WHERE IDReceta = " + txtID.getText();
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Receta Modificada");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error: " + ex.toString()); }
    }

    @Override
    public void borrar() {
        try {
            String sql = "DELETE FROM Recetas WHERE IDReceta = " + txtID.getText();
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Receta Eliminada");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error: " + ex.toString()); }
    }

    @Override
    public void consultar() {
        try {
            
            String sqlReceta = "SELECT * FROM Recetas WHERE IDReceta = " + txtID.getText();
            ResultSet rsReceta = con.createStatement().executeQuery(sqlReceta);
            
            if (rsReceta.next()) {
                txtNombre.setText(rsReceta.getString("Nombre"));
                txtTipoPlatillo.setText(rsReceta.getString("TipoPlatillo"));
                
               
                txtAreaIngredientes.setText(""); 
                
                String sqlIngredientes = "SELECT Nombre, Tipo FROM Ingredientes WHERE IDReceta = " + txtID.getText();
                ResultSet rsIngredientes = con.createStatement().executeQuery(sqlIngredientes);
                
                txtAreaIngredientes.append("INGREDIENTES: ");
                boolean tieneIngredientes = false;
                
                while (rsIngredientes.next()) {
                    tieneIngredientes = true;
                    String nombreIng = rsIngredientes.getString("Nombre");
                    String tipoIng = rsIngredientes.getString("Tipo");
                    
                   
                    int stockSimulado = (nombreIng.length() * 5) + 12; 
                    
                    txtAreaIngredientes.append("- " + nombreIng + " (" + tipoIng + ") | Stock: " + stockSimulado + " uds.\n");
                }
                
                if (!tieneIngredientes) {
                    txtAreaIngredientes.append("No hay ingredientes vinculados a esta receta.\n");
                }
                
            } else { 
                JOptionPane.showMessageDialog(null, "Receta no encontrada."); 
                txtAreaIngredientes.setText("");
            }
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error al consultar: " + ex.toString()); }
    }
}
