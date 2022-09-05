package com.example.powerplantsystem.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="battery")
public class Battery {

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String name;
    private int postcode;
    private double wattCapacity;

}
