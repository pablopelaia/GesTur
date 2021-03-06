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
	public void crearReserva(String pasajeroId, Long empleadoId, String actividadId, String fechaActividad,
			Integer cantPasajeros, String observaciones, String opinionExito) throws ErrorServices {
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
			throw new ErrorServices("Verifique la existencia del Pasajero, Empleado o Actividad.");
		}

	}

	// Lista completa
	public List<Reserva> listaReserva() {
		return resRep.listaReserva();
	}

	public Reserva buscarReservaPorId(Integer id) {
		return resRep.findById(id).orElse(null);
	}

	// Reserva por NOMBRE de ACTIVIDAD
	public List<Reserva> buscarReservaActividad(String id) {
		return resRep.reservaPorActividad(id);
	}

	public List<Reserva> buscarReservaNombrePasajero(String nombre) {
		nombre = '%' + nombre + '%';
		return resRep.reservaPorNombrePasajero(nombre);
	}

	public List<Reserva> buscarReservaDocumentoPasajero(String documento) {
		return resRep.reservaPorDocumentoPasajero(documento);
	}

	public List<Reserva> buscarReservaPorFecha(String desde, String hasta) {
		return resRep.findByFechaActividadBetween(desde, hasta);
	}

	// Modifica TODA una RESERVA a la vez
	@Transactional
	public void modificarReserva(Date fechaActividad, Integer cantPasajeros, Integer id) throws ErrorServices {
		validarFecha(fechaActividad);
		validarId(id);
		validarCantidad(cantPasajeros);
		Optional<Reserva> res = resRep.findById(id);
		if (res.isPresent()) {
			resRep.modificarReserva(fechaActividad, cantPasajeros, id);
		} else {
			throw new ErrorServices("No existe tal Reserva.");
		}

	}

	// Solo la FECHA de ACTIVIDAD
	@Transactional
	public void modificarReserva(Date fechaActividad, Integer id) throws ErrorServices {
		validarId(id);
		validarFecha(fechaActividad);
		Optional<Reserva> res = resRep.findById(id);
		if (res.isPresent()) {
			resRep.modificarFecha(fechaActividad, id);
		} else {
			throw new ErrorServices("No existe tal Reserva.");
		}

	}

	// Solo la CANTIDAD de PASAJEROS
	@Transactional
	public void modificarReserva(Integer cantPasajeros, Integer id) throws ErrorServices {
		validarCantidad(cantPasajeros);
		validarId(id);
		Optional<Reserva> res = resRep.findById(id);
		if (res.isPresent()) {
			resRep.modificarPasajeros(cantPasajeros, id);
		} else {
			throw new ErrorServices("No existe tal Reserva.");
		}

	}

	@Transactional
	public void borrarReserva(Integer id) throws ErrorServices {
		validarId(id);
		Optional<Reserva> res = resRep.findById(id);
		if (res.isPresent()) {
			Reserva reserva = res.get();
			resRep.delete(reserva);
		} else {
			throw new ErrorServices("No existe tal Reserva.");
		}
	}

	public void validarReserva(String pasajeroId, Long empleadoId, String actividadId, String fechaActividad,
			Integer cantPasajeros) throws ErrorServices {
		if (pasajeroId == null || pasajeroId.isEmpty()) {
			throw new ErrorServices("'pasajeroId' no puede ser nulo.");
		}
		if (empleadoId == null) {
			throw new ErrorServices("'empleadoId' no puede ser nulo.");
		}
		if (actividadId == null || actividadId.isEmpty()) {
			throw new ErrorServices("'actividadId' no puede ser nulo.");
		}
		if (cantPasajeros == null) {
			throw new ErrorServices("'Cantidad de Pasajeros' no puede ser nulo.");
		}
		if (fechaActividad == null) {
			throw new ErrorServices("'Fecha de Actividad' no puede ser nulo.");
		}

		if (!(pasajeroId instanceof String)) {
			throw new ErrorServices("'pasajeroId' debe ser una Cadena de Texto.");
		}
		if (!(empleadoId instanceof Long)) {
			throw new ErrorServices("'empleadoId' debe ser una Cadena de Texto.");
		}
		if (!(actividadId instanceof String)) {
			throw new ErrorServices("'actividadId' debe ser una Cadena de Texto.");
		}
		if (!(fechaActividad instanceof String)) {
			throw new ErrorServices("'Fecha de Actividad' debe ser una Fecha.");
		}
		if (!(cantPasajeros instanceof Integer)) {
			throw new ErrorServices("'Cantidad de Pasajeros' debe ser un Número.");
		}

	}

	public void validarId(Integer a) throws ErrorServices {
		if (a == null) {
			throw new ErrorServices("'ID' no puede ser nulo.");
		}
		if (!(a instanceof Integer)) {
			throw new ErrorServices("'ID' debe ser un Número.");
		}
	}

	public void validarCantidad(Integer a) throws ErrorServices {
		if (a == null) {
			throw new ErrorServices("'Cantidad de Pasajeros' no puede ser nulo.");
		}
		if (!(a instanceof Integer)) {
			throw new ErrorServices("'Cantidad de Pasajeros' debe ser un Número.");
		}
	}

	public void validarFecha(Date fechaActividad) throws ErrorServices {
		if (fechaActividad == null) {
			throw new ErrorServices("'Fecha de Actividad' no puede ser nulo.");
		}
		if (!(fechaActividad instanceof Date)) {
			throw new ErrorServices("'Fecha de Actividad' debe ser una Fecha.");
		}
	}

	public void validarLugar(String a) throws ErrorServices {
		if (a == null || a.isEmpty()) {
			throw new ErrorServices("'Lugar' no puede ser nulo.");
		}
		if (!(a instanceof String)) {
			throw new ErrorServices("'Lugar' debe ser una Cadena de Texto.");
		}
	}

	public void validarNombre(String a) throws ErrorServices {
		if (a == null || a.isEmpty()) {
			throw new ErrorServices("'Nombre' no puede ser nulo.");
		}
		if (!(a instanceof String)) {
			throw new ErrorServices("'Nombre' debe ser una Cadena de Texto.");
		}
	}
}
