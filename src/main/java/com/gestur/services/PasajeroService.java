
package com.gestur.services;

import com.gestur.entities.Pasajero;
import com.gestur.entities.Reserva;
import com.gestur.exceptions.ErrorServices;
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
    public void crear(String nombre, String apellido, String documento, String nacionalidad, Idiomas idioma, Reserva reserva) throws ErrorServices{
        
        validaDatos (nombre, apellido, documento, nacionalidad, idioma, reserva);
        
        Pasajero pasajero = new Pasajero();
        
        pasajero.setApellido(apellido);
        pasajero.setDocumento(documento);
        pasajero.setIdioma(idioma);
        pasajero.setNacionalidad(nacionalidad);
        pasajero.setReserva(reserva);
        
        pasajeroRepository.save(pasajero);
        
    }
    
    @Transactional
    public void modificarNombre (String id, String nombre) throws ErrorServices{
        
        Optional<Pasajero> respuesta=pasajeroRepository.findById(id);
        if (respuesta.isPresent()){
            Pasajero pasajero = respuesta.get();
            
            validaDatos(nombre, pasajero.getApellido(), pasajero.getDocumento(), pasajero.getNacionalidad(), pasajero.getIdioma(), pasajero.getReserva());
            
            pasajero.setNombre(nombre);
            pasajeroRepository.save(pasajero);
        }        
    }
    
    
    public void modificarApellido (String id, String apellido) throws ErrorServices{
        
        Optional<Pasajero> respuesta=pasajeroRepository.findById(id);
        if (respuesta.isPresent()){
            Pasajero pasajero = respuesta.get();
            
            validaDatos(pasajero.getNombre(), apellido, pasajero.getDocumento(), pasajero.getNacionalidad(), pasajero.getIdioma(), pasajero.getReserva());
            
            pasajero.setApellido(apellido);
            pasajeroRepository.save(pasajero);
        }        
    }
    
    public void modificarDocumento (String id, String documento) throws ErrorServices{
        
        Optional<Pasajero> respuesta=pasajeroRepository.findById(id);
        if (respuesta.isPresent()){
            Pasajero pasajero = respuesta.get();
            
            validaDatos(pasajero.getNombre(), pasajero.getApellido(), documento, pasajero.getNacionalidad(), pasajero.getIdioma(), pasajero.getReserva());
            
            pasajero.setDocumento(documento);
            pasajeroRepository.save(pasajero);
        }        
    }
    
    public void modificarNacionalidad (String id, String nacionalidad) throws ErrorServices{
        
        Optional<Pasajero> respuesta=pasajeroRepository.findById(id);
        if (respuesta.isPresent()){
            Pasajero pasajero = respuesta.get();
            
            validaDatos(pasajero.getNombre(), pasajero.getApellido(), pasajero.getDocumento(), nacionalidad, pasajero.getIdioma(), pasajero.getReserva());
            
            pasajero.setNacionalidad(nacionalidad);
            pasajeroRepository.save(pasajero);
        }        
    }
    
    public void modificarIdioma (String id, Idiomas idioma) throws ErrorServices{
        
        Optional<Pasajero> respuesta=pasajeroRepository.findById(id);
        if (respuesta.isPresent()){
            Pasajero pasajero = respuesta.get();
            
            validaDatos(pasajero.getNombre(), pasajero.getApellido(), pasajero.getDocumento(), pasajero.getNacionalidad(), idioma, pasajero.getReserva());
            
            pasajero.setIdioma(idioma);
            pasajeroRepository.save(pasajero);
        }        
    }
    
    public void modificarReserva (String id, Reserva reserva) throws ErrorServices{
        
        Optional<Pasajero> respuesta=pasajeroRepository.findById(id);
        if (respuesta.isPresent()){
            Pasajero pasajero = respuesta.get();
            
            validaDatos(pasajero.getNombre(), pasajero.getApellido(), pasajero.getDocumento(), pasajero.getNacionalidad(), pasajero.getIdioma(),reserva);
            
            pasajero.setReserva(reserva);
            pasajeroRepository.save(pasajero);
        }        
    }         

    private void validaDatos(String nombre, String apellido, String documento, String nacionalidad, Idiomas idioma, Reserva reserva) throws ErrorServices{
        
        if (nombre.isEmpty()||nombre.equals(null)){
            throw new ErrorServices("El nombre no puede estar vacío");
        }
        
        if (apellido.isEmpty()||apellido.equals(null)){
            throw new ErrorServices("El apellido no puede estar vacío");
        }
        
        if (documento.isEmpty()||documento.equals(null)){
            throw new ErrorServices("El documento no puede estar vacío");
        }
        
        if (nacionalidad.isEmpty()||nacionalidad.equals(null)){
            throw new ErrorServices("La nacionalidad no puede estar vacío");
        }
        
        if (idioma.equals(null)){
            throw new ErrorServices("Falta ingresar idioma");
        }
        
        if (reserva.equals(null)){
            throw new ErrorServices("Falta asociar reserva");
        }
    }
}
