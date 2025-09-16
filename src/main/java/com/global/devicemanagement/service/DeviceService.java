package com.global.devicemanagement.service;

import com.global.devicemanagement.entity.Device;
import com.global.devicemanagement.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository taskRepository) {
        this.deviceRepository = taskRepository;
    }

    public Optional<Device> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }

    public Device saveDevice(Device task) {
        return deviceRepository.save(task);
    }
    @DeleteMapping
    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }
}
