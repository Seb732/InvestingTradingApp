package controller.integrationtest;

import com.capgemini.investingtradingappposition.service.PositionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = {ObjectMapper.class, PositionService.class})
@AutoConfigureMockMvc

public class PositionControllerIntegrationTest {

    @Autowired
    private PositionService positionService;

    //    @Autowired
//    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


//    @Test
//    public void post_position_success() throws Exception {
//        String firstName = "Jeff";
//        String lastName = "Smith";
//        User user = userService.save(new UserDTO(firstName, lastName, "999-233-312", "jsmith@gmail.com"));
//        PositionDTO positionDTO = new PositionDTO();
//        positionDTO.setCompanyID(4);
//        positionDTO.setSize(3);
//        positionDTO.setTicker(99);
//        positionDTO.setPositionStatus(PositionStatus.OPEN);
//        String json = objectMapper.writeValueAsString(positionDTO);
//        mockMvc.perform(MockMvcRequestBuilders.post("/position?investingAccountID=" + user.getInvestingAccount().getInvestingAccountID())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .characterEncoding("utf-8")
//                        .content(json)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//    }


    @Test
    public void get_allPositions_success() throws Exception {
        mockMvc.perform(get("/position"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void get_positionByCompanyID_success() throws Exception {
        long id = 3;
        mockMvc.perform(get("/position/?companyID=" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(positionService.getByCompanyID(id))));
    }

    @Test
    public void get_positionByOpenDateAfter_success() throws Exception {
        String openDate = "06-07-2022 00:00";
        mockMvc.perform(get("/position/?openDate=" + openDate))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(positionService.getByOpenDateAfter
                        (LocalDateTime.parse("06-07-2022 00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))))));
    }

    @Test
    public void get_positionByTickerGreaterThan_success() throws Exception {
        double ticker = 98;
        mockMvc.perform(get("/position/?ticker=" + ticker))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(positionService.getPositionByTickerGreaterThan(ticker))));
    }

//    @Test
//    public void put_position_success() throws Exception {
//        String firstName = "Jeff";
//        String lastName = "Smith";
//        User user = userService.save(new UserDTO(firstName, lastName, "999-233-312", "jsmith@gmail.com"));
//        PositionDTO positionDTO = new PositionDTO();
//        positionDTO.setCompanyID(4);
//        positionDTO.setSize(3);
//        positionDTO.setTicker(99);
//        positionDTO.setPositionStatus(PositionStatus.OPEN);
//        Position position = positionService.save(user.getInvestingAccount().getInvestingAccountID(), positionDTO);
//        positionDTO.setSize(6);
//        String json = objectMapper.writeValueAsString(positionDTO);
//        mockMvc.perform(MockMvcRequestBuilders.put("/position/" + position.getPositionID())
//                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
//                        .content(json).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }

//    @Test
//    public void delete_position_success() throws Exception {
//        String firstName = "Jeff";
//        String lastName = "Smith";
//        User user = userService.save(new UserDTO(firstName, lastName, "999-233-312", "jsmith@gmail.com"));
//        PositionDTO positionDTO = new PositionDTO();
//        positionDTO.setCompanyID(4);
//        positionDTO.setSize(3);
//        positionDTO.setTicker(99);
//        positionDTO.setPositionStatus(PositionStatus.OPEN);
//        Position position = positionService.save(user.getInvestingAccount().getInvestingAccountID(), positionDTO);
//        mockMvc.perform(MockMvcRequestBuilders.delete("/position?positionID=" + position.getPositionID())
//                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
//                .andExpect(status().isNoContent());
//    }
}
