# Twitter-api (backend)
La idea es construir un servicio agregador de entradas de APIs sociales, en este caso Twitter. Este
servicio es multitenant, cada usuario puede definir uno o más boards, que expresan sus intereses
particulares. Cada usuario accede a la aplicación con un nombre de usuario y contraseña.

Existirán 2 recursos REST:
- API para comenzar a seguir (y dejar de hacerlo) un tema de interés o un usuario. Los intereses
se identifican con un “#”, y los usuarios se identifican con “@”. Por ejemplo, si los intereses de
un usuario son #River, @liomessi y #Boca es porque quiere visualizar los posts públicos que
mencionan los temas #River o #Boca, y todos los posts sociales del usuario @leomessi
(independientemente de si mencionan o no a los dos temas anteriores).
- API para acceder a las entradas correspondientes a los intereses especificados para el board,
ya sea vía polling, consulta paginada o vía streaming (por ejemplo con un websocket). Este API
debe soportar muchos clientes simultáneos sin sobrecargarse de threads o lockeos de
cualquier tipo.
 <br /> <br />
Ambos recursos (o los que se definan), serán relativos a un board. Por ejemplo: <br />
/boards/{id}/interests
 <br /> <br />
Cuando se registra el interés en un hashtag o user, pasan dos cosas: <br />
- Se registra el interés en algún tipo de base de datos (redis, mongo, mysql, hsqldb o lo que quieras),
de manera que estos intereses sobrevivan a un reinicio
- Un proceso recolector comienza a buscar nuevos mensajes de esa suscripción, y los persiste. La
búsqueda del proceso se ejecuta a intervalos regulares.

### Technologies
Spring boot, Java, JPA, Quartz scheduler, Heroku y MySQL.

### How to install
- Create a MySQL database
- Run the sql files in the /files/sql
- Change the properties in the "application-local.properties" to use your database
- Change environment (profile) to "local" the "application.properties"
- Ready!

### Host information
BASE_URL_LOCAL = http://localhost:8080  <br />
BASE_URL_PROD = https://twitter-back-api.herokuapp.com

### API References
GET "/users?id=test@test.com" <br />
GET "/boards/{id}/interests" <br />
GET "/boards/{id}/tweets" <br />
PUT "/users" <br />
POST "/interests" <br />
POST "/boards" <br />
DELETE "/boards/{id}" <br />
DELETE "/interests/{id}" <br />

Para más detalles consultar el proyecto postman: [Twitter-api.postman_collection.json](https://github.com/fmalessio/twitter-api/blob/master/files/postman/Twitter-api.postman_collection.json)

### Base de datos
Heroku Addon: "ClearDB MySQL" <br />
Esquema: <br />

![schema in database](https://github.com/fmalessio/twitter-api/blob/master/files/img/database_schema.jpg)
