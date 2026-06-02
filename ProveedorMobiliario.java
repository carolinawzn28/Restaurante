package jdbc_ejemplo1;

public class ProveedorMobiliario extends ABC_Proveedores {
    public ProveedorMobiliario(int id, String nombre) {
        this.txtID.setText(String.valueOf(id));
        this.txtNombre.setText(nombre);
        this.txtCategoria.setText("Mobiliario");
    }
}
