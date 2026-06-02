package jdbc_ejemplo1;

import javax.swing.JOptionPane;

public class RecetaPostre extends ABC_Recetas {
   
    public String tempRefrigeracion;
    public String tempHorneado;

    public RecetaPostre(String id, String nombre, String tempRef, String tempHor) {
        this.txtID.setText(id);
        this.txtNombre.setText(nombre);
        this.tempRefrigeracion = tempRef;
        this.tempHorneado = tempHor;
    }

   
    public void verRecetaPostre() {
        String mensaje = "RECETA DE POSTRE\n" +
                         "Nombre: " + this.txtNombre.getText() + "\n" +
                         "Temp. Refrigeración: " + this.tempRefrigeracion + "\n" +
                         "Temp. Horneado: " + this.tempHorneado;
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
