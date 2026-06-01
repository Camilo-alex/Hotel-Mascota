package hotelmascotas;

import Controlador.ControladorHotel;
import Vista.JFSistemaHotel;

public class HotelMascotas {
    public static void main (String[]args){
        JFSistemaHotel frmHotel = new JFSistemaHotel();
        ControladorHotel controlador = new ControladorHotel(frmHotel);
        frmHotel.setVisible(true);
    }
}