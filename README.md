# AgriArio - Proyecto de Hora de Libre Configuración

###### Óscar García Sánchez & Miguel Ángel de la Rosa Leva (2do DAW🌑)

## Descripción del Proyecto

Este proyecto consiste en el desarrollo de una aplicación web para la gestión de una tienda de tractores. La aplicación permite a los usuarios realizar diversas operaciones con los tractores disponibles en el inventario, tales como añadir nuevos tractores, modificar la información de los existentes y eliminar los que ya no están disponibles. La aplicación está diseñada para ser utilizada por dos tipos de perfiles de usuario:

1. **Administrador**: Tendrá acceso completo para añadir, eliminar y modificar los tractores en el sistema.
2. **Usuario**: Podrá consultar la información de los tractores disponibles, pero no podrá realizar cambios en la base de datos.

La aplicación maneja información como:

- **Nombre del tractor**
- **Marca**
- **Modelo**
- **Año**
- **Precio**
- **Descripción**
- **Cantidad disponible**

Los administradores podrán gestionar el inventario de tractores a través de una interfaz web fácil de usar.

## Funcionalidades

- **Añadir Tractor**: El administrador podrá agregar nuevos tractores al sistema, especificando todos los detalles relevantes como nombre, marca, año, etc.
- **Modificar Tractor**: El administrador podrá actualizar la información de los tractores existentes, como su precio, cantidad disponible y otros detalles.
- **Eliminar Tractor**: El administrador podrá eliminar tractores del inventario si ya no están disponibles o son obsoletos.
- **Consultar Tractores**: Los usuarios podrán consultar el catálogo de tractores disponibles, con información detallada sobre cada uno.

## Tecnologías Utilizadas

### Backend

- **Spring Boot**: Framework principal para desarrollar el backend de la aplicación, gestionando la lógica del negocio y las interacciones con la base de datos.
- **JPA (Java Persistence API)**: Para gestionar la interacción con la base de datos de manera eficiente.
- **MySQL**: Base de datos relacional para almacenar la información de los tractores.
- **Spring Security**: Para gestionar la autenticación y autorización de los usuarios (perfiles de Administrador y Usuario).
  
### Frontend

- **Thymeleaf**: Motor de plantillas utilizado en el frontend para renderizar páginas HTML de forma dinámica desde el servidor.
- **Bootstrap**: Framework CSS para crear una interfaz de usuario receptiva, moderna y amigable.
- **Bootstrap**: Framework CSS para crear una interfaz de usuario receptiva, moderna y amigable.

### Herramientas Adicionales

- **Maven**: Herramienta de construcción y gestión de dependencias.
- **Git**: Sistema de control de versiones utilizado para gestionar el código fuente del proyecto.

## Requisitos

- **JDK 11 o superior**: Se requiere tener instalado Java Development Kit (JDK) versión 11 o superior para ejecutar el proyecto.
- **MySQL**: Debes tener una instancia de MySQL en funcionamiento para almacenar la base de datos del proyecto.

## Instrucciones para Ejecutar el Proyecto

1. **Clonar el repositorio**:

2. **Configurar la base de datos**:

- Utiliza el archivo `tienda_tractores.sql` para realizar la importacion de la base de datos.
- Configura las credenciales en el archivo `application.properties` dentro del proyecto:

  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/tienda_tractores
  spring.datasource.username=tu_usuario
  spring.datasource.password=tu_contraseña
  ```

3. **Acceder a la aplicación**:
Una vez ejecutado el proyecto, abre tu navegador y ve a: <http://localhost:8080>
