package dux.AppDux.service;

import org.springframework.stereotype.Service;

import dux.AppDux.entity.Equipo;

@Service
public interface IServicioEquipo {

	Equipo addEquipo (Equipo equipo);
	
}
