package jdbc_ejemplo1;

public class RecetaBebida extends ABC_Recetas {
    public String gradoAlcohol;

    public RecetaBebida(String id, String nombre, String alcohol) {
        this.txtID.setText(id);
        this.txtNombre.setText(nombre);
        this.gradoAlcohol = alcohol;
    }

    public void verReceta() {
        super.verDetalleReceta("Tipo: Bebida\nGrado de Alcohol: " + this.gradoAlcohol);
    }
}
