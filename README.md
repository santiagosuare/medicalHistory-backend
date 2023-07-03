# Trabajo Final de Grado

## _Sistema único de historiales clínicos para la provincia de Córdoba_

Link Live Demo:

Proximamente...

## Proyecto Final
El mismo sera desplegado en 3 instancias:
- [x] Backend [medicalHistory-backend](https://github.com/santiagosuare/medicalHistory-backend)
- [x] Bff [medicalHistory-bff](https://google.com.ar)
- [x] Frontend [medicalHistory-frontend](https://google.com.ar)

### Tiempo de desarrollo
- Duracion 3 semanas
- 4 Hs semanales

### Tecnologias utilizadas
- <a href="https://docs.oracle.com/en/java/"> Java </a>
- <a href="https://react.dev/"> React.js </a>
- Patron de diseño MVC y BFF
- Arquitectura de Microservicios
- Para la base de datos se utilizo <a href="https://supabase.com/"> Supabase </a>

## Historia de Usuario 

Los mismos se encuentran extrictamente detallados en el documento TFG presentado para el cursado de la materia.


## Features

- Notificacion al usuario al momento de recibir un nuevo estudio, receta.
- Posibilidad de buscar por nombre, apellido al portal del Medico.
- Posibilidad de que un medico pueda ver sus ultimas consultas e historiales clinicos realizados.
- Integracion google maps, para accionar un boton de emergencia.

## Librerias externas

- [SpringBoot] - SpringBoot version 3.1.1-SNAPSHOT


[SpringBoot]: https://spring.io/projects/spring-boot


## Manual de Instalacion de compilacion

> Notas: Es necesario disponer Java 17 y Node.js para la ejecucion del sistema en entorno local.

Primero debemos tener en cuenta que cada servicio debe disponer de un puerto unico para poder ejecutarse.
Por defecto les recomendamos los siguientes:
- Backend: 8080
- Bff: 8081
- Frontend: 3000

### Backend

La base de datos se encuentra alojada en la nube, se necesita las siguientes credenciales para agregarlas al env. del backend.
- DB_HOST
- DB_PORT
- DB_NAME
- DB_USER
- DB_PASSWORD

las mismas deberan ser solicitadas al email: santiago.suarezdc@gmail.com. Ya que por cuestiones de seguridad no estan en distribucion publica.


```sh
$ spring.output.ansi.enabled=always
$ spring.datasource.url=jdbc:postgresql://
$ spring.datasource.username=
$ spring.datasource.password=
```

### Frontend
Una vez bajado el repositorio del frontend, se debe ejecutar los siguientes dos comandos:

```sh
$ npm install
```

```sh
$ npm start
```

Por ultimo

Abrir [http://localhost:3000](http://localhost:3000) para verlo en tu browser.


## License

Santiago Suarez

**Free Software, Hell Yeah!**