package com.gestur.controller;

import com.gestur.entities.Pasajero;
import com.gestur.entities.Reserva;
import com.gestur.exceptions.ErrorServices;
import com.gestur.repository.ReservaRepository;
import com.gestur.services.ActividadService;
import com.gestur.services.EmpleadoService;
import com.gestur.services.PasajeroService;
import com.gestur.services.ReservaService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/reserva")
@SessionAttributes("reserva")
public class ReservaController {

	@Autowired
	private ReservaService resServ;

	@Autowired
	private ReservaRepository resrep;

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

//		Date date1 = null;
//		try {
//			System.out.println(fechaActividad);
//			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fechaActividad);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}

		resServ.crearReserva(pasajero.getId(), empleadoId, actividadId, fechaActividad, cantPasajeros, observaciones,
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

	@GetMapping("/editarReserva/{id}")
	public String edRes(@PathVariable(value = "id") Integer id, Model model) throws ErrorServices {
		model.addAttribute("reserva", resServ.buscarReservaPorId(id));
		model.addAttribute("titulo", "Editar Reserva");
		return "reservas/editar";
	}

	@PostMapping("/editarReserva")
	public String edReserva(@Valid Reserva reserva, BindingResult result, Model model, SessionStatus status)
			throws ErrorServices {
		if (result.hasErrors()) {
//			System.out.println(result.);
			model.addAttribute("titulo", "Editar Reserva");
			return "reservas/editar";
		}

		resrep.save(reserva);
		status.setComplete();
		return "redirect:/reserva/listaReserva";
	}

	@GetMapping("/editarFecha")
	public String edFecha(ModelMap model) throws ErrorServices {
		model.addAttribute("titulo", "Edite la Fecha");
		return "editarFecha.html";
	}

//	// formaction="fecha"
//	@PostMapping("/editarFecha")
//	public String editarFecha(@RequestParam Date fechaActividad, @RequestParam Integer id, String exito, ModelMap model)
//			throws ErrorServices {
//		resServ.modificarReserva(fechaActividad, id);
//		return "redirect:/listaReserva";
//	}

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

	// formaction="borrar"
	@GetMapping("/borrarReserva/{id}")
	public String borrarReserva(@PathVariable(value = "id") Integer id) throws ErrorServices {
		System.out.println(id);
		resServ.borrarReserva(id);
		return "redirect:/reserva/listaReserva";
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

		if (desde != null && !desde.isBlank()) {

			if (hasta == null || hasta.isBlank()) {
				hasta = desde;
			}

			lista = resServ.buscarReservaPorFecha(desde, hasta);
			model.addAttribute("listaReserva", lista);
			model.addAttribute("titulo", "Listado de Reservas");

			return "reservas/listaReserva";
		}

		if (hasta != null && !hasta.isBlank()) {

			desde = hasta;

			lista = resServ.buscarReservaPorFecha(desde, hasta);
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
