package Modelo;

public class Cuidador extends Personal {

    private int añosExp;
    private String disponibilidad;
    private String especialidad;

    public Cuidador(String nombre, String cedula, String edad, String telefono,
                    int añosExp, String disponibilidad, String especialidad) {
        super(nombre, cedula, edad, telefono);
        this.añosExp = añosExp;
        this.disponibilidad = disponibilidad;
        this.especialidad = especialidad;
    }

    public int getAñosExp() { return añosExp; }
    public void setAñosExp(int añosExp) { this.añosExp = añosExp; }

    public String getDisponibilidad() { return disponibilidad; }
    public void setDisponibilidad(String disponibilidad) { this.disponibilidad = disponibilidad; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public void atenderMascota() {
        System.out.println("Cuidador " + getNombre() + " esta atendiendo una mascota.");
    }

    public void verificarDisponibilidad() {
        System.out.println("Disponibilidad de " + getNombre() + ": " + disponibilidad);
    }
}