package com.project.writing.Service.interf;

import com.project.writing.VO.BoardVO;

import java.util.List;

public interface BoardService {
    public List<BoardVO> selectBoardVO(String author_id);
    public BoardVO testVO(String author_id);
}
