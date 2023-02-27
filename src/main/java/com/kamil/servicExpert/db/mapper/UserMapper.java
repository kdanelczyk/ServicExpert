package com.kamil.servicExpert.db.mapper;

import org.mapstruct.Mapper;

import com.kamil.servicExpert.db.model.User;
import com.kamil.servicExpert.model.User.UserDtoGet;
import com.kamil.servicExpert.model.User.UserDtoGetDetails;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserDtoGet userToUserGet(User user);
	
	UserDtoGetDetails usersToUserGetDetails(User user);

}
