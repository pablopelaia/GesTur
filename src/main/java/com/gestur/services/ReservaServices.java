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
public class ReservaServices {

    @Autowired
    private ReservaRepository resRep;
    @Autowired
    private ActividadRepository adRep;
    @Autowired
    private EmpleadoRepository emRep;
    @Autowired
    private PasajeroRepository pasRep;
    
    @Transactional
    public void crearReserva(String pasajeroId, String empleadoId, String actividadId, Date fechaActividad, Integer cantPasajeros, String observaciones, String opinionExito)throws ErrorServicio {
        validarReserva(pasajeroId, empleadoId, actividadId, fechaActividad, cantPasajeros, observaciones, opinionExito);
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
            throw new ErrorServices("Verifique la existencia del pasajero, empleado o actividad.");
        }

    }
    
    
    public List<Object> buscarReserva(String actividad)throws ErrorServices{
       validarStrings(actividad);
       return resRep.reservaPorActividad(actividad);
    }
    public List<Object> buscarReserva(){
       return resRep.reservaPorActividad();
    }
    public void validarReserva(String pasajeroId, String empleadoId, String actividadId, Date fechaActividad, Integer cantPasajeros, String observaciones, String opinionExito)throws ErrorServices{
        if(pasajeroId==null||pasajeroId.isEmpty()||empleadoId==null||empleadoId.isEmpty()||actividadId==null||actividadId.isEmpty()||fechaActividad==null||cantPasajeros==null||observaciones==null||observaciones.isEmpty()||opinionExito==null||opinionExito.isEmpty()){
            throw new ErrorServices("Ningún campo puede ir nulo.");
        }        
    }
    public void validarStrings(String busqueda) throws ErrorServices{
        if(busqueda.isEmpty()||busqueda==null){
            throw new ErrorServices("Este campo no puede ir nulo.");
        }
    }
}
