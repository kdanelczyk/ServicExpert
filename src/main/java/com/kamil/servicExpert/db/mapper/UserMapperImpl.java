package com.kamil.servicExpert.db.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kamil.servicExpert.db.model.User;
import com.kamil.servicExpert.model.User.UserDtoGet;
import com.kamil.servicExpert.model.User.UserDtoGetDetails;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class UserMapperImpl implements UserMapper{
	
	RepairMapper repairMapper;
	NoteMapper noteMapper;
 
	@Override
	public UserDtoGet userToUserGet(User user) {
		if(user == null) {
			return null;
		}		
		return 	UserDtoGet
				.builder()
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
	public UserDtoGetDetails usersToUserGetDetails(User user) {
		if(user == null) {
			return null;
		}		
		return 	UserDtoGetDetails
				.builder()
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
