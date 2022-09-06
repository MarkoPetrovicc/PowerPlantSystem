package com.example.powerplantsystem.service;

import com.example.powerplantsystem.model.Battery;
import com.example.powerplantsystem.repository.BatteryRepository;
import com.example.powerplantsystem.serviceimpl.BatteryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

class BatteryServiceTest {

    @Mock
    private BatteryRepository batteryRepository;
    private BatteryService batteryService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        batteryService =new BatteryServiceImpl(batteryRepository);

    }
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
    void shouldSaveAllBatteries(){

        batteryService.saveBatteries(batteries);

        verify(batteryRepository).saveAll(batteries);
    }
    @Test
    void shouldReturnAllBatteriesInRange(){
        int from = anyInt();
        int to = anyInt();

        batteryService.getBatteriesInRange(from, to);

        verify(batteryRepository).findByPostcodeBetweenOrderByNameAsc(from, to);
    }
    @Test
    void shouldReturnTotalCapacity(){

        double totalCapacity = batteryService.calculateTotalWattCapacity(batteries);
        double batterySum=batteries.stream()
                .map(Battery::getWattCapacity)
                .reduce(0.0,Double::sum);

        assertEquals(batterySum, totalCapacity);
    }
    @Test
    void shouldReturnAverageCapacity(){

        double averageCapacity = batteryService.calculateAverageWattCapacity(batteries);
        double batteryAvgCapacity = batteries.stream()
                .map(Battery::getWattCapacity)
                .collect(Collectors.summarizingDouble(Double::doubleValue))
                .getAverage();

        assertEquals(batteryAvgCapacity, averageCapacity);
    }
}
