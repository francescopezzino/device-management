package com.global.devicemanagement.service;

import com.global.devicemanagement.dto.DeviceDTO;
import com.global.devicemanagement.entity.DeviceEntity;
import com.global.devicemanagement.repository.DeviceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public DeviceEntity createNewDevice(DeviceDTO deviceDto) {
        DeviceEntity deviceEntity = new DeviceEntity();
        BeanUtils.copyProperties(deviceDto, deviceEntity);
        return deviceRepository.save(deviceEntity);
    }

    // TODO: Creation tme cannot be updated.
    // TODO: Name and brand proper:es cannot be updated if the device is in use.
    public DeviceDTO updateDevice(DeviceDTO deviceDTO) {
        DeviceEntity deviceEntity = new DeviceEntity();
        BeanUtils.copyProperties(deviceDTO, deviceEntity);
        deviceEntity = deviceRepository.save(deviceEntity);
        DeviceDTO retDto =  new DeviceDTO();
        BeanUtils.copyProperties(deviceEntity, new DeviceDTO());
       return retDto;
    }

    public  Optional<DeviceDTO> getDeviceById(Long id) {
        Optional<DeviceEntity> deviceEntityOptional =  deviceRepository.findById(id);
        DeviceDTO deviceDTO = null;
        if (deviceEntityOptional.isPresent()) {
            deviceDTO = new DeviceDTO();
            BeanUtils.copyProperties(deviceEntityOptional.get(), deviceDTO);
        }
        return Optional.ofNullable(deviceDTO);
    }

    public List<DeviceDTO> fetchAllDevices() {
        List<DeviceEntity> entityDevices = (List<DeviceEntity>) deviceRepository.findAll();
        return getDeviceDTOS(entityDevices);
    }

    public List<DeviceDTO> fetchDevicesByBrand(String brand) {
        List<DeviceEntity> entityDevices = (List<DeviceEntity>) deviceRepository.findAllByBrand(brand);
        return getDeviceDTOS(entityDevices);
    }

    public List<DeviceDTO> fetchDevicesByState(String state) {
        List<DeviceEntity> entityDevices = (List<DeviceEntity>) deviceRepository.findAllByState(state);
        return getDeviceDTOS(entityDevices);
    }

    // TODO: In use devices cannot be deleted
    @DeleteMapping
    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    private static List<DeviceDTO> getDeviceDTOS(List<DeviceEntity> entityDevices) {
        List<DeviceDTO> dtoDevices = new ArrayList();
        for (DeviceEntity entity : entityDevices) {
            DeviceDTO dto = new DeviceDTO();
            BeanUtils.copyProperties(entity, dto);
            dtoDevices.add(dto);
        }
        return dtoDevices;
    }
}
