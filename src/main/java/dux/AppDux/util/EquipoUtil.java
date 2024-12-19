package dux.AppDux.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		
        if (equipo == null)
            return false;
       
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
	public static boolean validarNombreUnico(Equipo equipo, List<Equipo> equiposExistentes) {
	    if (equipo == null || equiposExistentes == null) {
	        throw new IllegalArgumentException("El equipo y la lista de equipos existentes no pueden ser nulos.");
	    }

	    Optional<Equipo> equipoExistente = equiposExistentes.stream()
	        .filter(e -> e.getNombre() != null && e.getNombre().equalsIgnoreCase(equipo.getNombre()))
	        .findFirst();

	    return equipoExistente.isEmpty();  // Retorna true si no se encuentra ningún equipo con el mismo nombre
	}

    
	
	
	
}
