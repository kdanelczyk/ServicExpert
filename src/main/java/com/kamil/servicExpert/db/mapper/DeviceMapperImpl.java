package com.kamil.servicExpert.db.mapper;

import java.util.Date;
import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.Device;
import com.kamil.servicExpert.model.Device.DeviceDtoGet;
import com.kamil.servicExpert.model.Device.DeviceDtoGetDetails;
import com.kamil.servicExpert.model.Device.DeviceDtoPost;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DeviceMapperImpl implements DeviceMapper{
	
	@Override
	public Device devicePostToDevice(DeviceDtoPost deviceDtoPost) {
		if(deviceDtoPost == null) {
			return null;
		}		
		return 	Device
				.builder()
				.customerPhoneNumber(deviceDtoPost.getCustomerPhoneNumber())
				.nameOfCustomer(deviceDtoPost.getNameOfCustomer())
				.dateOfReceipt(new Date())
				.repaired(false)
				.build();
	}

	@Override
	public DeviceDtoGet deviceToDeviceGet(Device device) {
		if(device == null) {
			return null;
		}		
		return 	DeviceDtoGet
				.builder()
				.nameOfCustomer(device.getNameOfCustomer())
				.repaired(device.isRepaired())
				.build();
	}

	@Override
	public DeviceDtoGetDetails deviceToDeviceGetDetails(Device device) {
		if(device == null) {
			return null;
		}		
		return 	DeviceDtoGetDetails
				.builder()
				.customerPhoneNumber(device.getCustomerPhoneNumber())
				.nameOfCustomer(device.getNameOfCustomer())
				.dateOfReceipt(device.getDateOfReceipt())
				.repaired(false)
				.build();
	}

}
