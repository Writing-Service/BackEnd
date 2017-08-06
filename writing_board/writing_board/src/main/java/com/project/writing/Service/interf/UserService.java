package com.project.writing.Service.interf;

import com.project.writing.VO.BoardVO;
import com.project.writing.VO.UserVO;

import java.util.List;

public interface UserService {

    public List<UserVO> selectuserVO();
    public UserVO selectestVO(String idx);
}
