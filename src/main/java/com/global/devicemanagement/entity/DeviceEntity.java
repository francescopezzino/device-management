package com.global.devicemanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.plaf.nimbus.State;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;

    @Column(name = "BRAND", nullable = false)
    private String brand;

    @Enumerated(EnumType.STRING)
    private State state;
}
