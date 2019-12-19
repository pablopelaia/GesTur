package com.gestur.repository;

import com.gestur.entities.Reserva;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

	@Query("SELECT c FROM Reserva c ORDER BY c.fechaActividad ASC")
	public List<Reserva> listaReserva();

	@Query("SELECT c FROM Reserva c WHERE c.actividad.id = :id")
	public List<Reserva> reservaPorActividad(@Param("id") String id);

	@Query("SELECT c FROM Reserva c WHERE concat(c.pasajero.nombre, ' ', c.pasajero.apellido) LIKE ?1")
	public List<Reserva> reservaPorNombrePasajero(@Param("pasajero") String pasajero);

	// Modificar todo al mismo tiempo
	@Modifying
	@Query("UPDATE FROM Reserva c SET fechaActividad=:fechaActividad,cantPasajeros=:cantPas WHERE c.id=:id")
	public void modificarReserva(@Param("fechaActividad") Date fechaActividad, @Param("cantPas") Integer cantPasajeros,
			@Param("id") Integer id);

	@Modifying
	@Query("UPDATE FROM Reserva c SET fechaActividad=:fechaActividad WHERE c.id=:id")
	public void modificarFecha(@Param("fechaActividad") Date fechaActividad, @Param("id") Integer id);

	@Modifying
	@Query("UPDATE FROM Reserva c SET cantPasajeros=:cantPas WHERE c.id=:id")
	public void modificarPasajeros(@Param("cantPas") Integer cantPasajeros, @Param("id") Integer id);

	public List<Reserva> findByFechaActividadBetween(String desde, String hasta);

	@Query("select r from Reserva r where r.pasajero.documento = ?1")
	public List<Reserva> reservaPorDocumentoPasajero(String documento);

}
