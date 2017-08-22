package com.project.writing.Service.interf;

import com.project.writing.VO.BoardVO;

import java.util.List;

public interface BoardService {
    public List<BoardVO>  outputboardVO(String board_writer);
    public int countBoardVO();
    public List<BoardVO> callboardVO(int board_number);
    public BoardVO writelookboardVO(int idx);
}
