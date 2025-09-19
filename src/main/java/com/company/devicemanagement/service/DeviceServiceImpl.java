package com.company.devicemanagement.service;

import com.company.devicemanagement.dto.DeviceDTO;
import com.company.devicemanagement.entity.DeviceEntity;
import static com.company.devicemanagement.enums.State.ACTIVE;
import com.company.devicemanagement.exception.BusinessException;
import com.company.devicemanagement.exception.ErrorModel;
import com.company.devicemanagement.repository.DeviceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public DeviceDTO createNewDevice(DeviceDTO deviceDto) {
        DeviceEntity deviceEntity = new DeviceEntity();
        BeanUtils.copyProperties(deviceDto, deviceEntity);
        deviceEntity = deviceRepository.save(deviceEntity);
        DeviceDTO retDto =  new DeviceDTO();
        BeanUtils.copyProperties(deviceEntity, retDto);
        return retDto;
    }

    // TODO: Creation tme cannot be updated.
    // TODO: Name and brand proper:es cannot be updated if the device is in use.
    @Override
    public DeviceDTO updateDevice(DeviceDTO deviceDTO) {
        Optional<DeviceEntity> deviceEntityOptional =  deviceRepository.findById(deviceDTO.getId());
        if (deviceEntityOptional.isPresent()) {
            DeviceEntity deviceEntity = deviceEntityOptional.get();
            if (deviceEntity.getState().equals(ACTIVE)) {
                List<ErrorModel> errorModelList = new ArrayList<>();
                ErrorModel errorModel = new ErrorModel();
                if (!deviceDTO.getName().equals(deviceEntity.getName())) {
                    errorModel.setCode("DEVICE_NAME_NOT_UPDATABLE");
                    errorModel.setMessage("Device in use cannot update name");
                    errorModelList.add(errorModel);
                }
                if (!deviceDTO.getBrand().equals(deviceEntity.getBrand())) {
                    errorModel.setCode("DEVICE_BRAND_NOT_UPDATABLE");
                    errorModel.setMessage("Device in use cannot update brand");
                    errorModelList.add(errorModel);
                }
                if(errorModelList.size() > 0){
                    throw new BusinessException(errorModelList);
                }
            }
        }
        DeviceEntity deviceEntity = new DeviceEntity();
        BeanUtils.copyProperties(deviceDTO, deviceEntity);

        deviceEntity = deviceRepository.save(deviceEntity);
        DeviceDTO retDto =  new DeviceDTO();
        BeanUtils.copyProperties(deviceEntity, retDto);
       return retDto;
    }

    @Override
    public Optional<DeviceDTO> getDeviceById(Long id) {
        Optional<DeviceEntity> deviceEntityOptional =  deviceRepository.findById(id);
        DeviceDTO deviceDTO = null;
        if (deviceEntityOptional.isPresent()) {
            deviceDTO = new DeviceDTO();
            BeanUtils.copyProperties(deviceEntityOptional.get(), deviceDTO);
        }
        return Optional.ofNullable(deviceDTO);
    }

    @Override
    public List<DeviceDTO> fetchAllDevices() {
        List<DeviceEntity> entityDevices = (List<DeviceEntity>) deviceRepository.findAll();
        return DeviceService.getDeviceDTOS(entityDevices);
    }

    @Override
    public List<DeviceDTO> fetchDevicesByBrand(String brand) {
        List<DeviceEntity> entityDevices = (List<DeviceEntity>) deviceRepository.findAllByBrand(brand);
        return DeviceService.getDeviceDTOS(entityDevices);
    }

    @Override
    public List<DeviceDTO> fetchDevicesByState(String state) {
        List<DeviceEntity> entityDevices = (List<DeviceEntity>) deviceRepository.findAllByState(state);
        return DeviceService.getDeviceDTOS(entityDevices);
    }

    @DeleteMapping
    @Override
    public void deleteDevice(Long id) {
        Optional<DeviceEntity> deviceEntityOptional =  deviceRepository.findById(id);
        if (deviceEntityOptional.isPresent()) {
            DeviceEntity deviceEntity = deviceEntityOptional.get();
            if (deviceEntity.getState().equals(ACTIVE)) {
                List<ErrorModel> errorModelList = new ArrayList<>();
                ErrorModel errorModel = new ErrorModel();
                errorModel.setCode("DEVICE_IS_ACTIVE");
                errorModel.setMessage("Device in use cannot delete");
                errorModelList.add(errorModel);
                throw new BusinessException(errorModelList);
            }
        }
        deviceRepository.deleteById(id);
    }

}
