package jdbc_ejemplo1;

public class ProveedorAlimento extends ABC_Proveedores {
    public ProveedorAlimento(int id, String nombre) {
        this.txtID.setText(String.valueOf(id));
        this.txtNombre.setText(nombre);
        this.txtCategoria.setText("Alimento");
    }
}
