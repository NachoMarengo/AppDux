package dux.AppDux.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dux.AppDux.entity.Equipo;

public interface EquipoRepositorio extends JpaRepository<Equipo, Long>{

    List<Equipo> findByNombreContainingIgnoreCase(String nombre);
    
    @Query("SELECT MAX(e.id) FROM Equipo e")
    Long findMaxId();

}