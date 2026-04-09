<!--
Guia operativa para migrar un backend Java a Laravel 11.
Relaciona el codigo fuente Java con las capas Laravel que se iran construyendo en este repo.
-->
# Guia operativa: Java -> Laravel 11

## 1. Relevamiento inicial

Antes de migrar codigo hay que identificar:

- endpoints expuestos
- entidades principales
- tablas y relaciones
- autenticacion y autorizacion
- integraciones externas
- jobs, colas o tareas programadas
- validaciones de negocio

## 2. Checklist por tipo de archivo Java

### Controllers / Resources

Por cada controlador Java:

- listar rutas HTTP
- identificar request y response esperados
- ubicar dependencias de servicios
- recrear el endpoint en `routes/api.php` o `routes/web.php`
- crear el controller equivalente en `app/Http/Controllers`

### Services

Por cada servicio Java:

- separar logica de negocio de acceso a datos
- mover la logica a clases en `app/Services`
- mantener nombres de metodos orientados al caso de uso
- encapsular transacciones con `DB::transaction()` cuando aplique

### Repositories / DAO

Por cada repository:

- revisar queries custom
- decidir si alcanza con Eloquent o conviene Query Builder
- evitar portar patrones innecesarios si Laravel ya resuelve el caso
- crear repositories dedicados solo si la complejidad lo justifica

### Entities / Models

Por cada entidad Java:

- mapear atributos a columnas
- identificar claves primarias y foraneas
- crear migration
- crear model
- definir `fillable`, casts y relaciones

### DTO / Request Objects

Por cada DTO:

- distinguir si representa input o output
- input: llevarlo a `FormRequest`
- output: llevarlo a `JsonResource` o a un DTO liviano
- centralizar validaciones en requests

## 3. Mapeo tecnico recomendado

### Seguridad

- JWT Java -> Laravel Sanctum o Passport segun necesidad
- filtros/interceptors -> middleware
- reglas por rol -> gates/policies/middleware

### Excepciones

- excepciones custom Java -> excepciones de dominio o `abort()` controlado
- centralizar renderizado en el handler de excepciones

### Persistencia

- JPA/Hibernate -> Eloquent
- consultas complejas -> Query Builder o SQL parametrizado
- paginacion -> `paginate()`

### Procesos asincronos

- `@Scheduled` -> `app/Console/Kernel.php`
- colas o workers Java -> Jobs de Laravel + queue driver

## 4. Orden sugerido de migracion

1. modelo de datos
2. autenticacion
3. modulo mas chico y autocontenido
4. servicios compartidos
5. integraciones externas
6. jobs y scheduler

## 5. Entregables utiles que necesito de tu lado

Para acelerar la migracion, subime si podes:

- codigo Java fuente
- `pom.xml` o `build.gradle`
- `application.properties` o `application.yml`
- scripts SQL
- colecciones Postman o Swagger/OpenAPI
- ejemplos de payloads reales

## 6. Criterio de migracion

La idea no es traducir Java linea por linea, sino conservar el comportamiento funcional usando patrones nativos de Laravel 11.
