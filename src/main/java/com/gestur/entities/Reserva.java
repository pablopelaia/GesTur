package com.gestur.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date fechaCarga;

	//@Temporal(TemporalType.DATE)
	private String fechaActividad;

	private Integer cantPasajeros;

	private String observaciones;

	private String opinionExito;

	@ManyToOne(fetch = FetchType.EAGER)
	private Actividad actividad;

	@ManyToOne(fetch = FetchType.EAGER)
	private Empleado empleado;

	@ManyToOne
	private Pasajero pasajero;

	public Pasajero getPasajero() {
		return pasajero;
	}

	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public String getFechaActividad() {
		return fechaActividad;
	}

	public void setFechaActividad(String fechaActividad) {
		this.fechaActividad = fechaActividad;
	}

	public Integer getCantPasajeros() {
		return cantPasajeros;
	}

	public void setCantPasajeros(Integer cantPasajeros) {
		this.cantPasajeros = cantPasajeros;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getOpinionExito() {
		return opinionExito;
	}

	public void setOpinionExito(String opinionExito) {
		this.opinionExito = opinionExito;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

}
