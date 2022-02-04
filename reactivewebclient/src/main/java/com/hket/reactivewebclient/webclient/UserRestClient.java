package com.hket.reactivewebclient.webclient;

import java.util.List;

import com.hket.reactivewebclient.Entity.User;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserRestClient {
    
    private WebClient webClient;

    public UserRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<User> retrieveAllUser() {
        return webClient.get().uri("/user")
            .retrieve()
            .bodyToFlux(User.class)
            .collectList()
            .block();
    }

    public User retrieveUserById(String id) {
        try {
            return webClient.get().uri("/user/" + id)
            .retrieve()
            .bodyToMono(User.class)
            .block();
        } catch (WebClientResponseException ex) {
            log.error("Error Response Code is {} and the response body is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error("WebClientResponseException in retrieveUserById ", ex);
            throw ex;
        } catch(Exception ex) {
            log.error("Exception in retrieveUserById ", ex);
            throw ex;
        }
        
    }

    public User addNewUser(User user) {
        return webClient.post().uri("/user")
            .bodyValue(user)
            .retrieve()
            .bodyToMono(User.class)
            .block();
    }

    public User updateUser(User user) {
        return webClient.put().uri("/user")
            .bodyValue(user)
            .retrieve()
            .bodyToMono(User.class)
            .block();
    }

    public String deleteUser(String id) {
        return webClient.delete().uri("/user/" + id)
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }

}
