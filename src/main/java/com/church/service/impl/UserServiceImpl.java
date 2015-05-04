package com.church.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.church.dao.UserDao;
import com.church.model.UserModel;
import com.church.service.UserService;
import com.church.utils.ServiceException;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	/*@Autowired
	private PwdEncoder pwdEncoder;*/
	@Autowired
	private UserDao userDao;

	public UserModel doLogin(UserModel user) throws ServiceException {

		UserModel userModel = queryByPhone(user.getPhone());
		if(userModel == null) {
			throw new ServiceException("error.account.not.exist");
		}
		if(userModel.getStatus() != UserModel.STATUS_TRUE) {
			throw new ServiceException("error.account.status.not.normal");
		}
		/*if(!pwdEncoder.isPasswordValid(userModel.getPassword(), user.getPassword())) {
			throw new ServiceException("error.account.password.invalid");
		}*/
		return userModel;
	}
	
	public UserModel queryByPhone(String phone){
		return userDao.queryByPhone(phone);
	}
}