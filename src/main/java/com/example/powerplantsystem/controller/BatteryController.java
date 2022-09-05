package com.example.powerplantsystem.controller;

import com.example.powerplantsystem.dto.BatteryDto;
import com.example.powerplantsystem.dto.BatteryStatisticDto;
import com.example.powerplantsystem.model.Battery;
import com.example.powerplantsystem.service.BatteryService;
import com.example.powerplantsystem.utils.MapperUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/batteries")
public class BatteryController {

    private final BatteryService batteryService;

    public BatteryController(BatteryService batteryService) {
        this.batteryService = batteryService;
    }
    @PostMapping
    public ResponseEntity<List<BatteryDto>> saveBatteries(@RequestBody @NotEmpty List<BatteryDto> batteries){
        batteryService.saveBatteries(MapperUtils.mapAll(batteries, Battery.class));
        return new ResponseEntity<>(batteries, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<BatteryStatisticDto> getBatteryInRange(@RequestParam @Min(value = 1) @NotNull int from, @NotNull @RequestParam int to){
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
