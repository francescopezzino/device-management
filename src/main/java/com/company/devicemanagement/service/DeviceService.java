package com.company.devicemanagement.service;

import com.company.devicemanagement.dto.DeviceDTO;
import com.company.devicemanagement.entity.DeviceEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface DeviceService {
    static List<DeviceDTO> getDeviceDTOS(List<DeviceEntity> entityDevices) {
        List<DeviceDTO> dtoDevices = new ArrayList<>();
        for (DeviceEntity entity : entityDevices) {
            DeviceDTO dto = new DeviceDTO();
            BeanUtils.copyProperties(entity, dto);
            dtoDevices.add(dto);
        }
        return dtoDevices;
    }

    DeviceDTO createNewDevice(DeviceDTO deviceDto);

    // TODO: Creation tme cannot be updated.
    // TODO: Name and brand proper:es cannot be updated if the device is in use.
    DeviceDTO updateDevice(DeviceDTO deviceDTO);

    Optional<DeviceDTO> getDeviceById(Long id);

    List<DeviceDTO> fetchAllDevices();

    List<DeviceDTO> fetchDevicesByBrand(String brand);

    List<DeviceDTO> fetchDevicesByState(String state);

    @DeleteMapping
    void deleteDevice(Long id);
}
