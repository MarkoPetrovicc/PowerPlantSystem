package com.example.powerplantsystem.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    @Min(value = 1, message = "Post code must be greater than zero.")
    private int postcode;
    private double wattCapacity;

}
