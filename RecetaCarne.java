package jdbc_ejemplo1;

import javax.swing.JOptionPane;

public class RecetaCarne extends ABC_Recetas {
    
    public String lote;
    public String terminoCoccion;

    public RecetaCarne(String id, String nombre, String lote, String termino) {
        this.txtID.setText(id);
        this.txtNombre.setText(nombre);
        this.lote = lote;
        this.terminoCoccion = termino;
    }

   
    public void verRecetaCarne() {
        String mensaje = "RECETA DE CARNE\n" +
                         "Nombre: " + this.txtNombre.getText() + "\n" +
                         "Lote: " + this.lote + "\n" +
                         "Término: " + this.terminoCoccion;
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
