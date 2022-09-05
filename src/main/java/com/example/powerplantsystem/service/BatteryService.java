package com.example.powerplantsystem.service;

import com.example.powerplantsystem.model.Battery;

import java.util.List;

public interface BatteryService {

    List<Battery> saveBatteries(List<Battery> batteries);

    List<Battery> getBatteriesInRange(int from, int to);

    double calculateTotalWattCapacity(List<Battery> batteries);

    double calculateAverageWattCapacity(List<Battery> batteries);


}
