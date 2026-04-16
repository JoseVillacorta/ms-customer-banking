
# Banking System Customer Microservice

**Microservicio para la gestión integral de clientes del banco.**

Este servicio es el corazón del sistema de identidad, permitiendo crear, consultar, actualizar y eliminar la información de los clientes (DNI, Nombres, Correo, etc.) de manera asíncrona y reactiva.

## Descripción del Proyecto

El **Customer Microservice** está construido bajo una arquitectura reactiva pura para garantizar la máxima escalabilidad y el menor consumo de recursos posible.

### Qué hace esta aplicación
- **Gestión CRUD:** Operaciones completas para el ciclo de vida del cliente.
- **Persistencia Reactiva:** Utiliza **R2DBC** para conectarse a PostgreSQL sin bloquear hilos.
- **Búsqueda Avanzada:** Permite encontrar clientes por ID o número de documento.
- **Documentación API:** Expone su contrato a través de Swagger/OpenAPI.

### Tecnologías utilizadas
- **Java 21**
- **Spring Boot 3.x / WebFlux** (Programación Funcional y Reactiva).
- **Spring Data R2DBC**: Para la persistencia no bloqueante.
- **PostgreSQL**: Base de datos relacional.
- **Lombok**: Para reducir el código repetitivo (Boilerplate).
- **Eureka Client & Config Client**.

---

## Cómo instalar y ejecutar el proyecto

### Requisitos previos
1. **ms-config-server** corriendo.
2. **registry-service** corriendo.
3. Base de datos **PostgreSQL** (db_customer) disponible.

### Pasos para ejecución local (Gradle)
1. Navega a la carpeta: `cd ms-customer`
2. Ejecuta:
   ```bash
   ./gradlew bootRun
   ```

### Pasos para ejecución con Docker
```bash
docker-compose up -d ms-customer
```

---

## Cómo utilizar el proyecto

### Endpoints (v1)
- **Listar todos:** `GET /api/v1/customers`
- **Buscar por ID:** `GET /api/v1/customers/{id}`
- **Buscar por documento:** `GET /api/v1/customers/document/{nro_documento}`
- **Buscar por zona:** `GET /api/v1/customers/zone/{zoneId}`
- **Crear cliente:** `POST /api/v1/customers`
- **Actualizar:** `PUT /api/v1/customers/{id}`
- **Eliminar:** `DELETE /api/v1/customers/{id}`

