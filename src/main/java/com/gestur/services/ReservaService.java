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
        validarReserva(pasajeroId, empleadoId, actividadId, fechaActividad, cantPasajeros);
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

    //Lista completa
    public List<Reserva> listaReserva() {
        return resRep.listaReserva();
    }

    //Reserva por NOMBRE de ACTIVIDAD
    public List<Reserva> buscarReservaActividad(String actividad) throws ErrorServices {
        validarStrings(actividad);
        return resRep.reservaPorActividad(actividad);
    }

    //Reserva por LUGAR de ACTIVIDAD
    public List<Reserva> buscarReservaLugar(String lugar) throws ErrorServices {
        validarStrings(lugar);
        return resRep.reservaPorLugar(lugar);
    }

    //Modifica TODA una RESERVA a la vez
    @Transactional
    public void modificarReserva(Date fechaActividad, Integer cantPasajeros, Integer id) throws ErrorServices {
        validarModificacion(fechaActividad, cantPasajeros, id);
        Optional<Reserva> res = resRep.findById(id);
        if (res.isPresent()) {
            resRep.modificarReserva(fechaActividad, cantPasajeros, id);
        } else {
            throw new ErrorServices("No existe tal reserva.");
        }

    }

    //Solo la FECHA de ACTIVIDAD
    @Transactional
    public void modificarReserva(Date fechaActividad, Integer id) throws ErrorServices {
        validarModificacion(fechaActividad, id);
        Optional<Reserva> res = resRep.findById(id);
        if (res.isPresent()) {
            resRep.modificarFecha(fechaActividad, id);
        } else {
            throw new ErrorServices("No existe tal reserva.");
        }

    }

    //Solo la CANTIDAD de PASAJEROS
    @Transactional
    public void modificarReserva(Integer cantPasajeros, Integer id) throws ErrorServices {
        validarModificacion(cantPasajeros, id);
        Optional<Reserva> res = resRep.findById(id);
        if (res.isPresent()) {
            resRep.modificarPasajeros(cantPasajeros, id);
        } else {
            throw new ErrorServices("No existe tal reserva.");
        }

    }

    @Transactional
    public void borrarReserva(Integer id) throws ErrorServices {
        validarInteger(id);
        Optional<Reserva> res = resRep.findById(id);
        if (res.isPresent()) {
            Reserva reserva = res.get();
            resRep.delete(reserva);
        } else {
            throw new ErrorServices("No existe tal reserva.");
        }
    }

    public void validarReserva(String pasajeroId, String empleadoId, String actividadId, Date fechaActividad, Integer cantPasajeros) throws ErrorServices {
        if (pasajeroId == null || pasajeroId.isEmpty()) {
            throw new ErrorServices("'pasajeroId' no puede ir nulo.");
        } else if (empleadoId == null || empleadoId.isEmpty()) {
            throw new ErrorServices("'empleadoId' no puede ir nulo.");
        } else if (actividadId == null || actividadId.isEmpty()) {
            throw new ErrorServices("'actividadId' no puede ir nulo.");
        } else if (cantPasajeros == null) {
            throw new ErrorServices("'Cantidad de Pasajeros' no puede ir nulo.");
        } else if (fechaActividad == null) {
            throw new ErrorServices("'Fecha de Actividad' no puede ir nulo.");
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

    public void validarInteger(Integer a) throws ErrorServices {
        if (a == null) {
            throw new ErrorServices("Este campo no puede ir nulo.");
        }
    }
}
