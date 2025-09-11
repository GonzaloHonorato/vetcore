# VetCore - Sistema de Gestión de Atención Medicas

## Descripción

VetCore es un sistema completo de gestión de atención  que permite manejar pacientes, consultas y atenciones médicas. El sistema está implementado con Spring Boot y utiliza Oracle Autonomous Database como base de datos.

## Características

- **Gestión completa de CRUD** para:
  - Pacientes
  - Consultas médicas  
  - Atenciones veterinarias
- **Base de datos Oracle** con conectividad segura mediante wallet
- **API REST** con endpoints completos
- **Persistencia JPA/Hibernate**
- **Validaciones y manejo de errores**

## Configuración de Base de datos Oracle

### Prerrequisitos

1. Oracle Autonomous Database configurado
2. Wallet de conexión descargado y ubicado en `src/main/resources/wallet/`

### Configuración

1. **Wallet**: El wallet de Oracle ya está incluido en `src/main/resources/wallet/`
2. **Configurar conexión**: Verificar en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@dbpeliculas_high?TNS_ADMIN="xx ruta al wallet "/wallet
   spring.datasource.username=ADMIN
   spring.datasource.password=IFO:`46gW0.%
   ```

3. **Crear tablas**: Ejecutar el script SQL de configuración:
   ```sql
   -- Ejecutar en Oracle SQL Developer o similar
   @atencion-db-setup.sql
   ```

### Scripts SQL incluidos

- `atencion-db-setup.sql`: Crea todas las tablas, secuencias y datos de prueba
- `autonomous-db-setup.sql`: Configuración general de la base de datos
- `oracle-setup.sql`: Configuración específica de Oracle

## Estructura del Proyecto

```
src/main/java/com/vetcore/vetcore/core/atencion/
├── dto/           # Objetos de transferencia de datos
├── mapper/        # Mappers para conversión DTO/Entity
├── model/         # Entidades JPA
├── repository/    # Repositorios Spring Data JPA
├── service/       # Lógica de negocio
└── web/          # Controladores REST
```

## Entidades principales

### Paciente
- ID (PK)
- RUT (único)
- Nombre, Apellido
- Fecha de nacimiento
- Teléfono, Email, Dirección
- Sexo, Estado civil

### Consulta
- ID (PK)
- Paciente ID (FK)
- Fecha de consulta
- Motivo, Síntomas
- Diagnóstico, Tratamiento
- Observaciones, Médico

### Atencion
- ID (PK)
- Consulta ID (FK)
- Paciente ID (FK)
- Fecha de atención
- Tipo de atención, Especialidad
- Resultado, Prescripciones
- Próxima cita, Estado

## API Endpoints

### Pacientes
- `GET /api/pacientes` - Listar todos los pacientes
- `GET /api/pacientes/{id}` - Obtener paciente por ID
- `GET /api/pacientes/buscar?rut={rut}` - Buscar por RUT
- `POST /api/pacientes` - Crear paciente
- `PUT /api/pacientes/{id}` - Actualizar paciente
- `DELETE /api/pacientes/{id}` - Eliminar paciente

### Consultas
- `GET /api/consultas` - Listar todas las consultas
- `GET /api/consultas/{id}` - Obtener consulta por ID
- `GET /api/consultas/paciente/{pacienteId}` - Consultas por paciente
- `POST /api/consultas` - Crear consulta
- `PUT /api/consultas/{id}` - Actualizar consulta
- `DELETE /api/consultas/{id}` - Eliminar consulta

### Atenciones
- `GET /api/atenciones` - Listar todas las atenciones
- `GET /api/atenciones/{id}` - Obtener atención por ID
- `GET /api/atenciones/paciente/{pacienteId}` - Atenciones por paciente
- `GET /api/atenciones/consulta/{consultaId}` - Atenciones por consulta
- `GET /api/atenciones/especialidad?nombre={nombre}` - Por especialidad
- `POST /api/atenciones` - Crear atención
- `PUT /api/atenciones/{id}` - Actualizar atención
- `DELETE /api/atenciones/{id}` - Eliminar atención

## Ejecución

1. **Compilar el proyecto**:
   ```bash
   ./mvnw clean compile
   ```

2. **Ejecutar la aplicación**:
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Acceder a la aplicación**:
   - URL base: `http://localhost:8080`
   - API endpoints: `http://localhost:8080/api/`

## Configuración adicional

### Hibernate
- DDL auto: `update` (crea/actualiza tablas automáticamente)
- Show SQL: `true` (muestra queries en consola)
- Format SQL: `true` (formatea queries para mejor legibilidad)

### Oracle específico
- Dialect: `OracleDialect`
- Default schema: `ADMIN`
- Character encoding: `UTF-8`
- SSL version: `1.2`

## Datos de prueba

El script `atencion-db-setup.sql` incluye datos de prueba:
- 5 pacientes
- 5 consultas médicas
- 6 atenciones veterinarias

## Tecnologías utilizadas

- Spring Boot 3.5.4
- Spring Data JPA
- Oracle Database 19c
- Hibernate
- Lombok
- Maven
- Java 21
