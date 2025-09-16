package com.company.devicemanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.company.devicemanagement.enums.State;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    private String name;

    private String brand;

    private State state;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Date creationTime;

}
