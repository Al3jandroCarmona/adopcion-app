# 🐾 Adopción de Mascotas – API REST

API REST construida con **Spring Boot 3.2** + **MySQL** + **JPA/Hibernate**.

---

## ⚙️ Requisitos

| Herramienta | Versión mínima |
|-------------|---------------|
| Java        | 17            |
| Maven       | 3.8+          |
| MySQL       | 8.0+          |

---

## 🚀 Pasos para ejecutar

### 1. Crear la base de datos

```bash
mysql -u root -p < database.sql
```

### 2. Configurar conexión

Edita `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/adopcion_db?...
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD
```

### 3. Compilar y ejecutar

```bash
mvn spring-boot:run
```

La API estará en: `http://localhost:8080`

---

## 📋 Endpoints

### 👤 Usuarios  `/api/usuarios`

| Método | Ruta              | Descripción           |
|--------|-------------------|-----------------------|
| GET    | `/api/usuarios`   | Listar activos        |
| GET    | `/api/usuarios/{id}` | Obtener por ID     |
| POST   | `/api/usuarios`   | Crear usuario         |
| PUT    | `/api/usuarios/{id}` | Actualizar         |
| DELETE | `/api/usuarios/{id}` | Soft-delete        |

**Ejemplo POST:**
```json
{
  "nombre": "María",
  "apellidoPaterno": "López",
  "apellidoMaterno": "Torres",
  "email": "maria@example.com",
  "password": "12345",
  "telefono": "9511234567"
}
```

---

### 🐶 Tipos de Mascota  `/api/tipos-mascota`

| Método | Ruta                    | Descripción     |
|--------|-------------------------|-----------------|
| GET    | `/api/tipos-mascota`    | Listar activos  |
| GET    | `/api/tipos-mascota/{id}` | Obtener por ID |
| POST   | `/api/tipos-mascota`    | Crear tipo      |
| PUT    | `/api/tipos-mascota/{id}` | Actualizar    |
| DELETE | `/api/tipos-mascota/{id}` | Soft-delete   |

**Ejemplo POST:**
```json
{ "descripcion": "Perro" }
```

---

### 🐕 Mascotas  `/api/mascotas`

| Método | Ruta                          | Descripción            |
|--------|-------------------------------|------------------------|
| GET    | `/api/mascotas`               | Listar activas         |
| GET    | `/api/mascotas/disponibles`   | Solo disponibles       |
| GET    | `/api/mascotas/{id}`          | Obtener por ID         |
| POST   | `/api/mascotas?idDonador=1&idTipo=1` | Crear mascota   |
| PUT    | `/api/mascotas/{id}?idTipo=2` | Actualizar             |
| DELETE | `/api/mascotas/{id}`          | Soft-delete            |

**Ejemplo POST:**
```json
{
  "nombre": "Rocky",
  "raza": "Bulldog",
  "sexo": "Macho",
  "edadAproximada": "3 años",
  "descripcion": "Muy tranquilo y cariñoso"
}
```

---

### 🖼️ Imágenes de Mascota  `/api/imagenes`

| Método | Ruta                              | Descripción             |
|--------|-----------------------------------|-------------------------|
| GET    | `/api/imagenes/mascota/{idMascota}` | Imágenes de una mascota |
| GET    | `/api/imagenes/{id}`              | Obtener por ID          |
| POST   | `/api/imagenes?idMascota=1`       | Agregar imagen          |
| PUT    | `/api/imagenes/{id}`              | Actualizar              |
| DELETE | `/api/imagenes/{id}`              | Eliminar                |

**Ejemplo POST:**
```json
{
  "urlImagen": "https://example.com/fotos/rocky.jpg",
  "imagenPrincipal": true
}
```

---

### 📝 Solicitudes de Adopción  `/api/solicitudes`

| Método | Ruta                                     | Descripción               |
|--------|------------------------------------------|---------------------------|
| GET    | `/api/solicitudes`                       | Listar todas              |
| GET    | `/api/solicitudes/usuario/{id}`          | Por solicitante           |
| GET    | `/api/solicitudes/mascota/{id}`          | Por mascota               |
| GET    | `/api/solicitudes/{id}`                  | Obtener por ID            |
| POST   | `/api/solicitudes?idMascota=1&idUsuario=2` | Crear solicitud         |
| PATCH  | `/api/solicitudes/{id}/estado?estado=Aprobada` | Cambiar estado     |
| DELETE | `/api/solicitudes/{id}`                  | Eliminar                  |

**Estados válidos:** `Pendiente` | `Aprobada` | `Rechazada`

**Ejemplo POST:**
```json
{
  "mensaje": "Tengo jardín grande y experiencia con perros."
}
```

---

### 🏠 Adopciones  `/api/adopciones`

| Método | Ruta                               | Descripción                          |
|--------|------------------------------------|--------------------------------------|
| GET    | `/api/adopciones`                  | Listar todas                         |
| GET    | `/api/adopciones/adoptante/{id}`   | Por adoptante                        |
| GET    | `/api/adopciones/{id}`             | Obtener por ID                       |
| POST   | `/api/adopciones?idSolicitud=1&idMascota=1&idAdoptante=2` | Registrar adopción (cambia estado mascota automáticamente) |
| PUT    | `/api/adopciones/{id}`             | Actualizar observaciones             |
| DELETE | `/api/adopciones/{id}`             | Eliminar                             |

**Ejemplo POST body:**
```json
{
  "observaciones": "Adopción completada sin problemas."
}
```

---

## 🗂️ Estructura del proyecto

```
src/main/java/com/adopcion/
├── AdopcionApiApplication.java
├── controller/
│   ├── UsuarioController.java
│   ├── CatTipoMascotaController.java
│   ├── MascotaController.java
│   ├── ImagenMascotaController.java
│   ├── SolicitudAdopcionController.java
│   └── AdopcionController.java
├── service/
│   └── (un Service por entidad)
├── repository/
│   └── (un Repository por entidad)
├── model/
│   ├── Usuario.java
│   ├── CatTipoMascota.java
│   ├── Mascota.java
│   ├── ImagenMascota.java
│   ├── SolicitudAdopcion.java
│   └── Adopcion.java
└── exception/
    ├── ResourceNotFoundException.java
    └── GlobalExceptionHandler.java
```
