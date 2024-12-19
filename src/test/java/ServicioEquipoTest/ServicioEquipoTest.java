package ServicioEquipoTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dux.AppDux.entity.Equipo;
import dux.AppDux.exception.Excepciones;
import dux.AppDux.repository.EquipoRepositorio;
import dux.AppDux.service.ServicioEquipo;

class ServicioEquipoTest {

	private static final Logger _traza = LogManager.getLogger(ServicioEquipoTest.class);

	@Mock
	private EquipoRepositorio equipoRepositorio;

	@InjectMocks
	private ServicioEquipo servicioEquipo;

	@BeforeEach
	void setPrueba() {;
		//metemos un espacio
		_traza.info("");
		MockitoAnnotations.openMocks(this);
	}

	private Equipo crearEquipo(String nombre, String pais, String liga) {
	    Equipo equipo = new Equipo();
	    equipo.setNombre(nombre);
	    equipo.setPais(pais);
	    equipo.setLiga(liga);
	    return equipo;
	}
	
	/**
	 * Se espera que se permita generar correctamente el equipo e insertar en bbdd 
	 */
	@Test
	void addEquipo_CasoExitoso() {
		_traza.info(" INICIO TEST INGRESO EQUIPO - CASO EXITOSO");

		Equipo equipo = crearEquipo("Boca", "Argentina", "Primera Division");


		when(equipoRepositorio.save(any(Equipo.class)))
		.thenAnswer(invocation -> invocation.getArgument(0));

		Equipo equipoGuardado = servicioEquipo.addEquipo(equipo);

		assertNotNull(equipoGuardado, "El equipo guardado no debe ser null");

		Assertions.assertThat(equipoGuardado.getId() <= 0);

		_traza.info("TEST INGRESO EQUIPO EXITOSO, ingresado equipo " + equipoGuardado);
	}
	
	/**
	 * Se espera que el equipo con el nombre repetido no sea aceptado, debe lanzar error
	 */
	@Test
	void addEquipo_CasoNombreRepetido() {

		_traza.info(" INICIO TEST INGRESO EQUIPO - CASO NOMBRE REPETIDO");

		Equipo equipoExistente = crearEquipo("Boca", "Argentina", "Primera Division");

		Equipo equipoNuevo = crearEquipo("Boca", "Argentina", "Primera Division");

		when(equipoRepositorio.findByNombreContainingIgnoreCase("Boca")).thenReturn(List.of(equipoExistente));

		Exception ex = assertThrows(Excepciones.ExcepcionBadRequest.class, () -> {
			servicioEquipo.addEquipo(equipoNuevo);
		});

		assertEquals("La solicitud es invalida", ex.getMessage());

		_traza.info("TEST INGRESO EQUIPO EXITOSO, solicitud invalida," + ex.getMessage() + "Objeto: " + equipoNuevo);

	}
	
	/**
	 * Se espera que el equipo con el nombre repetido no sea aceptado, debe lanzar error
	 */
	@Test
    void addEquipo_CasoNombreVacio() {
		
		_traza.info(" INICIO TEST INGRESO EQUIPO - CASO NOMBRE VACIO");
		
        Equipo equipo  = crearEquipo("", "Argentina", "Primera Division");

        Exception ex = assertThrows(Excepciones.ExcepcionBadRequest.class, () -> {
            servicioEquipo.addEquipo(equipo);
        });

        assertEquals("La solicitud es invalida", ex.getMessage());

        _traza.info("TEST INGRESO EQUIPO EXITOSO, solicitud invalida por nombre vacio: " + ex.getMessage() + "Objeto: "+equipo);

	}
	
