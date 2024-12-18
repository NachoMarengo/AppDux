package dux.AppDux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dux.AppDux.exception.Excepciones.ExcepcionAuth;
import dux.AppDux.util.JwtUtil;

@Service
public class ServicioAuth {
	
	@Autowired
	private final JwtUtil jwtUtil;
	
	@Value("${default.usuario}")
    private String _USUARIO;
	@Value("${default.pass}")
    private String _PASS;
	
	public ServicioAuth( JwtUtil jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
	}
	
	/**
	 * Rutina de autenticacion
	 * @param usuario
	 * @param pass
	 * @return
	 */
	public String autenticarUsuario (String usuario, String pass) {

		if(usuario.equals(_USUARIO) && pass.equals(_PASS)) {
			return jwtUtil.generarToken(usuario);
		}else {
	        throw new ExcepcionAuth("Usuario o contraseña incorrecta");
		}
	}

}
