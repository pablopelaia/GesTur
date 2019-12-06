/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestur.services;

import com.gestur.entities.Empleado;
import com.gestur.repository.EmpleadoRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {
    
    @Autowired
    private EmpleadoRepository er;

    @Transactional
    public void crearEmpleado(String nombre) {
        Empleado e= new Empleado();
       
        
            e.setNombre(nombre);
            er.save(e);

    }
    
}
