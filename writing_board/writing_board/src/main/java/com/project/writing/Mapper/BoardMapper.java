package com.project.writing.Mapper;

import com.project.writing.VO.BoardVO;

import java.util.List;

public interface BoardMapper {

    public List<BoardVO> selectBoard(String author_id);
    public BoardVO test(String author_id);
}
