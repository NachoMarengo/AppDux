 package dux.AppDux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;


@Configuration
@OpenAPIDefinition(
    info = @Info(
        title  = "AppDux API",
        version = "1.0",
        description = "API para evaluación técnica.  "
        		+ "Se solicitó la construcción de una API que permita la "
        		+ "manipulación de datos almacenados en una base de datos H2. "
        		+ "La mism debe contar con una serie de endpoints que "
        		+ "posibiliten la ejecución de operaciones CRUD sobre la "
        		+ "base de datos.",
        contact = @Contact(
            name = "Ignacio A. Marengo",
            email = "ignacioo.marengo@gmail.com"
        ),
        license = @License(name = "Licencia de prueba")
    ),
    servers = @Server(
        description = "Servidor local",
        url = "http://localhost:9090"
    ),
    security = {
    		@SecurityRequirement(
    				name = "bearerAuth"
    				)
    }
)
@SecurityScheme(
		name="Autenticación Bearer",
		description =  "Se generara un token el cual permitira a las demas "
				+ "consultas poder consumir los servicios de la API",
		scheme = "bearer",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		in =SecuritySchemeIn.HEADER
		
		)
public class OpenApiConfig {
}