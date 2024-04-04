package com.kamil.servicExpert.db.mapper;


import com.kamil.servicExpert.db.model.Device;
import com.kamil.servicExpert.model.Device.DeviceDtoGet;
import com.kamil.servicExpert.model.Device.DeviceDtoGetDetails;
import com.kamil.servicExpert.model.Device.DeviceDtoPost;

public interface DeviceMapper {
	
	Device devicePostToDevice(DeviceDtoPost deviceDtoPost);
	
	DeviceDtoGet deviceToDeviceGet(Device device);
	
	DeviceDtoGetDetails deviceToDeviceGetDetails(Device device);
	
}
