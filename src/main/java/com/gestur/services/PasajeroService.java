
package com.gestur.services;

import com.gestur.entities.Pasajero;
import com.gestur.entities.Reserva;
import org.springframework.stereotype.Service;

@Service
public class PasajeroService {
    private Pasajero pasajero;
    
    public Pasajero crear(String nombre, String apellido, String documento, String nacionalidad, String idioma, Reserva reserva){
        
        Pasajero pasajero = new Pasajero();
        
        pasajero.setApellido(apellido);
        pasajero.setDocumento(documento);
        pasajero.setIdioma(idioma);
        pasajero.setNacionalidad(nacionalidad);
        pasajero.setReserva(reserva);
        
        return pasajero;
    }
        
    
        
          
        
        
    
    
}
