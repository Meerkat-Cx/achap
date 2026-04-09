# AGENTS.md — Achap Laravel 11

## Arquitectura general

Proyecto PHP/Laravel 11 migrado desde Java (Spring Boot).
Sigue el patrón MVC extendido con una capa de Services para lógica de negocio.

## Stack tecnológico

- **Framework:** Laravel 11 (PHP 8.3)
- **ORM:** Eloquent
- **Base de datos:** MySQL 8.0
- **Autenticación:** Laravel Sanctum (API tokens) o sesiones web
- **Frontend:** Blade templates + Vite (Bootstrap 5)
- **Tests:** PHPUnit / Pest

## Estructura de capas

```
Controller → FormRequest (validación) → Service → Repository/Model → DB
```

- **Controllers** (`app/Http/Controllers/`): Reciben la request HTTP, delegan al Service, devuelven respuesta. No contienen lógica de negocio.
- **Services** (`app/Services/`): Contienen la lógica de negocio. Equivalen a los `@Service` de Spring.
- **Repositories** (`app/Repositories/`): Encapsulan queries complejas. Para queries simples, usar Eloquent directamente en el Service.
- **Models** (`app/Models/`): Mapean tablas. Definen relaciones, casts y fillable. Equivalen a las `@Entity` de JPA.
- **FormRequests** (`app/Http/Requests/`): Validación de inputs. Equivalen a los DTOs + `@Valid` de Spring.

## Convenciones de nombres

| Tipo              | Convención                  | Ejemplo                          |
|-------------------|-----------------------------|----------------------------------|
| Controller        | PascalCase + `Controller`   | `ClienteController`              |
| Model             | PascalCase singular         | `Cliente`                        |
| Migration         | snake_case descriptiva      | `create_clientes_table`          |
| Service           | PascalCase + `Service`      | `ClienteService`                 |
| Repository        | PascalCase + `Repository`   | `ClienteRepository`              |
| FormRequest       | PascalCase + `Request`      | `StoreClienteRequest`            |
| Tabla DB          | snake_case plural           | `clientes`, `ordenes_servicio`   |

## Rutas

- `routes/web.php` — Rutas con sesión/autenticación web
- `routes/api.php` — Rutas API REST (prefijo `/api/`, stateless con Sanctum)

## Tablas principales

> Completar a medida que se migren las entidades del sistema Java original.

| Tabla              | Descripción                    | Modelo            |
|--------------------|--------------------------------|-------------------|
| `users`            | Usuarios del sistema           | `User`            |
| (pendiente migrar) | Entidades del sistema Java     | —                 |

## Patrones obligatorios

### 1. Docblock en todo archivo nuevo o modificado

```php
/**
 * Gestiona [qué hace].
 * 
 * Tables: tabla_a (R/W), tabla_b (R)
 * Related: OtroController, OtroService
 */
```

### 2. Queries con bindings (nunca concatenar variables en SQL)

```php
// ✅ Correcto
DB::select('SELECT * FROM clientes WHERE id = ?', [$id]);

// ❌ Incorrecto
DB::select("SELECT * FROM clientes WHERE id = $id");
```

### 3. Multi-tenant

Si el sistema es multi-tenant, NUNCA hardcodear nombres de base de datos.
Usar el helper de dominio/tenant correspondiente.

### 4. Traducciones

Usar `__('clave')` o el helper `t_local('clave', 'fallback')` para strings de UI.
Archivos de idioma en `resources/lang/es/`.

## Comandos de desarrollo frecuentes

```bash
php artisan make:controller NombreController --resource
php artisan make:model Nombre -msr          # Model + Migration + Seeder + Repository
php artisan make:request StoreNombreRequest
php artisan make:service NombreService       # requiere: composer require --dev reliese/laravel
php artisan migrate:fresh --seed            # reset completo (solo en local)
php artisan route:list --path=api           # ver rutas API
php artisan test --filter NombreTest
```

## Migración desde Java — checklist por entidad

Cuando migrés una entidad del sistema Java original:

1. [ ] Crear migración con `php artisan make:migration`
2. [ ] Crear Model Eloquent con relaciones
3. [ ] Crear Service con la lógica de negocio
4. [ ] Crear Controller (resource o API)
5. [ ] Crear FormRequests para store/update
6. [ ] Agregar rutas en `web.php` o `api.php`
7. [ ] Crear tests básicos
8. [ ] Documentar la tabla en este AGENTS.md

## Seguridad

- NUNCA exponer tokens/credenciales en el frontend ni en git
- Usar `$request->validated()` siempre (no `$request->all()`)
- Rate limiting en rutas sensibles con `throttle:`
- CSRF activo en todas las rutas web (automático en Laravel)
