package com.kamil.servicExpert.db.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.User;
import com.kamil.servicExpert.model.User.UserGet;
import com.kamil.servicExpert.model.User.UserGetDetails;

@Component
public class UserMapperImpl implements UserMapper{
	
	@Autowired
	RepairMapper repairMapper;
	
	@Autowired
	NoteMapper noteMapper;

	@Override
	public UserGet userToUserGet(User user) {
		if(user == null) {
			return null;
		}		
		return 	UserGet.builder()
				.name(user.getName())
				.email(user.getEmail())
				.repairs(user.getRepairs()
						.stream()
						.map(repairMapper::repairToRepairGet)
						.collect(Collectors.toList()))
				.notes(user.getNotes()
						.stream()
						.map(noteMapper::noteToNoteGet)
						.collect(Collectors.toList()))
				.build();
	}

	@Override
	public UserGetDetails usersToUserGetDetails(User user) {
		if(user == null) {
			return null;
		}		
		return 	UserGetDetails.builder()
			.username(user.getUsername())
			.userPhoneNumber(user.getUserPhoneNumber())
			.name(user.getName())
			.email(user.getEmail())
			.repairs(user.getRepairs()
					.stream()
					.map(repairMapper::repairToRepairGet)
					.collect(Collectors.toList()))
			.notes(user.getNotes()
					.stream()
					.map(noteMapper::noteToNoteGet)
					.collect(Collectors.toList()))
			.build();
	}

}
