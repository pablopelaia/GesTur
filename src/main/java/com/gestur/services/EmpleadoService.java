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
        validar(nombre);
        e.setNombre(nombre);
        er.save(e);

    }

    public List<Empleado> listaEmpleado() {
        return er.empleadosPorNombre();
    }

    public List<Empleado> listaEmpleado(String nombre) throws ErrorServices {
        validar(nombre);
        return er.buscarEmpleado(nombre);
    }

    @Transactional
    public void modificarEmpleado(String id, String nombre) throws ErrorServices {
        Optional<Empleado> emp = er.findById(id);
        if (emp.isPresent()) {
            er.modificarNombreEmpleado(nombre);
        } else {
            throw new ErrorServices("No existe tal empleado");
        }
    }

    @Transactional
    public void borrarEmpleado(String id) throws ErrorServices {
        validar(id);
        Optional<Empleado> emp = er.findById(id);
        if (emp.isPresent()) {
            Empleado empleado = emp.get();
            er.delete(empleado);
        } else {
            throw new ErrorServices("No existe tal empleado.");
        }
    }

    public void validar(String nombre) throws ErrorServices {
        if (nombre.isEmpty() || nombre == null) {
            throw new ErrorServices("El nombre no puede ir nulo.");
        }
    }

}
