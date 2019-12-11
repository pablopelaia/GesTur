package com.gestur.services;

import com.gestur.entities.Empleado;
import com.gestur.exceptions.ErrorServices;
import com.gestur.repository.EmpleadoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository er;

    @Transactional
    public void crearEmpleado(String nombre) throws ErrorServices {
        Empleado e = new Empleado();
        validarNombre(nombre);
        e.setNombre(nombre);
        er.save(e);

    }

    public List<Empleado> listaEmpleado() {
        return er.empleadosPorNombre();
    }

    public List<Empleado> buscarEmpleado(String nombre) throws ErrorServices {
        validarNombre(nombre);
        return er.buscarEmpleado(nombre);
    }

    @Transactional
    public void modificarEmpleado(String id, String nombre) throws ErrorServices {
        validarID(id);
        validarNombre(nombre);
        Optional<Empleado> emp = er.findById(id);
        if (emp.isPresent()) {
            er.modificarNombreEmpleado(id, nombre);
        } else {
            throw new ErrorServices("No existe tal empleado.");
        }
    }

    @Transactional
    public void borrarEmpleado(String id) throws ErrorServices {
        validarID(id);
        Optional<Empleado> emp = er.findById(id);
        if (emp.isPresent()) {
            Empleado empleado = emp.get();
            er.delete(empleado);
        } else {
            throw new ErrorServices("No existe tal empleado.");
        }
    }

    public void validarNombre(String nombre) throws ErrorServices {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServices("'Nombre' no puede ser nulo.");
        }
        if (!(nombre instanceof String)) {
            throw new ErrorServices("'ID' debe ser una cadena de texto.");
        }
    }

    public void validarID(String id) throws ErrorServices {
        if (id == null || id.isEmpty()) {
            throw new ErrorServices("'ID' no puede ser nulo.");
        }
        if (!(id instanceof String)) {
            throw new ErrorServices("'ID' debe ser una cadena de texto.");
        }
    }

}
