package jdbc_ejemplo1;

public class ProveedorUtensilio extends ABC_Proveedores {
    public ProveedorUtensilio(int id, String nombre) {
        this.txtID.setText(String.valueOf(id));
        this.txtNombre.setText(nombre);
        this.txtCategoria.setText("Utensilio");
    }
}
