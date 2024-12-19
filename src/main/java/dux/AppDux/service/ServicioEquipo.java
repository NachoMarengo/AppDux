package dux.AppDux.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dux.AppDux.exception.Excepciones;
import dux.AppDux.exception.Excepciones.ExcepcionAuth;
import dux.AppDux.exception.Excepciones.ExcepcionNotFound;
import dux.AppDux.repository.EquipoRepositorio;
import dux.AppDux.util.EquipoUtil;
import dux.AppDux.entity.Equipo;

@Service
public class ServicioEquipo implements IServicioEquipo {

	private final EquipoRepositorio equipoRepositorio;
	
	@Autowired
	public ServicioEquipo(EquipoRepositorio equipoRepositorio) {
		this.equipoRepositorio = equipoRepositorio;
	}
	
    public List<Equipo> getListaEquipos() {
        return equipoRepositorio.findAll();
    }
    
    public Equipo getEquipoPorId(Long id){
    	Optional<Equipo> equipoOptional =  equipoRepositorio.findById(id);
    	if(equipoOptional.isPresent())
    		return equipoOptional.get();
    	else
	        throw new Excepciones.ExcepcionNotFound("Equipo no encontrado");
    }
    
    public List<Equipo> getEquipoPorNombre(String nombre){
    	List<Equipo> equipos = equipoRepositorio.findByNombreContainingIgnoreCase(nombre);
    	return equipos;
    }
    
    public List<String> getListaNombresEquiposExistentes(String nombre){
    	List<Equipo> equiposExistentes = getEquipoPorNombre(nombre);
    	List<String> nombresExistentes = equiposExistentes.stream().map(obj -> obj.getNombre()).toList();
    	return nombresExistentes;
    }
    
    public Equipo addEquipo(Equipo equipo) {
    	
    	String msjError = "La solicitud es invalida";
    	    	
    	boolean validado = EquipoUtil.validarDatosCompletos(equipo);
    		
    	if(!validado)
	        throw new Excepciones.ExcepcionBadRequest(msjError);
    	
		List<Equipo> nombresExistentes = getEquipoPorNombre(equipo.getNombre());
	
    	validado = EquipoUtil.validarNombreUnico(equipo, nombresExistentes);
    	
    	if(!validado)
	        throw new Excepciones.ExcepcionBadRequest(msjError);
    	
    	equipo.setId(findMaxId()+1);
    	
        return equipoRepositorio.save(equipo);
        
    }
    
    public Equipo updateEquipo(Long id, Equipo equipo)  {
    	
        if (equipoRepositorio.existsById(id)) {
        	equipo.setId(id);
        	if(EquipoUtil.validarDatosCompletos(equipo))
            	return equipoRepositorio.save(equipo);
        	else
    	        throw new Excepciones.ExcepcionBadRequest("La solicitud es invalida");
        	
        } 
        
        throw new Excepciones.ExcepcionNotFound("Equipo no encontrado");
        
    }
    
    public boolean deleteEquipo(Long id) {
        if (equipoRepositorio.existsById(id)) {
            equipoRepositorio.deleteById(id);
            return true;
        }else
        	throw new Excepciones.ExcepcionNotFound("Equipo no encontrado");
    }

	public Long findMaxId() {
		Long id= equipoRepositorio.findMaxId();
	    return (id != null) ? id : 0L;
	}

}
