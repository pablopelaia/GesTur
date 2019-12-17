package com.gestur.services;

import com.gestur.entities.Empleado;
import com.gestur.entities.Rol;
import com.gestur.exceptions.ErrorServices;
import com.gestur.repository.EmpleadoRepository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("empleadoService")
public class EmpleadoService implements UserDetailsService {

	@Autowired
	private EmpleadoRepository er;

	@Transactional
	public void crearEmpleado(String nombre, String username, String contraseña) throws ErrorServices {
		Empleado e = new Empleado();
		validarNombre(nombre);
		e.setNombre(nombre);
		e.setUsername(username);
		e.setContraseña(contraseña);
		e.setEnabled(true);
		
		
		
		er.save(e);

	}

//
//	public List<Empleado> listaEmpleado() {
//		return er.empleadosPorNombre();
//	}
//
//	public List<Empleado> buscarEmpleado(String nombre) throws ErrorServices {
//		validarNombre(nombre);
//		return er.buscarEmpleado(nombre);
//	}
//
//	@Transactional
//	public void modificarEmpleado(Long id, String nombre) throws ErrorServices {
//		validarID(id);
//		validarNombre(nombre);
//		Empleado emp = er.findById(id).orElse(null);
//		if (emp != null) {
//			er.modificarNombreEmpleado(nombre, id);
//		} else {
//			throw new ErrorServices("No existe tal Empleado.");
//		}
//	}
//
//	@Transactional
//	public void borrarEmpleado(Long id) throws ErrorServices {
//		validarID(id);
//		Empleado emp = er.findById(id).orElse(null);
//		if (emp != null) {
//			er.delete(emp);
//		} else {
//			throw new ErrorServices("No existe tal Empleado.");
//		}
//	}
//
	public void validarNombre(String nombre) throws ErrorServices {
		if (nombre == null || nombre.isEmpty()) {
			throw new ErrorServices("'Nombre' no puede ser nulo.");
		}
		if (!(nombre instanceof String)) {
			throw new ErrorServices("'ID' debe ser una Cadena de Texto.");
		}
	}

//
//	public void validarID(Long id) throws ErrorServices {
//		if (id == null) {
//			throw new ErrorServices("'ID' no puede ser nulo.");
//		}
//		if (!(id instanceof Long)) {
//			throw new ErrorServices("'ID' debe ser una Cadena de Texto.");
//		}
//	}
//
//	public Empleado buscarPorUsername(String username, String contraseña) {
//		return er.findByUsernameAndContraseña(username, contraseña);
//	}
//	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Empleado empleado = er.findByUsername(username);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Rol rol : empleado.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(rol.getAuthority()));
		}

		System.out.println(empleado.getUsername() + " " + empleado.getContraseña());

		return new User(empleado.getUsername(), empleado.getContraseña(), empleado.getEnabled(), true, true, true,
				authorities);

	}

}
