package com.gestur.services;

import com.gestur.entities.Reserva;
import com.gestur.repository.ActividadRepository;
import com.gestur.repository.EmpleadoRepository;
import com.gestur.repository.PasajeroRepository;
import com.gestur.repository.ReservaRepository;
import java.util.Date;
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
    public void crearReserva() {
        Reserva reserva = new Reserva();
        reserva.setFechaCarga(new Date());

    }
}
