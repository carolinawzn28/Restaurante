package jdbc_ejemplo1;

import javax.swing.JOptionPane;


public class RecetaPollo extends ABC_Recetas {
  
    public String lote;

    public RecetaPollo(String id, String nombre, String lote) {
        this.txtID.setText(id);
        this.txtNombre.setText(nombre);
        this.lote = lote;
    }


    public void verRecetaPollo() {
        String mensaje = "RECETA DE POLLO\n" +
                         "Nombre: " + this.txtNombre.getText() + "\n" +
                         "Lote: " + this.lote;
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
