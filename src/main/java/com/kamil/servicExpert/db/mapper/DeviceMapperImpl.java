package com.kamil.servicExpert.db.mapper;

import java.util.Date;
import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.Device;
import com.kamil.servicExpert.model.Device.DeviceGet;
import com.kamil.servicExpert.model.Device.DeviceGetDetails;
import com.kamil.servicExpert.model.Device.DevicePost;

@Component
public class DeviceMapperImpl implements DeviceMapper{
	
	@Override
	public Device deviceInputToDevice(DevicePost devicePost) {
		if(devicePost == null) {
			return null;
		}		
		return 	Device.builder()
				.customerPhoneNumber(devicePost.getCustomerPhoneNumber())
				.nameOfCustomer(devicePost.getNameOfCustomer())
				.dateOfReceipt(new Date())
				.repaired(false)
				.build();
	}

	@Override
	public DeviceGet deviceToDeviceGet(Device device) {
		if(device == null) {
			return null;
		}		
		return 	DeviceGet.builder()
				.nameOfCustomer(device.getNameOfCustomer())
				.repaired(device.isRepaired())
				.build();
	}

	@Override
	public DeviceGetDetails deviceToDeviceGetDetails(Device device) {
		if(device == null) {
			return null;
		}		
		return 	DeviceGetDetails.builder()
				.customerPhoneNumber(device.getCustomerPhoneNumber())
				.nameOfCustomer(device.getNameOfCustomer())
				.dateOfReceipt(device.getDateOfReceipt())
				.repaired(false)
				.build();
	}

}
