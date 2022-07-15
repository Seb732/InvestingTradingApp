package controller.unittest;

import com.capgemini.investingtradingappposition.Mappers;
import com.capgemini.investingtradingappposition.entity.Position;
import com.capgemini.investingtradingappposition.service.PositionService;
import com.capgemini.investingtradingapppositionclient.dto.PositionDTO;
import com.capgemini.investingtradingapppositionclient.dto.PositionStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = {ObjectMapper.class, ModelMapper.class})
@AutoConfigureMockMvc
public class PositionControllerUnitTest {

    @MockBean
    private PositionService positionService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void post_position_success() throws Exception {
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setCompanyID(4);
        positionDTO.setSize(3);
        positionDTO.setTicker(99);
        positionDTO.setPositionStatus(PositionStatus.OPEN);

        when(positionService.save(any(Long.class), any(PositionDTO.class))).thenReturn(modelMapper.map(positionDTO, Position.class));
        String json = objectMapper.writeValueAsString(positionDTO);
        mockMvc.perform(post("/position/?investingAccountID=5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8").content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(positionService).save(5, positionDTO);
    }

    @Test
    public void get_allPositions_success() throws Exception {
        mockMvc.perform(get("/position")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
        verify(positionService).getAll();
    }

    @Test
    public void get_positionByCompanyID_success() throws Exception {
        mockMvc.perform(get("/position/?companyID=3")).andExpect(status().isOk());
        verify(positionService).getByCompanyID(3);
    }

    @Test
    public void get_positionByOpenDateAfter_success() throws Exception {
        mockMvc.perform(get("/position/?openDate=06-07-2022 00:00")).andExpect(status().isOk());
        verify(positionService).getByOpenDateAfter(LocalDateTime.parse("06-07-2022 00:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }

    @Test
    public void get_positionByTickerGreaterThan_success() throws Exception {
        mockMvc.perform(get("/position/?ticker=98")).andExpect(status().isOk());
        verify(positionService).getPositionByTickerGreaterThan(98);
    }

    @Test
    public void put_position_success() throws Exception {
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setCompanyID(4);
        positionDTO.setSize(3);
        positionDTO.setTicker(99);
        positionDTO.setPositionStatus(PositionStatus.OPEN);
        long positionID = 7;
        when(positionService.update(positionID, positionDTO)).thenReturn(modelMapper.map(positionDTO, Position.class));
        String json = objectMapper.writeValueAsString(positionDTO);
        mockMvc.perform(put("/position/" + positionID).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
        verify(positionService).update(positionID, positionDTO);
    }

    @Test
    public void delete_position_success() throws Exception {
        long positionID = 7;
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setCompanyID(4);
        positionDTO.setSize(3);
        positionDTO.setTicker(99);
        positionDTO.setPositionStatus(PositionStatus.OPEN);
        mockMvc.perform(delete("/position?positionID=" + positionID).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andExpect(status().isNoContent());
    }
}
