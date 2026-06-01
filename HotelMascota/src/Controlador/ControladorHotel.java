package Controlador;

import Modelo.*;
import Vista.JFReserva;
import Vista.JFSistemaHotel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.time.LocalDate;

public class ControladorHotel implements ActionListener {

    private JFSistemaHotel frmHotel;
    private JFReserva frmReserva;
    private SistemaHotel sistema;

    public ControladorHotel(JFSistemaHotel frmHotel) {
        this.frmHotel = frmHotel;
        this.sistema = new SistemaHotel();
        this.frmReserva = new JFReserva();

        cargarPersonalEjemplo();

        frmHotel.btnGuardarCliente.addActionListener(this);
        frmHotel.btnRegistrarMascota.addActionListener(this);
        frmHotel.btnReservasActivas.addActionListener(this);
        frmHotel.btnPersonalDisponible.addActionListener(this);
        frmHotel.btnFinalizarProgram.addActionListener(this);

        frmReserva.btnGuardar.addActionListener(this);
        frmReserva.btnLimpiar.addActionListener(this);
        frmReserva.btnVolverCliente.addActionListener(this);

        frmReserva.cmbBoxNecesidades.removeAllItems();
        for (Servicio s : Servicio.values()) {
            frmReserva.cmbBoxNecesidades.addItem(s.toString());
        }
    }

