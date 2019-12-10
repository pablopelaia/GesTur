package com.gestur.services;

import com.gestur.entities.Actividad;
import com.gestur.exceptions.ErrorServices;
import com.gestur.repository.ActividadRepository;
import java.util.List;
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
        validarCreacion(nombre, precio, lugar);
        Actividad a = new Actividad();
        a.setNombre(nombre);
        a.setPrecio(precio);
        a.setLugar(lugar);
        adRep.save(a);

    }

    public List<Actividad> listaActividad() {
        return adRep.listaActividad();
    }

    public List<Actividad> listaPorNombre() {
        return adRep.actsPorNombre();
    }

    public List<Actividad> listaPorLugar() {
        return adRep.actsPorLugar();
    }

    public List<Actividad> listaPorPrecio() {
        return adRep.actsPorPrecio();
    }

    public List<Actividad> buscarPorNombre(String nombre) throws ErrorServices {
        validarNombre(nombre);
        return adRep.buscarPorNombre(nombre);
    }

    public List<Actividad> buscarPorLugar(String lugar) throws ErrorServices {
        validarLugar(lugar);
        return adRep.buscarPorLugar(lugar);
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
    public void modificarNombre(String id, String nombre) throws ErrorServices {
        validarID(id);
        validarNombre(nombre);
        Optional<Actividad> act = adRep.findById(id);
        if (act.isPresent()) {
            adRep.modificarNombre(nombre, id);
        } else {
            throw new ErrorServices("No existe tal actividad");
        }
    }

    @Transactional
    public void modificarPrecio(String id, Double precio) throws ErrorServices {
        validarDouble(precio);
        Optional<Actividad> act = adRep.findById(id);
        if (act.isPresent()) {
            adRep.modificarPrecio(precio, id);
        } else {
            throw new ErrorServices("No existe tal actividad");
        }
    }

    @Transactional
    public void modificarLugar(String id, String lugar) throws ErrorServices {
        validarID(id);
        validarLugar(lugar);
        Optional<Actividad> act = adRep.findById(id);
        if (act.isPresent()) {
            adRep.modificarLugar(lugar, id);
        } else {
            throw new ErrorServices("No existe tal actividad");
        }
    }

    @Transactional
    public void eliminarActividad(String id) throws ErrorServices {
        validarID(id);
        Optional<Actividad> act = adRep.findById(id);
        if (act.isPresent()) {
            Actividad actividad = act.get();
            adRep.delete(actividad);
        } else {
            throw new ErrorServices("No existe tal actividad");
        }
    }

    public void validarCreacion(String nombre, Double precio, String lugar) throws ErrorServices {

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
            throw new ErrorServices("'ID' no puede ir nulo.");
        }
        if (nombre.isEmpty() || nombre == null) {
            throw new ErrorServices("'Nombre' no puede ir nulo.");
        }
        if (precio == null) {
            throw new ErrorServices("'Precio' no puede ir nulo.");
        }
        if (lugar.isEmpty() || lugar == null) {
            throw new ErrorServices("'Lugar' no puede ir nulo.");
        }
    }

    public void validarNombre(String a) throws ErrorServices {
        if (a.isEmpty() || a == null) {
            throw new ErrorServices("'Nombre' no puede ir nulo.");
        }
    }

    public void validarID(String a) throws ErrorServices {
        if (a.isEmpty() || a == null) {
            throw new ErrorServices("'ID' no puede ir nulo.");
        }
    }

    public void validarLugar(String a) throws ErrorServices {
        if (a.isEmpty() || a == null) {
            throw new ErrorServices("'Lugar' no puede ir nulo.");
        }
    }

    public void validarDouble(Double a) throws ErrorServices {
        if (a == null) {
            throw new ErrorServices("'Precio' no puede ir nulo.");
        }
    }

}
