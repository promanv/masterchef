Pasos para ejecutar el demo:

I - Base de datos MySql
1. Crear la base de datos "masterchef"
2. Crear tabla "receta"
3. Poblar tabla "receta"
** Utilizar el script "receta.sql" para la creacion y poblado de la tabla.

II - Ejecutar App
1. Requisitos maven y Java 17
2. Descargar repositorio
3. Ejecuta app con mvn: mvn spring-boot:run
4. Alternativa, generar .jar de la aplicacion: mvn clean package, entrar a la carpeta target, ejecutar: java -jar masterchef-v1.jar

III - Swagger
1. El demo viene configurado con swagger para la documentacion de los endpoints.
   http://localhost:8080/swagger-ui/index.html

IV - Endpoint
1. Asumiendo que la aplicacion levanta en http://localhost:8080, se crearon los soguientes endpoints:
  -  POST /api/recetas: Añadir una nueva receta.
  -  GET /api/recetas: Obtener todas las recetas.
  -  GET /api/recetas/{id}: Ver una receta en particular.
  -  PUT /api/recetas/{id}: Actualizar la receta.
  -  DELETE /api/recetas/{id}: Eliminar una receta.
  -  PATCH /api/recetas/{id}/{voto}: Votar por una receta.
    ** en "masterchef\src\test\java\cl\prueba\ilis\masterchef\ejemplos" existen dos archivos con estructuras de ejemplo: list.json y single.json
