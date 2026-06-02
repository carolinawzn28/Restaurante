package jdbc_ejemplo1;

class Chef extends Empleado {
    public int idTipoReceta; 

    public Chef(int id, String nombre, String tel, double salario, int idTipoReceta) {
        super(id, nombre, tel, salario);
        this.idTipoReceta = idTipoReceta;
    }
  
}
