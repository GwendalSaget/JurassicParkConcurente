Este proyecto simula la gestión de un parque temático basado en dinosaurios. Los elementos clave del proyecto son:

Base de Datos SQLite: Utiliza una base de datos SQLite para almacenar y gestionar la información de los dinosaurios. La base de datos contiene una tabla llamada dino con campos como id, isla, espece, age, argent, temperature, y salud para cada dinosaurio.

Dinosaurios: Los dinosaurios en el parque son gestionados mediante objetos de la clase Dino, donde cada dinosaurio tiene atributos como especie, edad, salud, etc.

Simulación Anual: La simulación avanza año a año, y cada año se simula el ciclo de vida y evolución de los dinosaurios en el parque. Cada año se realiza una serie de actualizaciones y verificaciones del estado de los dinosaurios (salud, edad, temperatura, etc.).

Interfaz de Usuario: El usuario interactúa con el sistema a través de la consola. Se le presenta un menú con opciones para continuar simulando los años o finalizar la simulación. El sistema también ofrece la opción de borrar la base de datos y empezar de nuevo.

Servicios: Se utilizan servicios como ParkService para inicializar el parque y DinoService para realizar actualizaciones periódicas de los dinosaurios. Además, el proyecto tiene funcionalidades para agregar, actualizar o eliminar dinosaurios mediante consultas SQL.

Base de Datos: La conexión a la base de datos se realiza mediante JDBC, utilizando un controlador de SQLite. La base de datos se gestiona con operaciones de inserción, eliminación y actualización de dinosaurios.

Este proyecto es un ejemplo de cómo gestionar un sistema de simulación y gestión de datos en un entorno ficticio, utilizando tecnologías como Java, JDBC y SQLite, y ofreciendo al usuario una interfaz simple pero funcional para interactuar con la simulación.

Antes de empezar, hay que cambiar el camino de la base de datos, muchas gracias

enlace GitHub : https://github.com/GwendalSaget/JurassicParkConcurente

                        . - ~ ~ ~ - .
      ..     _      .-~               ~-.
     //|     \ `..~                      `.
    || |      }  }              /       \  \
(\   \\ \~^..'                 |         }  \
 \`.-~  o      /       }       |        /    \
 (__          |       /        |       /      `.
  `- - ~ ~ -._|      /_ - ~ ~ ^|      /- _      `.
              |     /          |     /     ~-.     ~- _
              |_____|          |_____|         ~ - . _ _~_-_
