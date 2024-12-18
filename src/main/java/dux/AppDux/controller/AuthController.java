package dux.AppDux.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dux.AppDux.dto.LoginDto;
import dux.AppDux.dto.TokenDto;
import dux.AppDux.service.ServicioAuth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final ServicioAuth servicioAuth;
    
    public static final String _AUTH = "/auth" ;
    public static final String _LOGIN = "/login" ;
    
    public AuthController(ServicioAuth servicioAuth) {
        this.servicioAuth = servicioAuth;
    }
	
    @Operation(
            summary = "Autenticarse", 
            description = "Devuelve un token para poder consumer los servicios",
            security = @SecurityRequirement(name = "bearerAuth")
        )
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Token obtenido"),
    	    @ApiResponse(responseCode = "401", description = "Usuario o contraseña incorrecta"),

    	})
	@PostMapping(_LOGIN)
	public ResponseEntity<TokenDto> autenticacar(@RequestBody final LoginDto loginDto){
		String token = 	servicioAuth.autenticarUsuario(loginDto.getUsername(), loginDto.getPassword());
        return ResponseEntity.ok(new TokenDto(token));
	}
}
