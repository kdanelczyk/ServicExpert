package com.kamil.servicExpert.db.mapper;

import org.mapstruct.Mapper;

import com.kamil.servicExpert.db.model.Device;
import com.kamil.servicExpert.model.Device.DeviceGet;
import com.kamil.servicExpert.model.Device.DeviceGetDetails;
import com.kamil.servicExpert.model.Device.DevicePost;

@Mapper(componentModel = "spring")
public interface DeviceMapper {
	
	Device deviceInputToDevice(DevicePost devicePost);
	
	DeviceGet deviceToDeviceGet(Device device);
	
	DeviceGetDetails deviceToDeviceGetDetails(Device device);
}
