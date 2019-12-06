package com.gestur.controller;

import com.gestur.exceptions.ErrorServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"", "", ""})
public class ReservaController {

    @GetMapping({"", ""})
    public String a()throws ErrorServices {
        return "";
    }

    @GetMapping({"", ""})
    public String b()throws ErrorServices {
        return "";
    }

}
