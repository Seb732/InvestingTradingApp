package com.capgemini.investingtradingappuser.controller.integrationtest;

import com.capgemini.investingtradingappuser.entity.User;
import com.capgemini.investingtradingappuser.repository.UserRepository;
import com.capgemini.investingtradingappuser.service.UserService;
import com.capgemini.investingtradingappuserclient.dto.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void post_User_success() throws Exception {
        UserDTO userDTO = new UserDTO("Jeff", "Smith", "999-233-312", "jsmith@gmail.com");
        String json = objectMapper.writeValueAsString(userDTO);
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8").content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void get_allUsers_success() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void get_UserFirstNameAndLastName_success() throws Exception {
        String firstName = "John";
        String lastName = "Smith";
        mockMvc.perform(get("/user?firstName=" + firstName + "&lastName=" + lastName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(userService.findByFirstNameAndLastName(firstName, lastName))));
    }

    @Test
    public void get_UserTeleNumbAndEmail_success() throws Exception {
        String teleNumb = "777-333-888";
        String email = "jsmith@gmail.com";
        mockMvc.perform(get("/user?teleNumb=" + teleNumb + "&email=" + email))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(userService.findByTeleNumbAndEmail(teleNumb, email))));
    }

    @Test
    public void put_User_success() throws Exception {
        UserDTO userDTO = new UserDTO("Jeff", "Smith", "999-233-312", "jsmith@gmail.com");
        User user = userService.save(userDTO);
        userDTO.setFirstName("Josh");
        String json = objectMapper.writeValueAsString(userDTO);
        mockMvc.perform(put("/user/" + user.getUserID())
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void delete_User_success() throws Exception {
        userService.save(new UserDTO("Jeff", "Smith", "999-233-312", "jsmith@gmail.com"));
        User user = userRepository.findUserByFirstNameAndLastName("Jeff", "Smith").get(0);
        mockMvc.perform(delete("/user?userID=" + user.getUserID())
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andExpect(status().isNoContent());
    }
}