package jdbc_ejemplo1;

public class Empleado {
    public int idEmpleado;
    public String nombre;
    public String telefono;
    public double salario; 

    public Empleado(int idEmpleado, String nombre, String telefono, double salario) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.telefono = telefono;
        this.salario = salario;
    }

    public Empleado() {
        this.idEmpleado = 0;
        this.nombre = "";
        this.telefono = "";
        this.salario = 0.0;
    }

    public double calcularSueldo() {
        return this.salario;
    }
}
