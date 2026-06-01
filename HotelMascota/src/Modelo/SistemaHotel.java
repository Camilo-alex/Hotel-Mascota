package Modelo;

import java.util.ArrayList;
import java.util.List;

public class SistemaHotel {

    private ArrayList<Personal> empleados;
    private ArrayList<Cliente> clientes;

    public SistemaHotel() {
        this.empleados = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public ArrayList<Personal> getEmpleados() { return empleados; }
    public ArrayList<Cliente> getClientes() { return clientes; }

    public void registrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void registrarEmpleado(Personal personal) {
        empleados.add(personal);
    }

    public void mostrarReservas() {
        for (Cliente c : clientes) {
            System.out.println("Cliente: " + c.getNombre());
            c.verReserva();
        }
    }

    public void adminReservasActivas() {
        System.out.println("Administrando reservas activas...");
    }

    public void asignarCuidador() {
        System.out.println("Asignando cuidador a mascota...");
    }
}