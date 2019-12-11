package com.gestur.entities;

import enumeraciones.Permisos;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuarios {

	@Id
	private Integer legajo;
	private String nombre;
	private String apellido;
	private String mail;
	private Date fechaingreso;
	private Permisos permiso;
	private String contraseña;

	public Usuarios() {
	}

	public Integer getLegajo() {
		return legajo;
	}

	public void setLegajo(Integer legajo) {
		this.legajo = legajo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getFechaingreso() {
		return fechaingreso;
	}

	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public Permisos getPermiso() {
		return permiso;
	}

	public void setPermiso(Permisos permiso) {
		this.permiso = permiso;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

}
