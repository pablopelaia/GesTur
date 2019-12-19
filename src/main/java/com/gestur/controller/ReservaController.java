package com.gestur.controller;

import com.gestur.entities.Pasajero;
import com.gestur.entities.Reserva;
import com.gestur.exceptions.ErrorServices;
import com.gestur.services.ActividadService;
import com.gestur.services.EmpleadoService;
import com.gestur.services.PasajeroService;
import com.gestur.services.ReservaService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@Autowired
	private EmpleadoService empServ;

	@GetMapping({ "/", "" })
	public String paginaPpal(ModelMap model) {
		model.addAttribute("titulo", "GesTur - Reservas");
		model.addAttribute("cabecera", "Nueva reserva");
		model.addAttribute("actividades", acSev.listaActividad());
		model.addAttribute("empleados", empServ.listar());
		return "reservas/reserva";
	}

	// Probablemente no se use
	@GetMapping("/crearReserva")
	public String creRes(ModelMap model) {
		model.addAttribute("titulo", "Crear Reserva");
		return "crearReserva.html";
	}

	// formaction="crear"
	@PostMapping("/crearReserva")
	public String crearReserva(@RequestParam String nombre, @RequestParam String apellido,
			@RequestParam String documento, @RequestParam(required = false) Long empleadoId,
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

		resServ.crearReserva(pasajero.getId(), empleadoId, actividadId, date1, cantPasajeros, observaciones,
				opinionExito);

		return "redirect:/reserva/listaReserva";
	}

	@GetMapping("/listaReserva")
	public String listaReserva(ModelMap model) throws ErrorServices {
		model.addAttribute("titulo", "Listado de Reservas");
		List<Reserva> listaReserva;
		listaReserva = resServ.listaReserva();
		model.put("listaReserva", listaReserva);
		return "reservas/listaReserva";
	}

	@GetMapping("/editarReserva")
	public String edRes(ModelMap model) throws ErrorServices {
		model.addAttribute("titulo", "Edite una Reserva completa");
		return "modificarReserva.html";
	}

	// formaction="res"
	@PostMapping("/editarReserva")
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
	@PostMapping("/editarFecha")
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
	@PostMapping("/editarCantPasajeros")
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
	@PostMapping("/borrarReserva")
	public String borrarReserva(@RequestParam Integer id) throws ErrorServices {
		resServ.borrarReserva(id);
		return "redirect:/listaReserva";
	}

	@GetMapping("/buscar-res-pax")
	public String buscarPorPax(@RequestParam(required = false) String pasajero, Model model) {

		List<Reserva> lista = null;

		if (pasajero != null && !pasajero.isBlank()) {
			lista = resServ.buscarReservaNombrePasajero(pasajero);
			model.addAttribute("listaReserva", lista);
			model.addAttribute("titulo", "Listado de Reservas");
			return "reservas/listaReserva";
		}

		model.addAttribute("pax", "pax");
		model.addAttribute("titulo", "Gestur - Buscar Reserva");
		return "search";
	}

	@GetMapping("/buscar-noAuth")
	public String buscarNoAuth(@RequestParam(required = false) String documento, Model model) {

		List<Reserva> lista = null;

		if (documento != null && !documento.isBlank()) {
			lista = resServ.buscarReservaDocumentoPasajero(documento);
			model.addAttribute("listaReserva", lista);
			model.addAttribute("titulo", "Listado de Reservas");
			return "reservas/listaReserva";
		}

		model.addAttribute("pax", "pax");
		model.addAttribute("titulo", "Gestur - Buscar Reserva");
		return "search";
	}

	@GetMapping("/buscar-res-fecha")
	public String buscarPorFecha(@RequestParam(required = false) String desde,
			@RequestParam(required = false) String hasta, Model model) {

		List<Reserva> lista = null;
		Date date1 = null;
		Date date2 = null;

		if (desde != null && !desde.isBlank()) {

			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(desde);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			if (hasta != null && !hasta.isBlank()) {
				try {
					date2 = new SimpleDateFormat("yyyy-MM-dd").parse(hasta);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				try {
					date2 = new SimpleDateFormat("yyyy-MM-dd").parse(desde);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			lista = resServ.buscarReservaPorFecha(date1, date2);
			model.addAttribute("listaReserva", lista);
			model.addAttribute("titulo", "Listado de Reservas");

			return "reservas/listaReserva";
		}

		if (hasta != null && !hasta.isBlank()) {
			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(hasta);
				date2 = new SimpleDateFormat("yyyy-MM-dd").parse(hasta);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			lista = resServ.buscarReservaPorFecha(date1, date2);
			model.addAttribute("listaReserva", lista);
			model.addAttribute("titulo", "Listado de Reservas");

			return "reservas/listaReserva";
		}

		model.addAttribute("fecha", "fecha");
		model.addAttribute("titulo", "Gestur - Buscar Reserva");
		return "search";
	}

	@GetMapping("/buscar-res-actividad")
	public String buscarPorAct(@RequestParam(required = false) String actividad, Model model) {

		List<Reserva> lista = null;

		if (actividad != null && !actividad.isBlank()) {
			lista = resServ.buscarReservaActividad(actividad);

			model.addAttribute("listaReserva", lista);
			model.addAttribute("titulo", "Listado de Reservas");
			return "reservas/listaReserva";
		}

		model.addAttribute("actividades", acSev.listaActividad());
		model.addAttribute("act", "act");
		model.addAttribute("titulo", "Gestur - Buscar Reserva");
		return "search";
	}

	@GetMapping("/buscar")
	public String buscar(Model model) {
		model.addAttribute("titulo", "Buscar");
		return "search-menu";
	}

}
