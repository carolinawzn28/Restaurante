package jdbc_ejemplo1;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ABC_Reservaciones extends FormularioPadreABC {
    public JTextField txtNombreCliente, txtFecha, txtHora, txtPersonas, txtIDHost;

    public ABC_Reservaciones() {
        super("Módulo Reservaciones (Host)");
        panelSuperior.setLayout(new GridLayout(5, 2, 5, 5));

        txtNombreCliente = new JTextField(); txtFecha = new JTextField();
        txtHora = new JTextField(); txtPersonas = new JTextField(); txtIDHost = new JTextField();

        panelSuperior.add(new JLabel("Nombre del Cliente (A nombre de quién):")); panelSuperior.add(txtNombreCliente);
        panelSuperior.add(new JLabel("Fecha (YYYY-MM-DD):")); panelSuperior.add(txtFecha);
        panelSuperior.add(new JLabel("Hora (HH:MM:SS):")); panelSuperior.add(txtHora);
        panelSuperior.add(new JLabel("Cantidad Personas:")); panelSuperior.add(txtPersonas);
        panelSuperior.add(new JLabel("IDHost Responsable:")); panelSuperior.add(txtIDHost);

        this.setSize(450, 250);
    }

    @Override
    public void agregar() {
        try {
            String sql = "INSERT INTO Reservaciones VALUES ('" + txtNombreCliente.getText() + "', '" + txtFecha.getText() + "', '" + txtHora.getText() + "', " + txtPersonas.getText() + ", " + txtIDHost.getText() + ")";
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Reservación Agendada Exitosamente");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }

    @Override
    public void modificar() {
        try {
            String sql = "UPDATE Reservaciones SET Fecha='" + txtFecha.getText() + "', Hora='" + txtHora.getText() + "', Personas=" + txtPersonas.getText() + ", IDHost=" + txtIDHost.getText() + " WHERE NombreCliente='" + txtNombreCliente.getText() + "'";
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Reservación Modificada");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }

    @Override
    public void borrar() {
        try {
            String sql = "DELETE FROM Reservaciones WHERE NombreCliente='" + txtNombreCliente.getText() + "'";
            con.createStatement().executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Reservación Eliminada de Manera Eficiente");
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }

    @Override
    public void consultar() {
        try {
            String sql = "SELECT * FROM Reservaciones WHERE NombreCliente='" + txtNombreCliente.getText() + "'";
            ResultSet rs = con.createStatement().executeQuery(sql);
            if (rs.next()) {
                txtFecha.setText(rs.getString("Fecha"));
                txtHora.setText(rs.getString("Hora"));
                txtPersonas.setText(rs.getString("Personas"));
                txtIDHost.setText(rs.getString("IDHost"));
            } else { JOptionPane.showMessageDialog(null, "No existe reservación a ese nombre."); }
        } catch (Exception ex) { JOptionPane.showMessageDialog(null, ex.toString()); }
    }
}
