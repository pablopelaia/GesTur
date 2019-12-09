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

    @Transactional
    public void modificarActividad(String id, String nombre, Double precio, String lugar) throws ErrorServices {
        validarModificacion(id, nombre, precio, lugar);
        Optional<Actividad> act = adRep.findById(id);
        if (act.isPresent()) {
            adRep.modificarTodo(nombre, lugar, precio, id);
        } else {
            throw new ErrorServices("No existe tal actividad");
        }
    }

    @Transactional
    public void modificarActividadNombre(String id, String nombre) throws ErrorServices {
        validarString(id);
        validarString(nombre);
        Optional<Actividad> act = adRep.findById(id);
        if (act.isPresent()) {
            adRep.modificarNombre(nombre, id);
        } else {
            throw new ErrorServices("No existe tal actividad");
        }
    }

    @Transactional
    public void modificarActividadPrecio(String id, Double precio) throws ErrorServices {
        Optional<Actividad> act = adRep.findById(id);
        if (act.isPresent()) {
            adRep.modificarPrecio(precio, id);
        } else {
            throw new ErrorServices("No existe tal actividad");
        }
    }

    @Transactional
    public void modificarActividadLugar(String id, String lugar) throws ErrorServices {
        validarString(id);
        validarString(lugar);
        Optional<Actividad> act = adRep.findById(id);
        if (act.isPresent()) {
            adRep.modificarLugar(lugar, id);
        } else {
            throw new ErrorServices("No existe tal actividad");
        }
    }

    @Transactional
    public void borrarActividad(String id) throws ErrorServices {
        validarString(id);
        Optional<Actividad> act = adRep.findById(id);
        if (act.isPresent()) {
            Actividad actividad = act.get();
            adRep.delete(actividad);
        } else {
            throw new ErrorServices("No existe tal actividad");
        }
    }

    public void validar(String nombre, Double precio, String lugar) throws ErrorServices {

        if (nombre.isEmpty() || nombre == null) {
            throw new ErrorServices("El nombre no puede ir nulo.");
        }
        if (precio == null) {
            throw new ErrorServices("El precio no puede ir nulo.");
        }
        if (lugar.isEmpty() || lugar == null) {
            throw new ErrorServices("El lugar no puede ir nulo.");
        }
    }

    public void validarModificacion(String id, String nombre, Double precio, String lugar) throws ErrorServices {
        if (id.isEmpty() || id == null) {
            throw new ErrorServices("El nombre no puede ir nulo.");
        }
        if (nombre.isEmpty() || nombre == null) {
            throw new ErrorServices("El nombre no puede ir nulo.");
        }
        if (precio == null) {
            throw new ErrorServices("El precio no puede ir nulo.");
        }
        if (lugar.isEmpty() || lugar == null) {
            throw new ErrorServices("El lugar no puede ir nulo.");
        }
    }

    public void validarString(String a) throws ErrorServices {
        if (a.isEmpty() || a == null) {
            throw new ErrorServices("Este campo no puede ir nulo.");
        }
    }

    public void validarDouble(Double a) throws ErrorServices {
        if (a == null) {
            throw new ErrorServices("Este campo no puede ir nulo.");
        }
    }

}
