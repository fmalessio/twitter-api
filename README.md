# Twitter-api (backend)
La idea es construir un servicio agregador de entradas de APIs sociales, en este caso Twitter. Este
servicio es multitenant, cada usuario puede definir uno o más boards, que expresan sus intereses
particulares. Cada usuario accede a la aplicación con un nombre de usuario y contraseña.

Tecnologías utilizadas: Java, Spring boot, JPA, MVC, Heroku y MySQL.

### Host information
BASE_URL_LOCAL = http://localhost:8080/twitter-api  <br />
BASE_URL_PROD = https://twitter-back-api.herokuapp.com

### API References

GET "/users?id=test@test.com" <br />
GET "/boards/1/interests" <br />
PUT "/users" <br />
POST "/interests" <br />
POST "/boards" <br />
DELETE "/boards/2" <br />
DELETE "/interests/11" <br />

Para más detalles consultar el proyecto postman: [LINK]

### Base de datos
Heroku Addon: "ClearDB MySQL" <br />
Esquema: <br />

[IMG]
