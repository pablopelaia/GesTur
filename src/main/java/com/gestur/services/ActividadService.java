
package com.gestur.services;

import com.gestur.entities.Actividad;
import com.gestur.exceptions.ErrorServices;
import com.gestur.repository.ActividadRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActividadService {
    
    @Autowired
    private ActividadRepository adRep;
    
    
    @Transactional
    public void crearActividad(String nombre, Double precio, String lugar) throws ErrorServices {
        Actividad a = new Actividad();
            validar(nombre, precio, lugar);
            
            a.setNombre(nombre);
            a.setPrecio(precio);
            a.setLugar(lugar);
            adRep.save(a);

    }
    
    public void modificarActividad(String id, String nombre, Double precio, String lugar){
        Optional<Actividad> act = adRep.findById(id);
        if(act.isPresent()){
            adRep.modificarTodo(nombre, lugar, precio);
        }
    }
    public void modificarActividadNombre(String id, String nombre){
        Optional<Actividad> act = adRep.findById(id);
        if(act.isPresent()){
            adRep.modificarNombre(nombre);
        }
    }
    public void modificarActividadPrecio(String id, Double precio){
        Optional<Actividad> act = adRep.findById(id);
        if(act.isPresent()){
            adRep.modificarPrecio(precio);
        }
    }
    public void modificarActividadLugar(String id, String lugar){
        Optional<Actividad> act = adRep.findById(id);
        if(act.isPresent()){
            adRep.modificarLugar(lugar);
        }
    }
    
    
    public void validar(String nombre, Double precio, String lugar) throws ErrorServices{
        if(nombre.isEmpty() || nombre == null){
        throw new ErrorServices("El nombre no puede ir nulo.");
        }
        if(precio == null){
        throw new ErrorServices("El precio no puede ir nulo.");
        }
        if(lugar.isEmpty() || lugar == null){
        throw new ErrorServices("El lugar no puede ir nulo.");
        }
    }
    
}
    
    
    