    private void cargarPersonalEjemplo() {
        sistema.registrarEmpleado(new Cuidador("Carlos Ramirez", "123456", "30", "3001234567", 5, "Disponible", "Perros grandes"));
        sistema.registrarEmpleado(new Cuidador("Maria Lopez", "789012", "25", "3109876543", 3, "Disponible", "Gatos y perros pequeños"));
        sistema.registrarEmpleado(new Cuidador("Juan Perez", "345678", "35", "3207654321", 8, "No disponible", "Animales exoticos"));
        sistema.registrarEmpleado(new Administrador("Ana Garcia", "901234", "40", "3156789012"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmHotel.btnGuardarCliente) {
            guardarCliente();
        } else if (e.getSource() == frmHotel.btnRegistrarMascota) {
            abrirVentanaReserva();
        } else if (e.getSource() == frmHotel.btnReservasActivas) {
            mostrarReservasActivas();
        } else if (e.getSource() == frmHotel.btnPersonalDisponible) {
            mostrarPersonalDisponible();
        } else if (e.getSource() == frmHotel.btnFinalizarProgram) {
            finalizarPrograma();
        } else if (e.getSource() == frmReserva.btnGuardar) {
            guardarReserva();
        } else if (e.getSource() == frmReserva.btnLimpiar) {
            limpiarCamposReserva();
        } else if (e.getSource() == frmReserva.btnVolverCliente) {
            volverVentanaPrincipal();
        }
    }

    private void guardarCliente() {
        String nombre = frmHotel.txtNombreCliente.getText();
        String cedula = frmHotel.txtCedulaCliente.getText();
        String edad = frmHotel.txtEdadCliente.getText();
        String telefono = frmHotel.txtTlfCliente.getText();

        if (nombre.isEmpty() || cedula.isEmpty() || edad.isEmpty() || telefono.isEmpty()) {
            frmHotel.AreaResultsCliente.setText("Por favor complete todos los campos del cliente.");
            return;
        }

        Cliente cliente = new Cliente(nombre, cedula, edad, telefono);
        sistema.registrarCliente(cliente);

        frmReserva.cmbBoxDueño.addItem(nombre);

        frmHotel.AreaResultsCliente.setText("Cliente registrado exitosamente:\n"
                + "Nombre: " + nombre + "\n"
                + "Cedula: " + cedula + "\n" + "Edad: " + edad + "\n" + "Telefono: " + telefono);

        limpiarCamposCliente();
    }

    private void abrirVentanaReserva() {
        if (frmReserva.cmbBoxDueño.getItemCount() == 0) {
            frmHotel.AreaResultsCliente.setText("Primero debe registrar un cliente.");
            return;
        }
        frmReserva.setVisible(true);
    }

    private void guardarReserva() {
        String nombreMascota = frmReserva.txtNameMascota.getText();
        String raza = frmReserva.txtRaza.getText();
        String edadTxt = frmReserva.txtEdadPerro.getText();
        String fechaIngreso = frmReserva.txtFechaIngreso.getText();
        String diasTxt = frmReserva.txtFechaSalida.getText();
        String servicio = (String) frmReserva.cmbBoxNecesidades.getSelectedItem();
        String dueño = (String) frmReserva.cmbBoxDueño.getSelectedItem();

        if (nombreMascota.isEmpty() || raza.isEmpty() || edadTxt.isEmpty()
                || fechaIngreso.isEmpty() || diasTxt.isEmpty()) {
            frmReserva.AreaAnimal.setText("Por favor complete todos los campos.");
            return;
        }

        try {
            
            int edad = Integer.parseInt(edadTxt);
            Mascota mascota = new Mascota(nombreMascota, raza, edad);
            mascota.actualizarNecesidades(Servicio.valueOf(servicio));

            
            String[] partesFecha = fechaIngreso.split("/");
            if (partesFecha.length != 3) {
                frmReserva.AreaAnimal.setText("Formato de fecha incorrecto.\nUse el formato: DD/MM/YYYY");
                return;
            }

            LocalDate fIngreso = LocalDate.of(
                    Integer.parseInt(partesFecha[2].trim()), 
                    Integer.parseInt(partesFecha[1].trim()), 
                    Integer.parseInt(partesFecha[0].trim()) 
            );

            
            int dias = Integer.parseInt(diasTxt);
            Reserva reserva = new Reserva(fIngreso, dias);
            reserva.agregarServicio(Servicio.valueOf(servicio));

            
            for (Cliente c : sistema.getClientes()) {
                if (c.getNombre().equals(dueño)) {
                    c.registrarMascota(mascota);
                    c.hacerReserva(reserva);
                    break;
                }
            }

            frmReserva.AreaAnimal.setText(
                    "Reserva registrada exitosamente:\n"
                    + "Dueno: " + dueño + "\n"
                    + "Mascota: " + nombreMascota + "\n"
                    + "Raza: " + raza + "\n"
                    + "Edad: " + edad + " anos\n"
                    + "Servicio: " + servicio + "\n"
                    + "Fecha Ingreso: " + fIngreso + "\n"
                    + "Dias de estadia: " + dias + "\n"
                    + "Fecha Salida: " + reserva.getFechaSalida()
            );

            limpiarCamposReserva();

        } catch (NumberFormatException ex) {
            frmReserva.AreaAnimal.setText("Error: verifique que edad y dias sean numeros validos.");
        } catch (Exception ex) {
            frmReserva.AreaAnimal.setText("Error inesperado: " + ex.getMessage());
        }
    }

    private void mostrarReservasActivas() {
        if (sistema.getClientes().isEmpty()) {
            frmHotel.AreaResultsCliente.setText("No hay clientes registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder("=== RESERVAS ACTIVAS ===\n\n");
        for (Cliente c : sistema.getClientes()) {
            if (!c.getReservas().isEmpty()) {
                sb.append("Cliente: ").append(c.getNombre()).append("\n");
                for (Reserva r : c.getReservas()) {
                    sb.append("  Ingreso: ").append(r.getFechaIngreso()).append("\n");
                    sb.append("  Salida: ").append(r.getFechaSalida()).append("\n");
                    sb.append("  Dias: ").append(r.getDiasEstadia()).append("\n");
                    sb.append("  Servicios: ").append(r.getServiciosAdd()).append("\n\n");
                }
            }
        }

        if (sb.toString().equals("=== RESERVAS ACTIVAS ===\n\n")) {
            sb.append("No hay reservas activas.");
        }

        frmHotel.AreaResultsCliente.setText(sb.toString());
    }

    private void mostrarPersonalDisponible() {
        if (sistema.getEmpleados().isEmpty()) {
            frmHotel.AreaResultsCliente.setText("No hay personal registrado.");
            return;
        }

        StringBuilder sb = new StringBuilder("=== PERSONAL DISPONIBLE ===\n\n");
        for (Personal p : sistema.getEmpleados()) {
            sb.append("Nombre: ").append(p.getNombre()).append("\n");
            if (p instanceof Cuidador) {
                Cuidador c = (Cuidador) p;
                sb.append("Disponibilidad: ").append(c.getDisponibilidad()).append("\n");
                sb.append("Especialidad: ").append(c.getEspecialidad()).append("\n\n");
            }
        }

        frmHotel.AreaResultsCliente.setText(sb.toString());
    }

    private void limpiarCamposCliente() {
        frmHotel.txtNombreCliente.setText("");
        frmHotel.txtCedulaCliente.setText("");
        frmHotel.txtEdadCliente.setText("");
        frmHotel.txtTlfCliente.setText("");
    }

    private void limpiarCamposReserva() {
        frmReserva.txtNameMascota.setText("");
        frmReserva.txtRaza.setText("");
        frmReserva.txtEdadPerro.setText("");
        frmReserva.txtFechaIngreso.setText("");
        frmReserva.txtFechaSalida.setText("");
    }

    private void volverVentanaPrincipal() {
        frmReserva.setVisible(false);
        frmHotel.setVisible(true);
    }

    private void finalizarPrograma() {
        System.exit(0);
    }
}
