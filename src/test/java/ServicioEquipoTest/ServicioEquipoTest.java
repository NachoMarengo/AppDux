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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
	void setPrueba() {
		// metemos un espacio
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

		when(equipoRepositorio.save(any(Equipo.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Equipo equipoGuardado = servicioEquipo.addEquipo(equipo);

		assertNotNull(equipoGuardado, "El equipo guardado no debe ser null");

		Assertions.assertThat(equipoGuardado.getId() <= 0);

		_traza.info("TEST INGRESO EQUIPO EXITOSO, ingresado equipo " + equipoGuardado);
	}

	/**
	 * Se espera que el equipo con el nombre repetido no sea aceptado, debe lanzar
	 * error
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
	 * Validaremos que sucede si se da el caso de que alguno de los parametros sea
	 * ingresado vacio o null
	 */
	@ParameterizedTest
	@CsvSource({ "'', Argentina, Primera Division",
				"Boca, '', Primera Division",
				"Boca, Argentina, ''",
				"null, Argentina, Primera Division",
				"Boca, null, Primera Division",
				"Boca, Argentina, null" })
	void addEquipo_CasosInvalidos(String nombre, String pais, String liga) {
		_traza.info("INICIO TEST INGRESO EQUIPO - CASOS INVALIDOS");

		nombre = "null".equals(nombre) ? null : nombre;
		pais = "null".equals(pais) ? null : pais;
		liga = "null".equals(liga) ? null : liga;

		final Equipo equipo = crearEquipo(nombre, pais, liga);

		Exception ex = assertThrows(Excepciones.ExcepcionBadRequest.class, () -> {
			servicioEquipo.addEquipo(equipo);
		});

		assertEquals("La solicitud es invalida", ex.getMessage());

		_traza.info("TEST INGRESO EQUIPO EXITOSO, solicitud invalida: " + ex.getMessage() + " Objeto: " + equipo);

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

		_traza.info("TEST INGRESO EQUIPO EXITOSO, solicitud invalida por equipo null " + ex.getMessage() + "Objeto: "
				+ equipo);
	}

}
