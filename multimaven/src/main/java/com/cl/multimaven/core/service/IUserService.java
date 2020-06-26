package com.cl.multimaven.core.service;


import com.cl.multimaven.core.common.Page;
import com.cl.multimaven.core.model.UserDO;

public interface IUserService {

    int insert(UserDO record);

    UserDO selectByPrimaryKey(Long id);

    int updateByPrimaryKey(UserDO record);

    int deleteByPrimaryKey(Long id);

    Page<UserDO> page(String userName, Integer curPage, Integer pageSize);

    UserDO selectByUserNameAndPassword(String userName, String password);
}
