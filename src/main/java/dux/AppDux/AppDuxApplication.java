package dux.AppDux;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



@SpringBootApplication
public class AppDuxApplication extends SpringBootServletInitializer{
	
	private static final Logger _traza = LogManager.getLogger(AppDuxApplication.class);

	@Autowired
	IServicioEquipo iServicioEquipo;
	
	public static void main(String[] args) {
		SpringApplication.run(AppDuxApplication.class, args);
		
		_traza.info("");
		_traza.info("");
		_traza.info("******************************************************");
		_traza.info("         Inicio AppDuxApplication exitoso            ");
		_traza.info("******************************************************");
		_traza.info("");
		_traza.info("");
		
	}
			
	public void run(String... args) throws Exception{
		Equipo equipo = new Equipo();
		iServicioEquipo.addEquipo(equipo);
	}
}
