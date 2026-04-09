# Mi Aplicación — Laravel 11

Proyecto migrado de Java a PHP Laravel 11.

## Requisitos

- PHP >= 8.2
- Composer
- MySQL 8.0+ (o MariaDB 10.6+)
- Node.js >= 18 y npm (para assets frontend)

## Instalación

```bash
# 1. Clonar el repositorio
git clone <url-del-repo> && cd <carpeta>

# 2. Instalar dependencias PHP
composer install

# 3. Copiar el archivo de entorno
cp .env.example .env

# 4. Generar la clave de la aplicación
php artisan key:generate

# 5. Configurar la base de datos en .env
# Editar DB_DATABASE, DB_USERNAME, DB_PASSWORD

# 6. Ejecutar migraciones
php artisan migrate

# 7. (Opcional) Instalar dependencias frontend
npm install && npm run build

# 8. Levantar el servidor de desarrollo
php artisan serve
```

## Estructura del proyecto

```
app/
├── Http/Controllers/    # Controladores (equivalente a @Controller/@RestController de Java)
├── Models/              # Modelos Eloquent (equivalente a @Entity de JPA)
├── Providers/           # Service Providers (equivalente a @Configuration de Spring)
config/                  # Configuraciones (equivalente a application.properties)
database/
├── migrations/          # Migraciones de BD (equivalente a Flyway/Liquibase)
├── factories/           # Factories para testing
├── seeders/             # Seeders de datos iniciales
resources/
├── views/               # Vistas Blade (equivalente a Thymeleaf/JSP)
├── css/                 # Estilos
├── js/                  # JavaScript
routes/
├── web.php              # Rutas web (equivalente a @RequestMapping)
├── console.php          # Comandos artisan (equivalente a @Scheduled / CLI runners)
tests/                   # Tests PHPUnit (equivalente a JUnit)
```

## Equivalencias Java → Laravel

| Concepto Java            | Equivalente Laravel           |
|--------------------------|-------------------------------|
| `@Entity`                | `Model` (Eloquent ORM)        |
| `@Repository`            | Eloquent / Query Builder      |
| `@Service`               | Service class / Action class   |
| `@Controller`            | `Controller`                  |
| `@RestController`        | `Controller` + `return json`  |
| `@RequestMapping`        | `routes/web.php` o `api.php`  |
| `application.properties` | `.env` + `config/*.php`       |
| `@Autowired`             | Dependency Injection (constructor) |
| `@Transactional`         | `DB::transaction()`           |
| JPA/Hibernate            | Eloquent ORM                  |
| Flyway/Liquibase         | `php artisan migrate`         |
| JUnit                    | PHPUnit                       |
| Maven/Gradle             | Composer                      |
| `@Scheduled`             | Task Scheduling / Queues      |
| Middleware (Filter)      | Middleware                    |

## Comandos útiles

```bash
php artisan serve              # Levantar servidor de desarrollo
php artisan make:model Foo -mcr  # Crear modelo + migración + controller resource
php artisan make:controller FooController  # Crear controlador
php artisan migrate            # Ejecutar migraciones pendientes
php artisan migrate:rollback   # Revertir última migración
php artisan tinker             # Consola interactiva (como JShell)
php artisan route:list         # Ver todas las rutas (como listar endpoints)
php artisan test               # Ejecutar tests
```

## Licencia

MIT
