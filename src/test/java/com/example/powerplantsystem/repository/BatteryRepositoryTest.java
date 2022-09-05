package com.example.powerplantsystem.repository;

import com.example.powerplantsystem.model.Battery;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class BatteryRepositoryTest {

    @Autowired
    private BatteryRepository batteryRepository;
    Battery battery1 = Battery.builder()
            .id(1)
            .name("Test1")
            .postcode(111)
            .wattCapacity(23.0)
            .build();
    Battery battery2 = Battery.builder()
            .id(6)
            .name("Test2")
            .postcode(112)
            .wattCapacity(742.0)
            .build();
    Battery battery3 = Battery.builder()
            .id(17)
            .name("TestBattery3")
            .postcode(113)
            .wattCapacity(221.0)
            .build();
    List<Battery> batteries = Arrays.asList(battery1, battery2, battery3);

    @Test
    void shouldSaveAll(){
        List<Battery> savedBatteries = batteryRepository.saveAll(batteries);

        assertEquals(batteries.size(), savedBatteries.size());
    }
    @Test
    void givenPostCode_shouldBeInRange(){

    }
}
