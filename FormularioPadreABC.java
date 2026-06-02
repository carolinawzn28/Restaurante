package jdbc_ejemplo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public abstract class FormularioPadreABC extends JFrame implements ActionListener {
    public Connection con; 
    public JButton btnAgregar, btnModificar, btnBorrar, btnConsultar;
    public JPanel panelSuperior, panelBotones;

    public FormularioPadreABC(String titulo) {
        super(titulo);
        panelSuperior = new JPanel();
        panelBotones = new JPanel();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Restaurante;user=sa;password=jffp1234;encrypt=true;trustServerCertificate=true;";
            con = DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.toString());
        }

        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(panelSuperior, BorderLayout.NORTH);
        c.add(panelBotones, BorderLayout.SOUTH);

        panelBotones.setLayout(new FlowLayout());

        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnBorrar = new JButton("Borrar");
        btnConsultar = new JButton("Consultar");

        btnAgregar.addActionListener(this);
        btnModificar.addActionListener(this);
        btnBorrar.addActionListener(this);
        btnConsultar.addActionListener(this);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnBorrar);     
        panelBotones.add(btnConsultar);
    }

    public abstract void agregar();
    public abstract void modificar();
    public abstract void borrar();
    public abstract void consultar();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) { agregar(); }
        if (e.getSource() == btnModificar) { modificar(); }
        if (e.getSource() == btnBorrar) { borrar(); }
        if (e.getSource() == btnConsultar) { consultar(); }
    }
}
