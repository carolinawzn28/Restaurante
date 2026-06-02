package jdbc_ejemplo1;

class Mesero extends Empleado {
    public double propinasDia;
    public int meserosTurno;

    public Mesero(int id, String nombre, int salario, double propinasDia, int meserosTurno) {
        super(id, nombre, salario, meserosTurno); 
        this.propinasDia = propinasDia;
        this.meserosTurno = meserosTurno;
    }


    @Override
    public double calcularSueldo() {
        if (meserosTurno > 0) {
            return this.salario + (propinasDia / meserosTurno);
        }
        return this.salario;
    }
}
