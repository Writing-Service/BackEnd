package com.project.writing.Service.impl;

import com.project.writing.Mapper.UserMapper;
import com.project.writing.Service.interf.UserService;
import com.project.writing.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("com.project.writing.Service.impl.UserServiceImpl")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVO> selectuserVO(){ return userMapper.selectuser();};
}
