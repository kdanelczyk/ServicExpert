package com.kamil.servicExpert.db.mapper;

import org.mapstruct.Mapper;

import com.kamil.servicExpert.db.model.Device;
import com.kamil.servicExpert.model.Device.DeviceDtoGet;
import com.kamil.servicExpert.model.Device.DeviceDtoGetDetails;
import com.kamil.servicExpert.model.Device.DeviceDtoPost;

@Mapper(componentModel = "spring")
public interface DeviceMapper {
	
	Device deviceInputToDevice(DeviceDtoPost deviceDtoPost);
	
	DeviceDtoGet deviceToDeviceGet(Device device);
	
	DeviceDtoGetDetails deviceToDeviceGetDetails(Device device);
}
