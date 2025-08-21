# Pokémon SOAP Services

## API SOAP para consultar información de Pokémon usando Spring Boot, con integración de pruebas unitarias, BDD con Cucumber y Dockerización del proyecto.

### 📌 Tecnologías
    
    - Java 17
    - Spring Boot 3.5.4
    - Spring WS
    - Gradle 8.14.3
    - Cucumber 7.15 + JUnit 5
    - Docker
    - Postman (colección incluida)

### 🚀 Características

    * El api Soap comprende los siguientes metodos:
            1. Abilities (Retorna una lista de habilidades del pokemon)
            2. Experience (Retorna el valor de experiencia (int) del pokemon)
            3. Items (Retorna una lista de items (name,url) del pokemon)
            4. Id (Retorna el identificador del pokemon)
            5. Name (Retorna el nombre del pokemon)
            6. Location_area (Retorna una lista de areas (name,url) donde es posible encontrar al pokemon)

    * Validaciones y pruebas automatizadas con Cucumber + JUnit 5
    * Reportes HTML y dashboard de pruebas
    * Contenerización con Docker (Compilado de proyecto)
    * Base de datos H2 (Persistencia en memoria)

### <img width="15" height="15" alt="image" src="https://github.com/user-attachments/assets/5f9f5f7f-489a-4991-969b-bcedb72c2c7c" />  Pruebas (Tests unitarios y reporte cucumber)
    
    Ejecutar sobre una terminal en la ruta raiz del proyecto el siguiente comando:

| Commando | Descripcion |
| --- | --- |
| ./gradlew clean test | Instrucción para generar reportes de Test |            

    Los reportes generados se encontraran sobre las siguientes rutas dentro del proyecto:

    
| Reporte | ruta |
| --- | --- |
|Reporte de pruebas unitarias (Junit)| \build\reports\tests\test\index.html |     
|Reportes cucumber html|\build\reports\cucumber\cucumber.html|
|Reporte features cucumber html |\build\reports\cucumber\cucumber-html\html\cucumber-html-reports\overview-features.html|

### 📡 Endpoints SOAP

**WSDL:**
    http://localhost:8080/webServicesPokemon/pokemon.wsdl

**Ejemplo de solicitud SOAP (Metodo Name)**

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                      xmlns:pok="http://challengeBankaya.com/pokemon/soap">
       <soapenv:Header/>
       <soapenv:Body>
           <pok:getNameRequest>
                <pok:name>pikachu</pok:name>
           </pok:getNameRequest>
       </soapenv:Body>
     </soapenv:Envelope>

**Ejemplo de respuesta SOAP (Metodo Name)**

    <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
        <SOAP-ENV:Header/>
        <SOAP-ENV:Body>
            <ns2:getNameResponse xmlns:ns2="http://challengeBankaya.com/pokemon/soap">
                <ns2:name>pikachu</ns2:name>
            </ns2:getNameResponse>
        </SOAP-ENV:Body>
    </SOAP-ENV:Envelope>

    
### <img width="18" height="18" alt="Postman" src="https://github.com/user-attachments/assets/efb4b6d0-36b6-4ac2-bab7-9c48232297fc" /> **Colección Postman**

   Se incluye una colección de Postman para probar rápidamente los endpoints soap del servicio.
        [Descargar colección Postman](postman/Pokemon-Service.postman_collection.json) 

    Para importarla en Postman:
        1.- Abrir Postman
        2.- Click en "Import"
        3.-Seleccionar el archivo JSON
    
    Ejecutar los requests disponibles en la colección

### 🐳 Docker (Requiere instalación y configuración previa de docker)

**Contruccion de imagen con compilacion del proyecto**
    
    docker build -t pokemon-service:latest .

**Ejecución de imagen**

    docker run --name WebServicesPokemon -p 8080:8080 pokemon-service:latest

        
### Validación de Persistencia en base de datos H2

**1. Ingresar la siguiente url en el navegador**
    
    http://localhost:8080/h2-console

**2. Ingresar con los siguientes datos de conexión**

    JDBC URL: jdbc:h2:mem:pokemonApi
	User Name : sa
	Password : @admin@123456
    
    
    
