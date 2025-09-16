package com.global.devicemanagement.entity;

import com.global.devicemanagement.enums.State;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity()
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Device_Table")
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", updatable = true, nullable = false)
    private String name;

    @Column(name = "BRAND", updatable = true, nullable = false)
    private String brand;

    @Column(name = "STATE", updatable = true, nullable = true)
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "CREATION_TIME", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

}
