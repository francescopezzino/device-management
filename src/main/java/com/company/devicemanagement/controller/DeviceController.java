package com.company.devicemanagement.controller;

import com.company.devicemanagement.dto.DeviceDTO;
import com.company.devicemanagement.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /**
     * Create a new device
     * @param deviceDTO
     * @return ResponseEntity<DeviceEntity>
     */
    @Operation( description= "This method is used to create a new device")
    @PostMapping("/createNew")
    public ResponseEntity<DeviceDTO> createDevice(@Parameter(
            name = "deviceDTO",
            example = "device information",
            required = true
    )@RequestBody DeviceDTO deviceDTO) {
        deviceDTO.setCreationTime(new Date());
        return ResponseEntity.ok(deviceService.createNewDevice(deviceDTO));
    }

    /**
     * Fully and/or par:ally update an existing device
     * @param id
     * @param deviceDTO
     * @return ResponseEntity<DeviceDTO>
     */
    @PutMapping("/{id}")
    public ResponseEntity<DeviceDTO> updateDevice(@PathVariable Long id, @RequestBody DeviceDTO deviceDTO) {
        Optional<DeviceDTO> deviceOptional = deviceService.getDeviceById(id);
        if (!deviceOptional.isEmpty()) {
            DeviceDTO updatedDevice = deviceOptional.get();
            updatedDevice.setName(deviceDTO.getName());
            updatedDevice.setBrand(deviceDTO.getBrand());
            updatedDevice.setState(deviceDTO.getState());
            return ResponseEntity.ok(deviceService.updateDevice(updatedDevice));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Fetch a single device
     * @param id
     * @return ResponseEntity<DeviceDTO>
     */
    @GetMapping("/{id}")
    public ResponseEntity<DeviceDTO> getDeviceById(@PathVariable Long id) {
        Optional<DeviceDTO> deviceDTOOptional = deviceService.getDeviceById(id);
        if (deviceDTOOptional.isPresent()) {
            return ResponseEntity.ok(deviceDTOOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Fetch all devices
     * @return ResponseEntity<List<DeviceDTO>>
     */
    @GetMapping("/fetchAll")
    public ResponseEntity<List<DeviceDTO>> getAllDevices() {
        List<DeviceDTO> dtoDevices = deviceService.fetchAllDevices();
        if(!dtoDevices.isEmpty()) {
            //return new ResponseEntity<List<DeviceDTO>>(dtoDevices, OK);
            return ResponseEntity.ok(dtoDevices);
        }
        return ResponseEntity.noContent().build();
    }


    /**
     * Fetch devices by brand
     * @param brand
     * @return ResponseEntity<List<DeviceDTO>>
     */
    @GetMapping("/fetchAllByBrand")
    public ResponseEntity<List<DeviceDTO>> getDevicesByBrand(@RequestParam(value = "brand", required = true) String brand) {
        List<DeviceDTO> dtoDevices = deviceService.fetchDevicesByBrand(brand);
        if(!dtoDevices.isEmpty()) {
            //return new ResponseEntity<List<DeviceDTO>>(dtoDevices, OK);
            return ResponseEntity.ok(dtoDevices);
        }
        return ResponseEntity.noContent().build();
    }

    /**
     * Fetch devices by state
     * @param state
     * @return ResponseEntity<List<DeviceDTO>>
     */
    @GetMapping("/fetchAllByState")
    public ResponseEntity<List<DeviceDTO>> getDevicesByState(@RequestParam(value = "state", required = true) String state) {
        List<DeviceDTO>  dtoDevices = deviceService.fetchDevicesByState(state);
        if(!dtoDevices.isEmpty()) {
            //return new ResponseEntity<List<DeviceDTO>>(dtoDevices, OK);
            return ResponseEntity.ok(dtoDevices);
        }
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete a single device
     * @param id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {

        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}
