package jdbc_ejemplo1;

import javax.swing.*;
import java.awt.event.*;

public class MenuPrincipal extends JFrame implements ActionListener {
    public JMenuBar mBar;
    public JMenu mABC;
    public JMenuItem iEmp, iRec, iRes, iIng, iProv;
    public int rol;
    public JMenu menuDetalles;
    public JMenuItem DetalleSalario;

    public MenuPrincipal(int rol) {
        super("Menú");
        this.rol = rol;
        mBar = new JMenuBar(); mABC = new JMenu("ABC");
        iEmp = new JMenuItem("Empleados"); iRec = new JMenuItem("Recetas");
        iRes = new JMenuItem("Reservaciones"); iIng = new JMenuItem("Ingredientes");
        iProv = new JMenuItem("Proveedores");
        menuDetalles = new JMenu("Detalles");
        DetalleSalario = new JMenuItem("Calcular Salario");
      
        

        iEmp.addActionListener(this); iRec.addActionListener(this);
        iRes.addActionListener(this); iIng.addActionListener(this);
        iProv.addActionListener(this);
        DetalleSalario.addActionListener(this);

        mABC.add(iEmp); mABC.add(iRec); mABC.add(iRes); mABC.add(iIng); mABC.add(iProv);
        mBar.add(mABC); setJMenuBar(mBar);
        menuDetalles.add(DetalleSalario);
        mBar.add(menuDetalles);
        
        this.setSize(400, 300);

        if (rol != 1) { iEmp.setEnabled(false); }
        if (rol == 2) { iRes.setEnabled(false);
        				iProv.setEnabled(false);}
        if (rol == 3) { iIng.setEnabled(false);
						iProv.setEnabled(false);
						iRec.setEnabled(false);}
        if (rol == 4) { iIng.setEnabled(false);
						iProv.setEnabled(false);
						iRec.setEnabled(false);
						iRes.setEnabled(false);}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == iEmp) { new ABC_Empleado().setVisible(true); }
        if (e.getSource() == iRec) { new ABC_Recetas().setVisible(true); }
        if (e.getSource() == iRes) { new ABC_Reservaciones().setVisible(true); }
        if (e.getSource() == iIng) { new ABC_Ingredientes().setVisible(true); }
        if (e.getSource() == iProv) { new ABC_Proveedores().setVisible(true); }
        if(e.getSource() == DetalleSalario) { new CalculoSalario().setVisible(true);} 
    }
}
