package com.project.writing.Mapper;

import com.project.writing.VO.BoardVO;

import java.util.List;

public interface BoardMapper {
    public List<BoardVO>  outputboard(String board_writer);
    public int countBoard();
    public List<BoardVO> callboard(int board_number);
    public BoardVO writelookboard(int idx);
}
