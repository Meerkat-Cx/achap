# Migracion de Java a Laravel 11

Este repositorio ya quedo inicializado con una base limpia de Laravel 11 para empezar la migracion de tu aplicacion Java.

## Estado actual

- Laravel 11 instalado en la raiz del repo
- Dependencias PHP resueltas con Composer
- Base SQLite creada para desarrollo rapido
- Migraciones base ejecutadas correctamente

Todavia no se migro logica de negocio porque el codigo Java original aun no esta dentro de este repositorio.

## Como levantar el proyecto

```bash
composer install
cp .env.example .env
php artisan key:generate
php artisan migrate
php artisan serve
```

## Que necesito para empezar a pasarlo desde Java

Subime una de estas opciones:

1. El repo Java completo
2. Un zip con el codigo fuente
3. Las carpetas principales del backend Java, por ejemplo:
   - `controller` / `resource`
   - `service`
   - `repository` / `dao`
   - `entity` / `model`
   - `dto`
   - configuracion de seguridad
   - scripts SQL o estructura de base

## Como vamos a migrarlo

La migracion la voy a hacer por capas para no romper reglas de negocio:

1. Analizar endpoints, servicios y entidades del proyecto Java
2. Mapear tablas y relaciones a migrations + models de Laravel
3. Migrar logica de negocio a services de Laravel
4. Exponer controladores y rutas equivalentes
5. Reemplazar seguridad/autenticacion por middleware, policies o guards de Laravel
6. Validar paridad funcional con pruebas puntuales

## Mapeo general Java -> Laravel 11

| Java | Laravel 11 |
| --- | --- |
| Controller / Resource | Controller |
| Service | Service class en `app/Services` |
| Repository / DAO | Eloquent / Query Builder / Repository dedicado si hace falta |
| Entity | Model + Migration |
| DTO | Form Request / Resource / Data object |
| Scheduler | Console Command / Scheduler |
| Async jobs | Queue Jobs |
| Spring Security / filtros | Middleware / Auth / Policies |

## Siguiente paso

Cuando subas tu codigo Java al repo, puedo empezar por una de estas dos estrategias:

- **Migracion por modulo**: pasamos primero un modulo completo de punta a punta
- **Migracion por capa**: primero modelos y tablas, despues servicios, despues endpoints

## Guia adicional

Deje una guia operativa en `docs/migracion-java-a-laravel.md` con el checklist para convertir cada pieza del proyecto Java.
