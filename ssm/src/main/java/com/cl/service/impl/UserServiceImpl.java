package com.cl.service.impl;

import com.cl.dao.UserDAO;
import com.cl.model.UserDO;
import com.cl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public void insert(UserDO userDO) {
        userDAO.insert(userDO);
    }
}
