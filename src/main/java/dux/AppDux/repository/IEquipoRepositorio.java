package dux.AppDux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dux.AppDux.entity.Equipo;

@Repository
public interface IEquipoRepositorio extends JpaRepository<Equipo, Integer> {
 
	
}
