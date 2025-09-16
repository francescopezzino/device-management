package com.global.devicemanagement.controller;

import com.global.devicemanagement.entity.Device;
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
    public ResponseEntity<Device> getTaskById(@PathVariable Long id) {
        Optional<Device> task = deviceService.getDeviceById(id);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Device> updateTask(@PathVariable Long id, @RequestBody Device device) {
        Optional<Device> taskOptional = deviceService.getDeviceById(id);
        if (taskOptional.isPresent()) {
            Device updatedTask = taskOptional.get();
            updatedTask.setName(device.getName());
            updatedTask.setBrand(device.getBrand());
            return ResponseEntity.ok(deviceService.saveDevice(updatedTask));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addNew")
    public ResponseEntity<Device> createTask(@RequestBody Device device) {
        return ResponseEntity.ok(deviceService.saveDevice(device));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}
