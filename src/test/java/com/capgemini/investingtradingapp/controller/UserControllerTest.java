package com.capgemini.investingtradingapp.controller;

import com.capgemini.investingtradingapp.dto.UserDTO;
import com.capgemini.investingtradingapp.entity.User;
import com.capgemini.investingtradingapp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void post_User_success() throws Exception {
        UserDTO userDTO = new UserDTO("Jeff", "Smith", "999-233-312", "jsmith@gmail.com");

        when(userService.save(any(UserDTO.class))).thenReturn(modelMapper.map(userDTO, User.class));
        String json = objectMapper.writeValueAsString(userDTO);
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(userService).save(userDTO);
    }

    @Test
    public void get_allUsers_success() throws Exception {
        mockMvc.perform(get("/user")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
        verify(userService).getAll();
    }

    @Test
    public void get_UserFirstNameAndLastName_success() throws Exception {
        mockMvc.perform(get("/user?firstName=John&lastName=Smith"))
                .andExpect(status().isOk());
        verify(userService).findByFirstNameAndLastName("John", "Smith");
    }

    @Test
    public void get_UserTeleNumbAndEmail_success() throws Exception {
        mockMvc.perform(get("/user?teleNumb=777-333-888&email=jsmith@gmail.com"))
                .andExpect(status().isOk());
        verify(userService).findByTeleNumbAndEmail("777-333-888", "jsmith@gmail.com");
    }

    @Test
    public void put_User_success() throws Exception {
        UserDTO userDTO = new UserDTO("Jeff", "Smith", "999-233-312", "jsmith@gmail.com");
        when(userService.update(7, userDTO)).thenReturn(modelMapper.map(userDTO, User.class));
        String json = objectMapper.writeValueAsString(userDTO);
        mockMvc.perform(put("/user/7").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
        verify(userService).update(7, userDTO);
    }

    @Test
    public void delete_User_success() throws Exception {
        UserDTO userDTO = new UserDTO("Jeff", "Smith", "999-233-312", "jsmith@gmail.com");
        mockMvc.perform(delete("/user?userID=7").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andExpect(status().isNoContent());
    }
}