package jdbc_ejemplo1;

class Admin extends Empleado {
    public Admin(int id, String nombre, int salario) {
       
        super(id, nombre, salario, 1); 
    }
}