	/**
	 * Se espera que el equipo con el Pais repetido no sea aceptado, debe lanzar error
	 */
	@Test
    void addEquipo_CasoPaisVacio() {
		
		_traza.info(" INICIO TEST INGRESO EQUIPO - CASO PAIS VACIO");
		
        Equipo equipo =   crearEquipo("Boca", "", "Primera Division");

        Exception ex = assertThrows(Excepciones.ExcepcionBadRequest.class, () -> {
            servicioEquipo.addEquipo(equipo);
        });

        assertEquals("La solicitud es invalida", ex.getMessage());

        _traza.info("TEST INGRESO EQUIPO EXITOSO, solicitud invalid por pais vacio: " + ex.getMessage()+ "Objeto: "+equipo);

	}

	
	/**
	 * Se espera que el equipo con el Liga repetido no sea aceptado, debe lanzar error
	 */
	@Test
    void addEquipo_CasoLigaVacio() {
		
		_traza.info(" INICIO TEST INGRESO EQUIPO - CASO LIGA VACIA");
		
        Equipo equipo  = crearEquipo("Boca", "Argentina", "");

        Exception ex = assertThrows(Excepciones.ExcepcionBadRequest.class, () -> {
            servicioEquipo.addEquipo(equipo);
        });

        assertEquals("La solicitud es invalida", ex.getMessage());

        _traza.info("TEST INGRESO EQUIPO EXITOSO, solicitud invalid por LIGA VACIA: " + ex.getMessage()+ "Objeto: "+equipo);

	}
	
	/**
	 * Se espera que el equipo con nombre null no sea aceptado, debe lanzar error
	 */
	@Test
	void addEquipo_CasoNombreNull() {
	    _traza.info("INICIO TEST INGRESO EQUIPO - CASO NOMBRE NULL");

	    Equipo equipo = crearEquipo(null, "Argentina", "Primera Division");

	    Exception ex = assertThrows(Excepciones.ExcepcionBadRequest.class, () -> {
	        servicioEquipo.addEquipo(equipo);
	    });

	    assertEquals("La solicitud es invalida", ex.getMessage());

	    _traza.info("TEST INGRESO EQUIPO EXITOSO, solicitud invalida por nombre null: " + ex.getMessage() + "Objeto: "+equipo);
	}
	
	/**
	 * Se espera que el equipo con pais null no sea aceptado, debe lanzar error
	 */
	@Test
	void addEquipo_CasoPaisNull() {
	    _traza.info("INICIO TEST INGRESO EQUIPO - CASO PAIS Null");

	    Equipo equipo = crearEquipo("Boca", null, "Primera Division");

	    Exception ex = assertThrows(Excepciones.ExcepcionBadRequest.class, () -> {
	        servicioEquipo.addEquipo(equipo);
	    });

	    assertEquals("La solicitud es invalida", ex.getMessage());

	    _traza.info("TEST INGRESO EQUIPO EXITOSO, solicitud invalida por pais Null: " + ex.getMessage() + "Objeto: "+equipo);
	}
	
	/**
	 * Se espera que el equipo con pais Null no sea aceptado, debe lanzar error
	 */
	@Test
	void addEquipo_CasoLigaNull() {
	    _traza.info("INICIO TEST INGRESO EQUIPO - CASO liga Null");

	    Equipo equipo = crearEquipo("Boca", "Argentina",null);

	    Exception ex = assertThrows(Excepciones.ExcepcionBadRequest.class, () -> {
	        servicioEquipo.addEquipo(equipo);
	    });

	    assertEquals("La solicitud es invalida", ex.getMessage());

	    _traza.info("TEST INGRESO EQUIPO EXITOSO, solicitud invalida por pais Null: " + ex.getMessage() + "Objeto: "+equipo);
	}
	
	/**
	 * Se espera que el equipo con pais vacio no sea aceptado, debe lanzar error
	 */
	@Test
	void addEquipo_CasoEquipoNull() {
	    _traza.info("INICIO TEST INGRESO EQUIPO - CASO Equipo Null");

	    Equipo equipo = null;

	    Exception ex = assertThrows(Excepciones.ExcepcionBadRequest.class, () -> {
	        servicioEquipo.addEquipo(equipo);
	    });

	    assertEquals("La solicitud es invalida", ex.getMessage());

	    _traza.info("TEST INGRESO EQUIPO EXITOSO, solicitud invalida por equipo null " + ex.getMessage() + "Objeto: "+equipo);
	}

	
}
