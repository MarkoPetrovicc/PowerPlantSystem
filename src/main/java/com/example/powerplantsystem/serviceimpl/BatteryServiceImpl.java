package com.example.powerplantsystem.serviceimpl;

import com.example.powerplantsystem.model.Battery;
import com.example.powerplantsystem.repository.BatteryRepository;
import com.example.powerplantsystem.service.BatteryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatteryServiceImpl implements BatteryService {

    private final BatteryRepository batteryRepository;

    public BatteryServiceImpl(BatteryRepository batteryRepository) {
        this.batteryRepository = batteryRepository;
    }

    @Override
    public List<Battery> saveBatteries(List<Battery> batteries) {
        return batteryRepository.saveAll(batteries);
    }

    @Override
    public List<Battery> getBatteriesInRange(int from, int to) {
        return batteryRepository.findByPostcodeBetweenOrderByNameAsc(from, to);
    }

    @Override
    public double calculateTotalWattCapacity(List<Battery> batteries) {
        return batteries.stream()
                .map(Battery::getWattCapacity)
                .reduce(0.0,Double::sum);
    }

    @Override
    public double calculateAverageWattCapacity(List<Battery> batteries) {
        return batteries.stream()
                .map(Battery::getWattCapacity)
                .collect(Collectors.summarizingDouble(Double::doubleValue))
                .getAverage();


    }
}
