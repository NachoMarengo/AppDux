Proyecto API CRUD equipos de futbol - Examen tecnico dux software

Este proyecto implementa una API RESTful para gestionar información sobre 
equipos de futbol utilizando Spring boot 3 y Java 17. La API permite 
consultar, crear, actualizar, eliminar y buscar equipos de futbol en 
una base de datos H2 localizada en memoria. Ademas, cuenta con autenticación 
basada en JWT para acceder a los endpoints.

.------Requisitos-------.
|Java 17				|
|Maven 3.9 				|
|Docker					|	
-----------------------
 
Ejecuta el comando mvn clean package, que generara el .jar en el 
directorio target.

Ejecuta en una terminal bash los siguientes comandos:

java -jar target/AppDux-1.0.0-SNAPSHOT.jar

Para ejecucion en contenedor docker:

docker build -t appdux .

docker run -p 9090:9090 appdux

###############################################################################

Documentación Swagger

La documentacion de la API está disponible en Swagger, y puedes acceder desde 
http://localhost:9090/swagger-ui.html cuando la aplicacion esté en ejecucion.

Eso es todo lo que debes saber para poder ejecutar de manera correcta la app. (?