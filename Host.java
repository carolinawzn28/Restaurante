package jdbc_ejemplo1;

class Host extends Empleado {
    public int clientesMes;

    public Host(int id, String nombre, int salario, int clientesMes) {
      
        super(id, nombre, salario, 3); 
        this.clientesMes = clientesMes;
    }

    @Override
    public double calcularSueldo() {
        if (clientesMes > 300) {
            return this.salario + (this.salario * 0.15);
        }
        return this.salario;
    }
}
