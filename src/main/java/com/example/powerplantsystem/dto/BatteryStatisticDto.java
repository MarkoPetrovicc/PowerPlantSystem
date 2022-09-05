package com.example.powerplantsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BatteryStatisticDto {

    public List<String> name;
    public double totalWattCapacity;
    public double averageWattCapacity;


}
