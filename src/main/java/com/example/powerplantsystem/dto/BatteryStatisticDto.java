package com.example.powerplantsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BatteryStatisticDto {

    private List<String> name;
    private double totalWattCapacity;
    private double averageWattCapacity;


}
