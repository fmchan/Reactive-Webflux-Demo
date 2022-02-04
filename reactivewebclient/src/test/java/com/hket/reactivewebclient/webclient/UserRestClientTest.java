package com.hket.reactivewebclient.webclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;

import com.hket.reactivewebclient.Entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

public class UserRestClientTest {
    
    private static final String baseUrl = "http://localhost:8082";
    private WebClient webClient = WebClient.create(baseUrl);

    UserRestClient userRestClient = new UserRestClient(webClient);

    @Test
    void retrieveAllUser() {
        List<User> userList = userRestClient.retrieveAllUser();
        assertTrue(userList.size()>0);
        userList.forEach(item -> {
            System.out.println("Id : " + item.getId());
            System.out.println("Name : " + item.getName());
            System.out.println("Email : " + item.getEmail());
            System.out.println("=============================");
        });       
    }
    
    @Test
    void addNewUser() {
        User user = new User(null,"Lok", "wlfung@hket.com");
        User user1 = userRestClient.addNewUser(user);
        assertTrue(user1.getId() != null);
        System.out.println("New user id : " + user1.getId());
        System.out.println("New user name : " + user1.getName());
        System.out.println("New user email : " + user1.getEmail());
    }

    @Test
    void retrieveUserById() {
        String id = "61f3bf1a2487704440b488ed";
        User user = userRestClient.retrieveUserById(id);
        //System.out.println("User name is " + user.getName());
        assertEquals("Lok", user.getName());
        System.out.println("ID : " + user.getId());
        System.out.println("Name : " + user.getName());
        System.out.println("Email : " + user.getEmail());
    }

    @Test
    void updateUser() {
        String id = "61f3bf1a2487704440b488ed";
        User user = new User(id,"Lok88", "wlfung88@hket.com");
        User user1 = userRestClient.updateUser(user);
        assertEquals("Lok88", user1.getName());
        assertEquals("wlfung88@hket.com", user1.getEmail());
        System.out.println("ID : " + user1.getId());
        System.out.println("Name : " + user1.getName());
        System.out.println("Email : " + user1.getEmail());
    }

    @Test
    void deleteUser() {
        String id = "61f3bf1a2487704440b488ed";
        userRestClient.deleteUser(id);
    }

}
