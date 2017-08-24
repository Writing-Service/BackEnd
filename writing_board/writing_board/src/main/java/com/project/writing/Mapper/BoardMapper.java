package com.project.writing.Mapper;

import com.project.writing.VO.BoardVO;
import com.project.writing.VO.SubmitBoardVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BoardMapper {
    public List<BoardVO>  catchBoard(String board_catcher);
    public int countBoard();
    public List<BoardVO> callboard(int board_number);
    public BoardVO writelookboard(int idx);
    public void insertbool(@Param("board_catcher")String board_catcher,@Param("idx") int idx);
    public void tempinsertboard(@Param("author_save") String author_save ,@Param("idx") int idx,@Param("board_catcher") String board_catcher);
    public void commitinsertboard(BoardVO boardVO);
    public void commitinsertbeforeboard(@Param("author_save") String author_save ,@Param("idx") int idx);
    public void submitupdateboard(@Param("datatime")Date datatime, @Param("content")String content, @Param("board_bool") String board_bool,@Param("idx") int idx);
    public void submitinsertboard(BoardVO boardVO);
    public void commmitdeletecatcherboard(@Param("author_save")String author_save,@Param("board_catcher")String board_catcher,@Param("idx") int idx);
    public BoardVO choiceboard(@Param("write_number")int write_number,@Param("board_number") int board_number);


    public void transdeleteboard(int board_number);
    public void transsubmitboard(SubmitBoardVO boardVO);

    public int libraryselectnumber(@Param("author_id") String author_id,@Param("page")int page);
    public List<SubmitBoardVO> libraryselect(@Param("board_Number")String board_Number);

}

