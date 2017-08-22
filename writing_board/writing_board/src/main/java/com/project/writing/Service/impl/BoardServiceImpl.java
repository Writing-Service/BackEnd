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
    public List<BoardVO> outputboardVO(String board_writer){ return boardMapper.outputboard(board_writer); }

    @Override
    public int countBoardVO(){ return boardMapper.countBoard(); };

    @Override
    public List<BoardVO> callboardVO(int board_number){ return boardMapper.callboard(board_number);}

    @Override
    public BoardVO writelookboardVO(int idx){return boardMapper.writelookboard(idx);};

}
