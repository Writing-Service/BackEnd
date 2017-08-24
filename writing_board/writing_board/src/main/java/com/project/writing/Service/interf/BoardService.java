package com.project.writing.Service.interf;

import com.project.writing.VO.BoardVO;
import com.project.writing.VO.SubmitBoardVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BoardService {
    public List<BoardVO>  outputboardVO(String board_writer);
    public int countBoardVO();
    public List<BoardVO> callboardVO(int board_number);
    public BoardVO writelookboardVO(int idx);
    public void insertboolVO(@Param("board_catcher")String board_catcher,@Param("idx")int idx);
    public void tempinsertboardVO(@Param("author_save") String author_save ,@Param("idx") int idx,@Param("board_catcher") String board_catcher);
    public void commitinsertboardVO(BoardVO boardVO);
    public void commitinsertbeforeboardVO(@Param("author_save") String author_save ,@Param("idx") int idx);
    public void submitupdateboardVO(@Param("datatime")Date datatime, @Param("content")String content, @Param("board_bool") String board_bool,@Param("idx") int idx);
    public void submitinsertboardVO(BoardVO boardVO);
    public void commmitdeletecatcherboardVO(@Param("author_save")String author_save,@Param("board_catcher")String board_catcher,@Param("idx") int idx);
    public BoardVO choiceboardVO(@Param("write_number")int write_number,@Param("board_number") int board_number);

    public void transdeleteboardVO(int board_number);
    public void transsubmitboardVO(SubmitBoardVO boardVO);


    public int libraryselectnumberVO(@Param("author_id") String author_id,@Param("page")int page);
    public List<SubmitBoardVO> libraryselectVO(@Param("board_Number")String board_Number);
}
