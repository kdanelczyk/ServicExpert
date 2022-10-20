package com.kamil.servicExpert.db.mapper;

import org.mapstruct.Mapper;

import com.kamil.servicExpert.db.model.User;
import com.kamil.servicExpert.model.User.UserGet;
import com.kamil.servicExpert.model.User.UserGetDetails;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserGet userToUserGet(User user);
	
	UserGetDetails usersToUserGetDetails(User user);

}
