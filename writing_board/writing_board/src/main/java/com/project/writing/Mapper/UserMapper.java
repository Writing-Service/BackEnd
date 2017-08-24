package com.project.writing.Mapper;

import com.project.writing.VO.UserVO;

import java.util.List;

public interface UserMapper {
    public List<UserVO> selectuser();
    public List<UserVO> setuser();
    public void insertuser(UserVO uservo);
}
