package com.kamil.servicExpert.db.mapper;

import com.kamil.servicExpert.db.model.User;
import com.kamil.servicExpert.model.User.UserDtoGet;
import com.kamil.servicExpert.model.User.UserDtoGetDetails;

public interface UserMapper {
	
	UserDtoGet userToUserGet(User user);
	
	UserDtoGetDetails usersToUserGetDetails(User user);
	
}
