package jdbc_ejemplo1;

import javax.swing.JOptionPane;

public class RecetaBebida extends ABC_Recetas {
 
    public String porcentajeAlcohol;

    public RecetaBebida(String id, String nombre, String alcohol) {
        this.txtID.setText(id);
        this.txtNombre.setText(nombre);
        this.porcentajeAlcohol = alcohol;
    }

   
    public void verRecetaBebida() {
        String mensaje = "RECETA DE BEBIDA\n" +
                         "Nombre: " + this.txtNombre.getText() + "\n" +
                         "Grado de Alcohol: " + this.porcentajeAlcohol + "%";
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
