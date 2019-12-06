/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestur.services;

import com.gestur.entities.Empleado;
import com.gestur.exceptions.ErrorServices;
import com.gestur.repository.EmpleadoRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {
    
    @Autowired
    private EmpleadoRepository er;

    @Transactional
    public void crearEmpleado(String nombre) throws ErrorServices {
        Empleado e= new Empleado();
        validar(nombre);
        
            e.setNombre(nombre);
            er.save(e);

    }
    
    public void validar(String nombre) throws ErrorServices{
        if(nombre.isEmpty() || nombre == null){
        throw new ErrorServices("El nombre no puede ir nulo.");
        }
    }
}
