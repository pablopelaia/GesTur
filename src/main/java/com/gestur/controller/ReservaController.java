package com.gestur.controller;

import com.gestur.entities.Reserva;
import com.gestur.exceptions.ErrorServices;
import com.gestur.services.ReservaService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService resServ;

    @GetMapping({"/", ""})
    public String paginaPpal(ModelMap model) {
        model.addAttribute("titulo", "Página Principal de Reservas");
        return "reserva.html";
    }

    @GetMapping("/crearReserva")
    public String creRes(ModelMap model) {
        model.addAttribute("titulo", "Crear Reserva");
        return "crearReserva.html";
    }

    //formaction="crear" u otro que quiera Cocco
    @GetMapping("/crearReserva/crear")
    public String crearReserva(@RequestParam String pasajeroId, @RequestParam String empleadoId, @RequestParam String actividadId, @RequestParam Date fechaActividad, @RequestParam Integer cantPasajeros, @RequestParam(required = false) String observaciones, @RequestParam(required = false) String opinionExito, ModelMap model, String exito) throws ErrorServices {
        resServ.crearReserva(pasajeroId, empleadoId, actividadId, fechaActividad, cantPasajeros, observaciones, opinionExito);
        return "redirect:/listaReserva";
    }

    @GetMapping("/listaReserva")
    public String listaReserva(@RequestParam(required = false) String nombre, ModelMap model) throws ErrorServices {
        model.addAttribute("titulo", "Listado de Reservas");
        List<Reserva> listaReserva;
        if (nombre == null || nombre.isEmpty()) {
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

    //formaction="res" u otro que quiera Cocco
    @GetMapping("/editarReserva/res")
    public String editarReserva(@RequestParam Date fechaActividad, @RequestParam Integer cantPasajeros, @RequestParam Integer id, String exito, ModelMap model) throws ErrorServices {
        resServ.modificarReserva(fechaActividad, cantPasajeros, id);
        return "redirect:/listaReserva";
    }

    @GetMapping("/editarFecha")
    public String edFecha(ModelMap model) throws ErrorServices {
        model.addAttribute("titulo", "Edite la Fecha");
        return "editarFecha.html";
    }

    //formaction="fecha" u otro que quiera Cocco
    @GetMapping("/editarFecha/fecha")
    public String editarFecha(@RequestParam Date fechaActividad, @RequestParam Integer id, String exito, ModelMap model) throws ErrorServices {
        resServ.modificarReserva(fechaActividad, id);
        return "redirect:/listaReserva";
    }

    @GetMapping("/editarCantPasajeros")
    public String edPas(ModelMap model) throws ErrorServices {
        model.addAttribute("titulo", "Edite la Cantidad de Pasajeros");
        return "editarCantPas.html";
    }

    //formaction="cantPas" u otro que quiera Cocco
    @GetMapping("/editarCantPasajeros/cantPas")
    public String editarCantPasajeros(@RequestParam Integer cantPasajeros, @RequestParam Integer id, String exito, ModelMap model) throws ErrorServices {
        resServ.modificarReserva(cantPasajeros, id);
        return "redirect:/listaReserva";
    }

    @GetMapping("/borrarReserva")
    public String borRes(ModelMap model) {
        model.addAttribute("titulo", "Elimine una Reserva");
        return "borrarReserva.html";
    }

    //formaction="borrar" u otro que quiera Cocco
    @GetMapping("/borrarReserva/borrar")
    public String borrarReserva(@RequestParam Integer id) throws ErrorServices {
        resServ.borrarReserva(id);
        return "redirect:/listaReserva";
    }
    //Falta un kilo de cosas, por ejemplo "Try & Catch" todas las variables, y encima falta los controllers de las 3 clases restantes. GG.
}
