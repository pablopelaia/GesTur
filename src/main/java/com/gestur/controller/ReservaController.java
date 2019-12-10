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
        model.addAttribute("titulo", "Página principal de Reservas");
        return "reserva.html";
    }

    @GetMapping("/crearReserva")
    public String asdf(ModelMap model) {
        model.addAttribute("titulo", "Crear Reserva");
        return "crearReserva.html";
    }

    //formaction="crear" u otro que quiera cocco
    @GetMapping("/crearReserva/crear")
    public String crearReserva(@RequestParam(required = false) String pasajeroId, @RequestParam(required = false) String empleadoId, @RequestParam(required = false) String actividadId, @RequestParam(required = false) Date fechaActividad, @RequestParam(required = false) Integer cantPasajeros, @RequestParam(required = false) String observaciones, @RequestParam(required = false) String opinionExito, ModelMap model, String exito) throws ErrorServices {
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

    @GetMapping("/modificarReserva")
    public String modificar(ModelMap model) throws ErrorServices {
        model.addAttribute("titulo", "Modifique una Reserva completa");
        return "modificarReserva.html";
    }

    @GetMapping("/modificarFecha")
    public String modFecha(ModelMap model) throws ErrorServices {
        model.addAttribute("titulo", "Modifique la Fecha");
        return "modificarFecha.html";
    }

    @GetMapping("/modificarCantPas")
    public String modPas(ModelMap model) throws ErrorServices {
        model.addAttribute("titulo", "Modifique la Cantidad de Pasajeros");
        return "modificarCantPas.html";
    }

    //formaction="res" u otro que quiera cocco
    @GetMapping("/modificarReserva/res")
    public String modificarReserva(@RequestParam Date fechaActividad, @RequestParam Integer cantPasajeros, @RequestParam(required = false) Integer id, String exito, ModelMap model) throws ErrorServices {
        resServ.modificarReserva(fechaActividad, cantPasajeros, id);
        return "redirect:/listaReserva";
    }

    //formaction="fecha" u otro que quiera cocco
    @GetMapping("/modificarFecha/fecha")
    public String modificarFecha(@RequestParam Date fechaActividad, @RequestParam(required = false) Integer id, String exito, ModelMap model) throws ErrorServices {
        resServ.modificarReserva(fechaActividad, id);
        return "redirect:/listaReserva";
    }

    //formaction="cantPas" u otro que quiera cocco
    @GetMapping("/modificarCantPas/cantPas")
    public String modificarCantPasajeros(@RequestParam Integer cantPasajeros, @RequestParam(required = false) Integer id, String exito, ModelMap model) throws ErrorServices {
        resServ.modificarReserva(cantPasajeros, id);
        return "redirect:/listaReserva";
    }

    @GetMapping("/borrarReserva")
    public String borRes(ModelMap model) {
        model.addAttribute("titulo", "Elimine una Reserva");
        return "borrarReserva.html";
    }

    //formaction="borrar" u otro que quiera cocco
    @GetMapping("/borrarReserva/borrar")
    public String borrarReserva(@RequestParam Integer id) throws ErrorServices {
        resServ.borrarReserva(id);
        return "redirect:/listaReserva";
    }
    //Falta un kilo de cosas, por ejemplo "Try y Catch" todas las variables, y encima falta los controllers de las 3 clases restantes. GG.
}
