package jdbc_ejemplo1;

public class RecetaPostre extends ABC_Recetas {
    public String tempRefrigeracion;
    public String tempHorneado;

    public RecetaPostre(String id, String nombre, String tempRef, String tempHor) {
        this.txtID.setText(id);
        this.txtNombre.setText(nombre);
        this.tempRefrigeracion = tempRef;
        this.tempHorneado = tempHor;
    }

    public void verReceta() {
        super.verDetalleReceta("Tipo: Postre\nTemp. Refrigeración: " + this.tempRefrigeracion + "\nTemp. Horneado: " + this.tempHorneado);
    }
}
