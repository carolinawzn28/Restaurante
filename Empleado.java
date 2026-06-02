package jdbc_ejemplo1;

public class Empleado {

    public int idEmpleado;
    public String nombre;
    public int salario; 
    public int idRol;

   
    public Empleado(int idEmpleado, String nombre, int salario, int idRol) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.salario = salario;
        this.idRol = idRol;
    }

    public Empleado() {
        this.idEmpleado = 0;
        this.nombre = "";
        this.salario = 0;
        this.idRol = 0;
    }

    public double calcularSueldo() {
        return this.salario;
    }
}
