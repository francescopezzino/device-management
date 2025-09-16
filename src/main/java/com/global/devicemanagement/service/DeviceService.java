package com.global.devicemanagement.service;

import com.global.devicemanagement.entity.DeviceEntity;
import com.global.devicemanagement.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Optional<DeviceEntity> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }

    public DeviceEntity saveDevice(DeviceEntity device) {
        return deviceRepository.save(device);
    }

    // Creation tme cannot be updated.
    // Name and brand proper:es cannot be updated if the device is in use.
    public DeviceEntity updateDevice(DeviceEntity device) {
        return deviceRepository.save(device);
    }

    // In use devices cannot be deleted
    @DeleteMapping
    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }
}
