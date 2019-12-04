package com.gestur.services;

import com.gestur.entities.Reserva;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServices {

    @Autowired
    private ActividadRepository adRep;
    @Autowired
    private EmpleadoRepository emRep;
    @Autowired
    private PasajeroRepository pasRep;
    /*
    @Temporal(TemporalType.DATE)
    private Date fechaCarga;

    @Temporal(TemporalType.DATE)
    private Date fechaActividad;

    private Integer cantPasajeros;

    private String observaciones;

    private String opinionExito;

    @ManyToOne
    private Actividad actividad;

    @ManyToOne
    private Empleado empleado;
    */
    @Transactional
    public void crearReserva(){
        Reserva reserva=new Reserva();
        reserva.setFechaCarga(new Date());
        
    }
}
