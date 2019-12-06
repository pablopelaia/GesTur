
package com.gestur.services;

import com.gestur.entities.Actividad;
import com.gestur.repository.ActividadRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActividadService {
    
    @Autowired
    private ActividadRepository adRep;
    
    
    @Transactional
    public void crearActividad(String nombre, Double precio, String lugar) {
        Actividad a = new Actividad();
       
        
            a.setNombre(nombre);
            a.setPrecio(precio);
            a.setLugar(lugar);
            adRep.save(a);

    }
}
