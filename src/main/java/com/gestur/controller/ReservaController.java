package com.gestur.controller;

import com.gestur.entities.Pasajero;
import com.gestur.entities.Reserva;
import com.gestur.exceptions.ErrorServices;
import com.gestur.services.ActividadService;
import com.gestur.services.PasajeroService;
import com.gestur.services.ReservaService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

	@Autowired
	private ReservaService resServ;

	@Autowired
	private ActividadService acSev;

	@Autowired
	private PasajeroService pasServ;

	@GetMapping({ "/", "" })
	public String paginaPpal(ModelMap model) {
		model.addAttribute("titulo", "GesTur - Reservas");
		model.addAttribute("cabecera", "Nueva reserva");
		model.addAttribute("actividades", acSev.listaActividad());
		return "reserva.html";
	}

	@GetMapping("/crearReserva")
	public String creRes(ModelMap model) {
		model.addAttribute("titulo", "Crear Reserva");
		return "crearReserva.html";
	}

	// formaction="crear"
	@PostMapping("/crearReserva")
	public String crearReserva(@RequestParam String nombre, @RequestParam String apellido,
			@RequestParam String documento, @RequestParam(required = false) String empleadoId,
			@RequestParam String actividadId, @RequestParam Integer cantPasajeros, @RequestParam String fechaActividad,
			@RequestParam(required = false) String observaciones, @RequestParam(required = false) String opinionExito,
			ModelMap model, String exito) throws ErrorServices {

		Pasajero pasajero = pasServ.crear(nombre, apellido, documento, null, null);

		Date date1 = null;
		try {
			System.out.println(fechaActividad);
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaActividad);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		resServ.crearReserva(pasajero.getId(), "1", actividadId, date1, cantPasajeros, observaciones, opinionExito);

//		return "redirect:/listaReserva";
		return "redirect:/";
	}

	@PostMapping("/listaReserva")
	public String listaReserva(@RequestParam(required = false) String nombre, ModelMap model) throws ErrorServices {
		model.addAttribute("titulo", "Listado de Reservas");
		List<Reserva> listaReserva;
		if (nombre == null) {
			listaReserva = resServ.listaReserva();
		} else {
			listaReserva = resServ.buscarReservaActividad(nombre);
		}
		model.put("listaReserva", listaReserva);
		return "listaReserva.html";
	}

	@GetMapping("/editarReserva")
	public String edRes(ModelMap model) throws ErrorServices {
		model.addAttribute("titulo", "Edite una Reserva completa");
		return "modificarReserva.html";
	}

	// formaction="res"
	@PostMapping("/editarReserva/res")
	public String editarReserva(@RequestParam Date fechaActividad, @RequestParam Integer cantPasajeros,
			@RequestParam Integer id, String exito, ModelMap model) throws ErrorServices {
		resServ.modificarReserva(fechaActividad, cantPasajeros, id);
		return "redirect:/listaReserva";
	}

	@GetMapping("/editarFecha")
	public String edFecha(ModelMap model) throws ErrorServices {
		model.addAttribute("titulo", "Edite la Fecha");
		return "editarFecha.html";
	}

	// formaction="fecha"
	@PostMapping("/editarFecha/fecha")
	public String editarFecha(@RequestParam Date fechaActividad, @RequestParam Integer id, String exito, ModelMap model)
			throws ErrorServices {
		resServ.modificarReserva(fechaActividad, id);
		return "redirect:/listaReserva";
	}

	@GetMapping("/editarCantPasajeros")
	public String edPas(ModelMap model) throws ErrorServices {
		model.addAttribute("titulo", "Edite la Cantidad de Pasajeros");
		return "editarCantPas.html";
	}

	// formaction="cantPas"
	@PostMapping("/editarCantPasajeros/cantPas")
	public String editarCantPasajeros(@RequestParam Integer cantPasajeros, @RequestParam Integer id, String exito,
			ModelMap model) throws ErrorServices {
		resServ.modificarReserva(cantPasajeros, id);
		return "redirect:/listaReserva";
	}

	@GetMapping("/borrarReserva")
	public String borRes(ModelMap model) {
		model.addAttribute("titulo", "Elimine una Reserva");
		return "borrarReserva.html";
	}

	// formaction="borrar"
	@PostMapping("/borrarReserva/borrar")
	public String borrarReserva(@RequestParam Integer id) throws ErrorServices {
		resServ.borrarReserva(id);
		return "redirect:/listaReserva";
	}

}
