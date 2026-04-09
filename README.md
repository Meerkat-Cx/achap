# Achap — Laravel 11

Proyecto migrado de Java a PHP/Laravel 11.

## Requisitos

- PHP >= 8.2
- Composer >= 2.x
- MySQL >= 8.0
- Node.js >= 18 (para assets con Vite)

## Instalación local

```bash
# 1. Clonar el repositorio
git clone <url-del-repo> achap
cd achap

# 2. Instalar dependencias PHP
composer install

# 3. Copiar y configurar el entorno
cp .env.example .env
php artisan key:generate

# 4. Configurar la base de datos en .env
# DB_DATABASE=achap
# DB_USERNAME=tu_usuario
# DB_PASSWORD=tu_password

# 5. Crear la base de datos y correr migraciones
php artisan migrate

# 6. (Opcional) Cargar datos de prueba
php artisan db:seed

# 7. Levantar el servidor de desarrollo
php artisan serve
```

## Estructura del proyecto

```
app/
├── Http/
│   ├── Controllers/     # Controladores (equivalen a los @RestController de Java)
│   ├── Middleware/      # Middleware de autenticación, permisos, etc.
│   └── Requests/        # Form Requests para validación (equivalen a los DTO/validators de Java)
├── Models/              # Modelos Eloquent (equivalen a las @Entity de Java)
├── Services/            # Lógica de negocio (equivalen a los @Service de Java)
├── Repositories/        # Acceso a datos (equivalen a los @Repository de Java)
└── Traits/              # Traits reutilizables

database/
├── migrations/          # Estructura de tablas (equivalen a los scripts SQL/Liquibase de Java)
├── seeders/             # Datos de prueba
└── factories/           # Factories para tests

resources/
├── views/               # Vistas Blade (equivalen a los templates Thymeleaf/JSP de Java)
└── lang/                # Traducciones

routes/
├── web.php              # Rutas web (equivalen a los @RequestMapping de Java)
└── api.php              # Rutas API REST
```

## Equivalencias Java → Laravel

| Java (Spring Boot)         | Laravel 11                         |
|----------------------------|------------------------------------|
| `@RestController`          | `Controller` + `return response()->json()` |
| `@Service`                 | Clase en `app/Services/`           |
| `@Repository`              | Modelo Eloquent o clase en `app/Repositories/` |
| `@Entity`                  | Modelo en `app/Models/`            |
| `@RequestMapping`          | `Route::get/post/put/delete` en `routes/` |
| `application.properties`  | `.env`                             |
| `@Autowired`               | Inyección de dependencias en constructor |
| `JPA / Hibernate`          | Eloquent ORM                       |
| `Flyway / Liquibase`       | `php artisan migrate`              |
| `@Valid` / `BindingResult` | `FormRequest` con `rules()`        |
| `JWT / Spring Security`    | Laravel Sanctum / Passport         |

## Comandos útiles

```bash
# Crear un nuevo controller
php artisan make:controller NombreController

# Crear un modelo con su migración
php artisan make:model NombreModelo -m

# Crear un Form Request (validación)
php artisan make:request NombreRequest

# Ver todas las rutas
php artisan route:list

# Limpiar caché
php artisan optimize:clear

# Correr tests
php artisan test
```

## Configuración de base de datos

El proyecto usa MySQL. Asegurate de crear la base de datos antes de migrar:

```sql
CREATE DATABASE achap CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

## Entornos

| Variable        | Descripción                          |
|-----------------|--------------------------------------|
| `APP_ENV`       | `local`, `staging`, `production`     |
| `APP_DEBUG`     | `true` solo en desarrollo            |
| `APP_URL`       | URL base de la aplicación            |
| `DB_CONNECTION` | `mysql` (recomendado para producción)|
| `DB_DATABASE`   | Nombre de la base de datos           |
