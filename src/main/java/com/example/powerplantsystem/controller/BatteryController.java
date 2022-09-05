package com.example.powerplantsystem.controller;

import com.example.powerplantsystem.dto.BatteryDto;
import com.example.powerplantsystem.dto.BatteryStatisticDto;
import com.example.powerplantsystem.model.Battery;
import com.example.powerplantsystem.service.BatteryService;
import com.example.powerplantsystem.utils.MapperUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/batteries")
public class BatteryController {

    private final BatteryService batteryService;
    private final ModelMapper modelMapper;

    public BatteryController(BatteryService batteryService, ModelMapper modelMapper) {
        this.batteryService = batteryService;
        this.modelMapper = modelMapper;
    }
    @PostMapping
    public ResponseEntity<List<BatteryDto>> saveBatteries(@RequestBody List<BatteryDto> batteries){
        batteryService.saveBatteries(MapperUtils.mapAll(batteries, Battery.class));
        return new ResponseEntity<>(batteries, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<BatteryStatisticDto> getBatteryInRange(@RequestParam int from, @RequestParam int to){
        List<Battery> batteries = batteryService.getBatteriesInRange(from, to);
        List<String> batteryNames = batteries.stream().
                map(Battery::getName).
                collect(Collectors.toList());
       BatteryStatisticDto batteryStatisticDto = new BatteryStatisticDto(batteryNames,
                batteryService.calculateTotalWattCapacity(batteries),
                batteryService.calculateAverageWattCapacity(batteries));

        return new ResponseEntity<>(batteryStatisticDto, HttpStatus.OK);



    }
}
