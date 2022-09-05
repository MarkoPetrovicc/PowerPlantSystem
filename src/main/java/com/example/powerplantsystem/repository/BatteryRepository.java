package com.example.powerplantsystem.repository;

import com.example.powerplantsystem.model.Battery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatteryRepository extends JpaRepository<Battery, Integer> {

    List<Battery> findByPostcodeBetweenOrderByNameAsc(int from, int to);

}
