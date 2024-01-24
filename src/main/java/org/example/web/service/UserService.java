package org.example.web.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.web.entity.User;
import org.example.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void fetchUsers() {
        String apiUrl = "https://dummyjson.com/comments";
        RestTemplate restTemplate = new RestTemplate();
        String jsonData = restTemplate.getForObject(apiUrl, String.class);

        List<User> users = mapJsonToUsers(jsonData);
        userRepository.saveAll(users);
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    private static List<User> mapJsonToUsers(String jsonData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonData);

            JsonNode commentsNode = rootNode.path("comments");
            if (commentsNode.isArray()) {
                return objectMapper.readValue(commentsNode.traverse(), objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
