package com.project.writing.Service.impl;

import com.project.writing.Mapper.BoardMapper;
import com.project.writing.Service.interf.BoardService;
import com.project.writing.VO.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("com.project.writing.Service.impl.BoardServiceImpl")
public class BoardServiceImpl implements BoardService {

    @Autowired
    public BoardMapper boardMapper;

    @Override
    public List<BoardVO> selectBoardVO(String author_id){
        return boardMapper.selectBoard(author_id);
    };

    @Override
    public BoardVO testVO(String author_id){return boardMapper.test(author_id);}

}
