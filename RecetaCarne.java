package jdbc_ejemplo1;

public class RecetaCarne extends ABC_Recetas {
    public String lote;
    public String terminoCoccion;

    public RecetaCarne(String id, String nombre, String lote, String termino) {
        this.txtID.setText(id);
        this.txtNombre.setText(nombre);
        this.lote = lote;
        this.terminoCoccion = termino;
    }

    public void verReceta() {
        super.verDetalleReceta("Tipo: Carne\nLote: " + this.lote + "\nTérmino Cocción: " + this.terminoCoccion);
    }
}
