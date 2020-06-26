package com.cl.multimaven.core.dao;


import com.cl.multimaven.core.model.UserDO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserDAO{

    int insert(UserDO record);

    UserDO selectByPrimaryKey(Long id);

    int updateByPrimaryKey(UserDO record);

    int deleteByPrimaryKey(Long id);

    List<UserDO> selectList(Map<String, Object> map);

    int countList(Map<String, Object> map);

    UserDO selectByUserNameAndPassword(Map<String, Object> map);

}