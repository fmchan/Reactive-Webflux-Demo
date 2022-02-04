### Discription
This demo is listen to reactivewebservice for testing the communicate between two services using reactive programming

### listen endpoint
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