package dux.AppDux.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dux.AppDux.dto.EquipoDto;
import dux.AppDux.entity.Equipo;
import dux.AppDux.service.ServicioEquipo;

@Service
public class EquipoUtil {
	
	/**
	 * Rutina de parseo Equipo a EquipoDto
	 * @param equipo
	 * @return
	 */
	public static EquipoDto parserEquipoToEquipoDto(Equipo equipo) {
		
		EquipoDto equipoDto = new EquipoDto();
		
		equipoDto.setId(equipo.getId());
		equipoDto.setNombre(equipo.getNombre());
		equipoDto.setLiga(equipo.getLiga());
		equipoDto.setPais(equipo.getPais());
		
		return equipoDto;
	}
	
	/**
	 * Rutina de parseo de listaEquipo a listaEquipoDto
	 * @param listaEquipo
	 * @return
	 */
	public static List<EquipoDto> parserListaEquipoToEquipoDto(List<Equipo> listaEquipo) {
		List<EquipoDto> listaEquipoDto = new ArrayList<EquipoDto>();
		
		for(Equipo equipo : listaEquipo) {
			listaEquipoDto.add(parserEquipoToEquipoDto(equipo));
		}

		return listaEquipoDto;
	}
	
	/**
	 * Validar que el objeto equipo contenga la informacion necesaria
	 * @param equipo
	 * @return
	 */
	public static boolean validarDatosCompletos(Equipo equipo) {

		return esTextoValido(equipo.getLiga()) &&
				esTextoValido(equipo.getNombre()) &&
				esTextoValido(equipo.getPais());
	}

	private static boolean esTextoValido(String texto) {
		return texto != null && !texto.trim().isEmpty();
	}
	
	/**
	 *  Validar que el objeto equipo no se llame igual a alguno existente
	 * @param nombreNuevoEquipo
	 * @param nombresExistentes
	 * @return
	 */
	public static boolean validarEquipo(Equipo equipo, List<Equipo> nombresExistentes) {
		
		for (Equipo nombreExistente : nombresExistentes) {
			if (nombreExistente.getNombre().equals(equipo.getNombre()))
				return false;
		}

		return true;
	}
}
