package jdbc_ejemplo1;

import javax.swing.*;
import java.awt.event.*;

public class MenuPrincipal extends JFrame implements ActionListener {
    public JMenuBar mBar;
    public JMenu mABC, mDetalles;
    public JMenuItem iEmp, iRec, iRes, iIng, iProv, iDetalleSalario;
    public String puestoUsuario; 

  
    public MenuPrincipal(String puesto) {
        super("Sistema de Operaciones - Restaurante");
        this.puestoUsuario = puesto;

        mBar = new JMenuBar();
        mABC = new JMenu("ABC");
        mDetalles = new JMenu("Detalles");

        iEmp = new JMenuItem("Empleados");
        iRec = new JMenuItem("Recetas");
        iRes = new JMenuItem("Reservaciones");
        iIng = new JMenuItem("Ingredientes");
        iProv = new JMenuItem("Proveedores");
        iDetalleSalario = new JMenuItem("Detalle de Salario Neto");

        iEmp.addActionListener(this); iRec.addActionListener(this);
        iRes.addActionListener(this); iIng.addActionListener(this);
        iProv.addActionListener(this); iDetalleSalario.addActionListener(this);

        mABC.add(iEmp); mABC.add(iRec); mABC.add(iRes); mABC.add(iIng); mABC.add(iProv);
        mDetalles.add(iDetalleSalario);
        
        mBar.add(mABC); mBar.add(mDetalles);
        setJMenuBar(mBar);
        this.setSize(450, 350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        
        if (puestoUsuario.equalsIgnoreCase("Chef")) {
            
            iEmp.setEnabled(false);
            iRes.setEnabled(false);
            iProv.setEnabled(false);
            
        } else if (puestoUsuario.equalsIgnoreCase("Mesero")) {
           
            mABC.setEnabled(false); 
           
            
        } else if (puestoUsuario.equalsIgnoreCase("Host")) {
      
            iEmp.setEnabled(false);
            iRec.setEnabled(false);
            iIng.setEnabled(false);
            iProv.setEnabled(false);
           
            
        } else if (puestoUsuario.equalsIgnoreCase("Admin")) {
         
            iEmp.setEnabled(true); iRec.setEnabled(true); iRes.setEnabled(true);
            iIng.setEnabled(true); iProv.setEnabled(true); iDetalleSalario.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == iEmp) { new ABC_Empleado().setVisible(true); }
        if (e.getSource() == iRec) { new ABC_Recetas().setVisible(true); }
        if (e.getSource() == iRes) { new ABC_Reservaciones().setVisible(true); }
        if (e.getSource() == iIng) { new ABC_Ingredientes().setVisible(true); }
        if (e.getSource() == iProv) { new ABC_Proveedores().setVisible(true); }
        if (e.getSource() == iDetalleSalario) { new CalculoSalario().setVisible(true); }
    }
}
