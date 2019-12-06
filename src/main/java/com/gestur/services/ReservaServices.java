package com.gestur.services;

import com.gestur.entities.Pasajero;
import com.gestur.entities.Actividad;
import com.gestur.entities.Empleado;
import com.gestur.entities.Reserva;
import com.gestur.repository.ActividadRepository;
import com.gestur.repository.EmpleadoRepository;
import com.gestur.repository.PasajeroRepository;
import com.gestur.repository.ReservaRepository;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServices {

    @Autowired
    private ReservaRepository resRep;
    @Autowired
    private ActividadRepository adRep;
    @Autowired
    private EmpleadoRepository emRep;
    @Autowired
    private PasajeroRepository pasRep;
    /*    
     Date fechaCarga;    
     Date fechaActividad;
     Integer cantPasajeros;
     String observaciones;
     String opinionExito;    
     Actividad actividad;  
     Empleado empleado;
     Pasajero pasajero;
     */

    @Transactional
    public void crearReserva(String pasajeroId, String empleadoId, String actividadId, Date fechaActividad, Integer cantPasajeros, String observaciones, String opinionExito) {
        //Crear clase exceptions (Tal VEZ)
        Reserva reserva = new Reserva();
        Optional<Empleado> emp = empRep.findById(empleadoId);
        Optional<Actividad> act = actRep.findById(actividadId);
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

        }

    }
}
