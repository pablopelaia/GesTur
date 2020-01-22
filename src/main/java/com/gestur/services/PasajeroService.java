
package com.gestur.services;

import com.gestur.entities.Pasajero;
import com.gestur.exceptions.ErrorServices;
import com.gestur.repository.PasajeroRepository;
import enumeraciones.Idiomas;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasajeroService {

	@Autowired
	private PasajeroRepository pasajeroRepository;

	@Transactional
	public Pasajero crear(String nombre, String apellido, String documento, String nacionalidad, Idiomas idioma)
			throws ErrorServices {

		validaDatos(nombre, apellido, documento);

		Pasajero pasajero = new Pasajero();

		pasajero.setNombre(nombre);
		pasajero.setApellido(apellido);
		pasajero.setDocumento(documento);
		pasajero.setIdioma(idioma);
		pasajero.setNacionalidad(nacionalidad);

		pasajeroRepository.save(pasajero);

		return pasajero;

	}

	@Transactional
	public void modificarNombre(String id, String nombre) throws ErrorServices {

		Optional<Pasajero> respuesta = pasajeroRepository.findById(id);
		if (respuesta.isPresent()) {
			Pasajero pasajero = respuesta.get();

			validaDatos(nombre, pasajero.getApellido(), pasajero.getDocumento());

			pasajero.setNombre(nombre);
			pasajeroRepository.save(pasajero);
		} else {
			throw new ErrorServices("no existe pasajero con ese id");
		}
	}

	@Transactional
	public void modificarApellido(String id, String apellido) throws ErrorServices {

		Optional<Pasajero> respuesta = pasajeroRepository.findById(id);
		if (respuesta.isPresent()) {
			Pasajero pasajero = respuesta.get();

			validaDatos(pasajero.getNombre(), apellido, pasajero.getDocumento());

			pasajero.setApellido(apellido);
			pasajeroRepository.save(pasajero);
		} else {
			throw new ErrorServices("no existe pasajero con ese id");
		}
	}

	@Transactional
	public void modificarDocumento(String id, String documento) throws ErrorServices {

		Optional<Pasajero> respuesta = pasajeroRepository.findById(id);
		if (respuesta.isPresent()) {
			Pasajero pasajero = respuesta.get();

			validaDatos(pasajero.getNombre(), pasajero.getApellido(), documento);

			pasajero.setDocumento(documento);
			pasajeroRepository.save(pasajero);
		} else {
			throw new ErrorServices("no existe pasajero con ese id");
		}
	}

	@Transactional
	public void modificarNacionalidad(String id, String nacionalidad) throws ErrorServices {

		Optional<Pasajero> respuesta = pasajeroRepository.findById(id);
		if (respuesta.isPresent()) {
			Pasajero pasajero = respuesta.get();

			validaDatos(pasajero.getNombre(), pasajero.getApellido(), pasajero.getDocumento());

			pasajero.setNacionalidad(nacionalidad);
			pasajeroRepository.save(pasajero);
		} else {
			throw new ErrorServices("no existe pasajero con ese id");
		}
	}

	@Transactional
	public void modificarIdioma(String id, Idiomas idioma) throws ErrorServices {

		Optional<Pasajero> respuesta = pasajeroRepository.findById(id);
		if (respuesta.isPresent()) {
			Pasajero pasajero = respuesta.get();

			validaDatos(pasajero.getNombre(), pasajero.getApellido(), pasajero.getDocumento());

			pasajero.setIdioma(idioma);
			pasajeroRepository.save(pasajero);
		} else {
			throw new ErrorServices("no existe pasajero con ese id");
		}
	}

	@Transactional
	private void validaDatos(String nombre, String apellido, String documento) throws ErrorServices {

		if (nombre.isEmpty() || nombre.equals(null)) {
			throw new ErrorServices("El nombre no puede estar vacío");
		} else {
			if (!(nombre instanceof String)) {
				throw new ErrorServices("El nombre debe ser un Sring");
			}
		}

		if (apellido.isEmpty() || apellido.equals(null)) {
			throw new ErrorServices("El apellido no puede estar vacío");
		} else {
			if (!(apellido instanceof String)) {
				throw new ErrorServices("El apellido debe ser un Sring");
			}
		}

		if (documento.isEmpty() || documento.equals(null)) {
			throw new ErrorServices("El documento no puede estar vacío");
		} else {
			if (!(documento instanceof String)) {
				throw new ErrorServices("El documento debe ser un Sring");
			}
		}
	}
}
