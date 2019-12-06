package com.gestur.services;

import com.gestur.entities.Pasajero;
import com.gestur.entities.Actividad;
import com.gestur.entities.Empleado;
import com.gestur.entities.Reserva;
import com.gestur.exceptions.ErrorServices;
import com.gestur.repository.ActividadRepository;
import com.gestur.repository.EmpleadoRepository;
import com.gestur.repository.PasajeroRepository;
import com.gestur.repository.ReservaRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository resRep;
    @Autowired
    private ActividadRepository adRep;
    @Autowired
    private EmpleadoRepository emRep;
    @Autowired
    private PasajeroRepository pasRep;

    @Transactional
    public void crearReserva(String pasajeroId, String empleadoId, String actividadId, Date fechaActividad, Integer cantPasajeros, String observaciones, String opinionExito) throws ErrorServices {
        validarReserva(pasajeroId, empleadoId, actividadId, fechaActividad, cantPasajeros, observaciones, opinionExito);
        Reserva reserva = new Reserva();
        Optional<Empleado> emp = emRep.findById(empleadoId);
        Optional<Actividad> act = adRep.findById(actividadId);
        Optional<Pasajero> pas = pasRep.findById(pasajeroId);
        if (emp.isPresent() && act.isPresent() && pas.isPresent()) {
            Empleado empleado = emp.get();
            Actividad actividad = act.get();
            Pasajero pasajero = pas.get();
            reserva.setFechaCarga(new Date());
            reserva.setFechaActividad(fechaActividad);
            reserva.setCantPasajeros(cantPasajeros);
            reserva.setObservaciones(observaciones);
            reserva.setOpinionExito(opinionExito);
            reserva.setEmpleado(empleado);
            reserva.setPasajero(pasajero);
            reserva.setActividad(actividad);
            resRep.save(reserva);

        } else {
            throw new ErrorServices("Verifique la existencia del pasajero, empleado o actividad.");
        }

    }

    public List<Reserva> buscarReserva() {
        return resRep.listaReserva();
    }

    public List<Reserva> buscarReserva(String actividad) throws ErrorServices {
        validarStrings(actividad);
        return resRep.reservaPorActividad(actividad);
    }

    //Modifica todos a la vez
    public void modificarReserva(Date fechaActividad, Integer cantPasajeros, Integer id) throws ErrorServices {
        validarModificacion(fechaActividad, cantPasajeros, id);
        Optional<Reserva> res = resRep.findById(id);
        if (res.isPresent()) {
            resRep.modificarReserva(fechaActividad, cantPasajeros, id);
        } else {
            throw new ErrorServices("No existe tal reserva.");
        }

    }

    //Solo la fecha de actividad

    public void modificarReserva(Date fechaActividad, Integer id) throws ErrorServices {
        validarModificacion(fechaActividad, id);
        Optional<Reserva> res = resRep.findById(id);
        if (res.isPresent()) {
            resRep.modificarFecha(fechaActividad, id);
        } else {
            throw new ErrorServices("No existe tal reserva.");
        }

    }

    //Solo la cantidad de pasajeros
    public void modificarReserva(Integer cantPasajeros, Integer id) throws ErrorServices {
        validarModificacion(cantPasajeros, id);
        Optional<Reserva> res = resRep.findById(id);
        if (res.isPresent()) {
            resRep.modificarPasajeros(cantPasajeros, id);
        } else {
            throw new ErrorServices("No existe tal reserva.");
        }

    }

    public void borrarReserva(Integer id) throws ErrorServices {
        Optional<Reserva> res = resRep.findById(id);
        if (res.isPresent()) {
            Reserva reserva = res.get();
            resRep.delete(reserva);
        } else {
            throw new ErrorServices("No existe tal reserva.");
        }
    }

    public void validarReserva(String pasajeroId, String empleadoId, String actividadId, Date fechaActividad, Integer cantPasajeros, String observaciones, String opinionExito) throws ErrorServices {
        if (pasajeroId == null || pasajeroId.isEmpty() || empleadoId == null || empleadoId.isEmpty() || actividadId == null || actividadId.isEmpty() || fechaActividad == null || cantPasajeros == null || observaciones == null || observaciones.isEmpty() || opinionExito == null || opinionExito.isEmpty()) {
            throw new ErrorServices("Ningún campo puede ir nulo.");
        }
    }

    public void validarModificacion(Date a, Integer b, Integer c) throws ErrorServices {
        if (a == null || b == null || c == null) {
            throw new ErrorServices("Ningún campo puede ir nulo.");
        }
    }

    public void validarModificacion(Date a, Integer c) throws ErrorServices {
        if (a == null || c == null) {
            throw new ErrorServices("Ningún campo puede ir nulo.");
        }
    }

    public void validarModificacion(Integer b, Integer c) throws ErrorServices {
        if (b == null || c == null) {
            throw new ErrorServices("Ningún campo puede ir nulo.");
        }
    }

    public void validarStrings(String busqueda) throws ErrorServices {
        if (busqueda.isEmpty() || busqueda == null) {
            throw new ErrorServices("Este campo no puede ir nulo.");
        }
    }
}
