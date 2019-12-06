
package com.gestur.services;

import com.gestur.entities.Pasajero;
import com.gestur.entities.Reserva;
import com.gestur.repository.PasajeroRepository;
import enumeraciones.Idiomas;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasajeroService {
    private Pasajero pasajero;
    
    @Autowired
    private PasajeroRepository pasajeroRepository;
    
    @Transactional
    public void crear(String nombre, String apellido, String documento, String nacionalidad, Idiomas idioma, Reserva reserva){
        
        Pasajero pasajero = new Pasajero();
        
        pasajero.setApellido(apellido);
        pasajero.setDocumento(documento);
        pasajero.setIdioma(idioma);
        pasajero.setNacionalidad(nacionalidad);
        pasajero.setReserva(reserva);
        
        pasajeroRepository.save(pasajero);
        
    }
    
    @Transactional
    public void modificarPasajero (String id, String nombre, String apellido, String documento, String nacionalidad, String idioma, Reserva reserva){
        
        Optional<Pasajero> respuesta=pasajeroRepository.findById(id);
        if (respuesta.isPresent()){
            Pasajero pasajero = respuesta.get();
            
            if (!nombre.equals(null)){
                pasajero.setNombre(nombre);
            }
            
            if (!apellido.equals(null)){
                pasajero.setApellido(apellido);
            }
            
            if (!documento.equals(null)){
                pasajero.setDocumento(documento);
            }
            
            if (!nacionalidad.equals(null)){
                pasajero.setNacionalidad(nacionalidad);
            }
            
            if (!reserva.equals(null)){
                pasajero.setReserva(reserva);
            }
            
            pasajeroRepository.save(pasajero);
        }        
    }   
}
