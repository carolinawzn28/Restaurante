package jdbc_ejemplo1;

public class RecetaPollo extends ABC_Recetas {
    public String lote;

    public RecetaPollo(String id, String nombre, String lote) {
        this.txtID.setText(id);
        this.txtNombre.setText(nombre);
        this.lote = lote;
    }

    public void verReceta() {
        super.verDetalleReceta("Tipo: Pollo\nLote: " + this.lote);
    }
}
