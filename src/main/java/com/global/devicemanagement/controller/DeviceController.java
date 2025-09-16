package com.global.devicemanagement.controller;

import com.global.devicemanagement.entity.DeviceEntity;
import com.global.devicemanagement.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceEntity> getDeviceById(@PathVariable Long id) {
        Optional<DeviceEntity> device = deviceService.getDeviceById(id);
        return device.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<DeviceEntity> updateDevice(@PathVariable Long id, @RequestBody DeviceEntity deviceEntity) {
        Optional<DeviceEntity> deviceOptional = deviceService.getDeviceById(id);
        if (deviceOptional.isPresent()) {
            DeviceEntity updatedDevice = deviceOptional.get();
            updatedDevice.setName(deviceEntity.getName());
            updatedDevice.setBrand(deviceEntity.getBrand());
            return ResponseEntity.ok(deviceService.updateDevice(updatedDevice));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addNew")
    public ResponseEntity<DeviceEntity> createDevice(@RequestBody DeviceEntity deviceEntity) {
        return ResponseEntity.ok(deviceService.saveDevice(deviceEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}
