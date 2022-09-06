package com.example.powerplantsystem.controller;

import com.example.powerplantsystem.dto.BatteryDto;
import com.example.powerplantsystem.model.Battery;
import com.example.powerplantsystem.service.BatteryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BatteryController.class)
class BatteryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    BatteryService batteryService;


    Battery battery1 = Battery.builder()
            .id(1)
            .name("Test1")
            .postcode(1)
            .wattCapacity(100.0)
            .build();
    Battery battery2 = Battery.builder()
            .id(6)
            .name("Test2")
            .postcode(2)
            .wattCapacity(100.0)
            .build();
    Battery battery3 = Battery.builder()
            .id(17)
            .name("TestBattery3")
            .postcode(5)
            .wattCapacity(100.0)
            .build();
@Test
void addBatteries_success() throws Exception {
    List<Battery> batteries = Arrays.asList(battery1, battery2, battery3);
    List<Battery> batteriesWithNullIds = batteries.stream().map(battery -> Battery.builder()
                    .id(0)
                    .name(battery.getName())
                    .postcode(battery.getPostcode())
                    .wattCapacity(battery.getWattCapacity())
                    .build())
            .collect(Collectors.toList());
    List<BatteryDto> batteriesDto = batteries.stream()
            .map(battery -> modelMapper.map(battery, BatteryDto.class))
            .collect(Collectors.toList());

    Mockito.when(batteryService.saveBatteries(batteriesWithNullIds)).thenReturn(batteries);

    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/batteries")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.objectMapper.writeValueAsString(batteriesDto));

    mockMvc.perform(mockRequest)
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].name").exists())
            .andExpect(jsonPath("$[0].postcode").exists())
            .andExpect(jsonPath("$[0].wattCapacity").exists())
            .andExpect(jsonPath("$[1].name").exists())
            .andExpect(jsonPath("$[1].postcode").exists())
            .andExpect(jsonPath("$[1].wattCapacity").exists())
            .andExpect(jsonPath("$[2].name").exists())
            .andExpect(jsonPath("$[2].postcode").exists())
            .andExpect(jsonPath("$[2].wattCapacity").exists())
            .andReturn();
}
}
