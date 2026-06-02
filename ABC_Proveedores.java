package jdbc_ejemplo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ABC_Proveedores extends FormularioPadreABC {
 
   
    public JTextField txtID, txtNombre, txtTelefono, txtIDAdmin;
    public JComboBox<String> cmbCategoriaProveedor; 
    
   
    public JButton btnSolicitarPed, btnCalcularTot, btnEjecutarSolicitud; 
    public JComboBox<String> cmbTipoProductoSimulado;
    public JTextField txtCantidadSolicitada, txtMensajeEstado;
    public JLabel lblResultadoCalculo, lblCantTexto, lblTipoTexto;

    public ABC_Proveedores() {
        super("ABC Proveedores");
     
       
        panelSuperior.setLayout(new GridLayout(8, 2, 8, 8));

    
        txtID = new JTextField(); 
        txtNombre = new JTextField();
        txtTelefono = new JTextField(); 
        txtIDAdmin = new JTextField();
        
        String[] categoriasBase = {"Alimentos", "Mobiliario", "Utensilios"};
        cmbCategoriaProveedor = new JComboBox<>(categoriasBase);

       
        String[] opcionesSimuladas = {"Alimentos", "Mobiliario", "Utensilios"};
        cmbTipoProductoSimulado = new JComboBox<>(opcionesSimuladas);
        txtCantidadSolicitada = new JTextField();
        txtMensajeEstado = new JTextField();
        txtMensajeEstado.setEditable(false);
        
        btnEjecutarSolicitud = new JButton("Confirmar Pedido");
        btnEjecutarSolicitud.addActionListener(this);


        lblTipoTexto = new JLabel("  Tipo a Solicitar:");
        lblCantTexto = new JLabel("  Cantidad:");

      
        lblTipoTexto.setVisible(false);
        cmbTipoProductoSimulado.setVisible(false);
        lblCantTexto.setVisible(false);
        txtCantidadSolicitada.setVisible(false);
        btnEjecutarSolicitud.setVisible(false);
        txtMensajeEstado.setVisible(false);

      
        lblResultadoCalculo = new JLabel("Total Pedido: $0.00");
        lblResultadoCalculo.setForeground(Color.BLUE); 

     
        panelSuperior.add(new JLabel("IDProveedor:")); panelSuperior.add(txtID);
        panelSuperior.add(new JLabel("Nombre:")); panelSuperior.add(txtNombre);
        panelSuperior.add(new JLabel("Teléfono Contacto:")); panelSuperior.add(txtTelefono);
        panelSuperior.add(new JLabel("IDAdmin Encargado:")); panelSuperior.add(txtIDAdmin);
        panelSuperior.add(new JLabel("Categoría que Provee:")); panelSuperior.add(cmbCategoriaProveedor);

        
        panelSuperior.add(lblTipoTexto); panelSuperior.add(cmbTipoProductoSimulado);
        panelSuperior.add(lblCantTexto); panelSuperior.add(txtCantidadSolicitada);
        panelSuperior.add(btnEjecutarSolicitud); panelSuperior.add(txtMensajeEstado);

      
        panelSuperior.add(lblResultadoCalculo); panelSuperior.add(new JLabel(""));

        
        btnSolicitarPed = new JButton("Solicitar Pedido");
        btnCalcularTot = new JButton("Calcular Total");

        btnSolicitarPed.addActionListener(this);
        btnCalcularTot.addActionListener(this);

        panelBotones.add(btnSolicitarPed);
        panelBotones.add(btnCalcularTot);

        
        this.setSize(650, 360);
        this.validate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == btnSolicitarPed) {
      
            lblTipoTexto.setVisible(true);
            cmbTipoProductoSimulado.setVisible(true);
            lblCantTexto.setVisible(true);
            txtCantidadSolicitada.setVisible(true);
            btnEjecutarSolicitud.setVisible(true);
            txtMensajeEstado.setVisible(true);
            
            txtMensajeEstado.setText("Esperando datos...");
            panelSuperior.validate(); 
        } 
        
        else if (e.getSource() == btnEjecutarSolicitud) {
            String tipo = cmbTipoProductoSimulado.getSelectedItem().toString();
            String cantidad = txtCantidadSolicitada.getText();
            txtMensajeEstado.setText("Se solicitó correctamente " + cantidad + " unidades de " + tipo);
        }
     
        else if (e.getSource() == btnCalcularTot) {
            try {
                String idArticulo = JOptionPane.showInputDialog("Ingresa el ID del artículo a pedir:");
                if (idArticulo != null) {
                    int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la cantidad:"));
                    double precioUnitario = Double.parseDouble(JOptionPane.showInputDialog("Ingresa el precio unitario:"));

                    double total = cantidad * precioUnitario;
                    lblResultadoCalculo.setText("ID Art: " + idArticulo + " | Total Calculado: $" + total);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en los formatos numéricos ingresados.");
            }
        } 
        
        else {
            super.actionPerformed(e);
        }
    }
   
    public void solicitarPedido(int cantidadUnidades) {
        double costoTotal = calcularCostoPedido(cantidadUnidades);
        JOptionPane.showMessageDialog(null, "Pedido solicitado al Proveedor: " + txtNombre.getText() + 
            "\nCategoría: " + cmbCategoriaProveedor.getSelectedItem().toString() + 
            "\nCantidad: " + cantidadUnidades + " unidades.\nCosto Total Neto: $" + costoTotal);
    }

    public double calcularCostoPedido(int unidades) {
        String cat = cmbCategoriaProveedor.getSelectedItem().toString().toLowerCase();
        if (cat.contains("alimento")) {
            return unidades * 45.50;
        } else if (cat.contains("mobiliario")) {
            return unidades * 1200.00; 
        } else {
            return unidades * 85.00; 
        }
    }

    @Override
    public void agregar() {
        try {
            String id = txtID.getText();
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();
            String idAdmin = txtIDAdmin.getText();
            String categoria = cmbCategoriaProveedor.getSelectedItem().toString();
            
            Statement stmt = con.createStatement();

           
            String sqlPadre = "INSERT INTO Proveedores VALUES (" + id + ", '" + nombre + "', '" + telefono + "', " + idAdmin + ")";
            stmt.executeUpdate(sqlPadre);

            
            String sqlHija = "";
            if (categoria.equals("Alimentos")) {
                sqlHija = "INSERT INTO Alimento (IDProveedor) VALUES (" + id + ")";
            } else if (categoria.equals("Mobiliario")) {
                sqlHija = "INSERT INTO Mobiliario (IDProveedor) VALUES (" + id + ")";
            } else if (categoria.equals("Utensilios")) {
                sqlHija = "INSERT INTO Utensilio (IDProveedor) VALUES (" + id + ")";
            }

            
            stmt.executeUpdate(sqlHija);
            
            JOptionPane.showMessageDialog(null, "Proveedor registrado con éxito en Proveedores y en la tabla " + categoria);
        } catch (Exception ex) { 
            JOptionPane.showMessageDialog(null, "Error al agregar: " + ex.toString()); 
        }
    }

    @Override
    public void modificar() {
        try {
            
            String sql = "UPDATE Proveedores SET Nombre='" + txtNombre.getText() + "', Telefono='" + txtTelefono.getText() + "', IDAdmin=" + txtIDAdmin.getText() + " WHERE IDProveedor=" + txtID.getText();
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Proveedor Modificado Correctamente.");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error al modificar: " + ex.toString()); }
    }

    @Override
    public void borrar() {
        try {
            String id = txtID.getText();
            Statement stmt = con.createStatement();
            
            
            stmt.executeUpdate("DELETE FROM Alimento WHERE IDProveedor = " + id);
            stmt.executeUpdate("DELETE FROM Mobiliario WHERE IDProveedor = " + id);
            stmt.executeUpdate("DELETE FROM Utensilio WHERE IDProveedor = " + id);
            
            
            String sqlPadre = "DELETE FROM Proveedores WHERE IDProveedor = " + id;
            int rows = stmt.executeUpdate(sqlPadre);
            
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Proveedor eliminado de todas las tablas con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "El ID de proveedor no existe.");
            }
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error al borrar: " + ex.toString()); }
    }

    @Override
    public void consultar() {
        try {
            String sql = "SELECT * FROM Proveedores WHERE IDProveedor=" + txtID.getText();
            ResultSet rs = con.createStatement().executeQuery(sql);
            if (rs.next()) {
                txtNombre.setText(rs.getString("Nombre"));
                txtTelefono.setText(rs.getString("Telefono"));
                txtIDAdmin.setText(rs.getString("IDAdmin"));
                
                
                cmbCategoriaProveedor.setSelectedItem("Alimentos");
                
                solicitarPedido(50);
            } else { JOptionPane.showMessageDialog(null, "No encontrado"); }
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error: " + ex.toString()); }
    }
}
