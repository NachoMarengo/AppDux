package dux.AppDux.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dux.AppDux.dto.EquipoDto;
import dux.AppDux.entity.Equipo;
import dux.AppDux.service.ServicioEquipo;
import dux.AppDux.util.EquipoUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;



@RestController
@RequestMapping("/equipos")
public class EquipoController {

	private static final Logger _traza = LogManager.getLogger(EquipoController.class);

    private final ServicioEquipo _servicioEquipo;
    
    public EquipoController(ServicioEquipo servicioEquipo) {
        this._servicioEquipo = servicioEquipo;
    }

    @Operation(
            summary = "Obtener todos los equipos", 
            description = "Devuelve una lista de todos los equipos registrados en la base de datos",
            security = @SecurityRequirement(name = "bearerAuth")
        )
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Lista de equipos obtenida con éxito"),
    	})
    @GetMapping("")
    public ResponseEntity<List<EquipoDto>> obtenerEquipos() {
        try {

            _traza.info("Se solicita info de todos los equipos");

        	List<Equipo> listaEquipos = _servicioEquipo.getListaEquipos();
            List<EquipoDto> listaEquiposDto = EquipoUtil.parserListaEquipoToEquipoDto(listaEquipos);

            _traza.info("Lista equipos enviados: "+listaEquiposDto);
            
            return ResponseEntity.ok(listaEquiposDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
    
    @Operation(
            summary = "Obtiene por ID", 
            description = "Devuelve un Equipo en particular por su Identificador",
            security = @SecurityRequirement(name = "bearerAuth")
        )
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Devuelve la información del equipo correspondiente al ID proporcionado"),
    	    @ApiResponse(responseCode = "404", description = "Error si el equipo no existe")
    	})
    @GetMapping("/{id}")
    public ResponseEntity<EquipoDto> obtenerEquipoPorId(@PathVariable Long id) {
    	
        _traza.info("Se solicita info del equipo id("+id+")");

        Equipo equipo = _servicioEquipo.getEquipoPorId(id);
        EquipoDto equipoDto = EquipoUtil.parserEquipoToEquipoDto(equipo);

        _traza.info("Equipo enviado: "+equipoDto);

        return ResponseEntity.ok(equipoDto);

    }
    
    @Operation(
            summary = "Obtiene por nombre", 
            description = "Devuelve la lista de equipos cuyos nombres contienen el valor proporcionado en el parámetro de búsqueda",
            security = @SecurityRequirement(name = "bearerAuth")
        )
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Devuelve la información de los equipos cuyos nombres coincidan con el ingresado"),
    	})
    @GetMapping("/buscar")
    public ResponseEntity<List<EquipoDto>> buscarEquiposPorNombre(@RequestParam String nombre) {
    	
        _traza.info("Se solicita info de los equipos cuyp nombre: "+nombre);
    	
        List<Equipo> equipos = _servicioEquipo.getEquipoPorNombre(nombre);
        List<EquipoDto> listaEquipoDtos = EquipoUtil.parserListaEquipoToEquipoDto(equipos);
        
        _traza.info("Lista equipos enviados: "+listaEquipoDtos);
        
        return ResponseEntity.ok(listaEquipoDtos);
    }
    
    @Operation(
            summary = "Alta nuevo equipo", 
            description = "Genera un nuevo equipo en base de datos",
            security = @SecurityRequirement(name = "bearerAuth")
        )
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "201", description = "Devuelve la información del equipo correspondiente con su respectivo ID"),
    	    @ApiResponse(responseCode = "400", description = "La solicitud es invalida")
    })
    @PostMapping("")
    public ResponseEntity<EquipoDto> addEquipo(@RequestBody Equipo equipo){
    	
        _traza.info("Solicitud de registro nuevo equipo");        
                        
        Equipo equipoGuardado = _servicioEquipo.addEquipo(equipo);

    	_traza.info("Se registra nuevo equipo "+equipoGuardado);

        EquipoDto equipoDto = EquipoUtil.parserEquipoToEquipoDto(equipoGuardado);

        return ResponseEntity.status(HttpStatus.CREATED).body(equipoDto);
    	
    }
    
    @Operation(
            summary = "Actualiza equipo", 
            description = "Genera una actualizacion de los datos del equipo",	
            security = @SecurityRequirement(name = "bearerAuth")
        )
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Devuelve la información actualizada del equipo. "),
    	    @ApiResponse(responseCode = "404", description = "Si el equipo no existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EquipoDto> updateEquipo(@PathVariable Long id, @RequestBody Equipo equipo) {
        _traza.info("Solicitud de actualizar equipo con id (" + id + ")");
        
        Equipo equipoActualizado = _servicioEquipo.updateEquipo(id, equipo);
        EquipoDto equipoDto = EquipoUtil.parserEquipoToEquipoDto(equipoActualizado);
        
        return ResponseEntity.ok(equipoDto);
    }

    
    @Operation(
            summary = "Datos del nuevo equipo", 
            description = "Genera un nuevo equipo en base de datos",
            security = @SecurityRequirement(name = "bearerAuth")
        )
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "204", description = "Elimina el equipo encontrado"),
    	    @ApiResponse(responseCode = "404", description = "Si el equipo no existe")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<EquipoDto> deleteEquipo(@PathVariable Long id){
    	
        _traza.info("Solicitud de registro nuevo equipo");        
                        
        boolean eliminado = _servicioEquipo.deleteEquipo(id);
        if(eliminado)
        	_traza.info("Eliminado elemento con id ("+id+")");        
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	
    }
    
}
