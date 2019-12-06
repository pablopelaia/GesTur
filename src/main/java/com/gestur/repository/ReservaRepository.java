package com.gestur.repository;

import com.gestur.entities.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Integer>{

    
    //Todas las Reservas por nombre de actividad y nombre de pasajero(Expandible a mostrar las fechas de reserva y/o actividad) (Sin parametrizar);
    //Esta lista deberia imprimirse en un PDF      
    @Query("SELECT d.nombre,e.nombre,c.fechaCarga,c.fechaActividad,e.documento,d.lugar FROM Reserva c,Actividad d,Pasajero e WHERE c.actividad_id=d.id AND c.pasajero_id=e.id ORDER BY d.nombre")
    public List<Object> reservaPorActividad();
 
    //Reservas por nombre de actividad , nombre de pasajero fechas de reserva y/o actividad (Parametro nombre de actividad);
    //Esta lista deberia imprimirse en un PDF
    @Query("SELECT d.nombre,e.nombre,c.fechaCarga,c.fechaActividad,e.documento,d.lugar FROM Reserva c,Actividad d,Pasajero e WHERE c.actividad_id=d.id AND c.pasajero_id=e.id AND d.nombre LIKE %:nombre% ORDER BY d.nombre")
    public List<Object> reservaPorActividad(@Param("nombre")String nombre);
        
    @Modifying
    @Query("UPDATE FROM Reserva c SET fechaActividad=:fechaActividad,")
    public void modificarReserva(@Param("fechaActividad")String fecha,@Param(""),@Param(""));
}
