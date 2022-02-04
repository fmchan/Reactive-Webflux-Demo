### Discription
This demo provide rest web service with mongo database using reactive and web service using functional format

### Mongo database on docker
> docker cmd : `docker run -d --name Mongo -p 27017:27017 mongo`

### Endpoint
#### web service with mongo database using reactive
- get all user (GET)
>http://localhost:8082/user

- get user by id (GET)
>http://localhost:8082/user/{id}

- add new user (POST)
>http://localhost:8082/user
```json
{
    "name": "Lok",
    "email": "wlfung@hket.com"
}
```

- update user (PUT)
>http://localhost:8082/user
```json
{
    "id": "61f223687b21e511816de1fb",
    "name": "Lok",
    "email": "wlfungcs@gmail.com"
}
```

- delete user (DELETE)
>http://localhost:8082/user/{id}

#### web service using functional format
- return mono
>http://localhost:8082/mono
- return flux
>http://localhost:8082/flux
- return object
>http://localhost:8082/hello