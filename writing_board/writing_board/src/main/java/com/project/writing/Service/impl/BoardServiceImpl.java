package com.project.writing.Service.impl;

import com.project.writing.Mapper.BoardMapper;
import com.project.writing.Service.interf.BoardService;
import com.project.writing.VO.BoardVO;
import com.project.writing.VO.SubmitBoardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("com.project.writing.Service.impl.BoardServiceImpl")
public class BoardServiceImpl implements BoardService {

    @Autowired
    public BoardMapper boardMapper;

    @Override
    public List<BoardVO> outputboardVO(String board_writer){ return boardMapper.catchBoard(board_writer); }

    @Override
    public int countBoardVO(){ return boardMapper.countBoard(); };

    @Override
    public List<BoardVO> callboardVO(int board_number){ return boardMapper.callboard(board_number);}

    @Override
    public BoardVO writelookboardVO(int idx){return boardMapper.writelookboard(idx);};

    @Override
    public void insertboolVO(@Param("board_catcher")String board_catcher,@Param("idx")int idx){boardMapper.insertbool(board_catcher,idx);}

    @Override
    public void tempinsertboardVO(@Param("author_save") String author_save ,@Param("idx") int idx,@Param("board_catcher") String board_catcher){boardMapper.tempinsertboard(author_save,idx,board_catcher);};

    @Override
    public void commitinsertboardVO(BoardVO boardVO){boardMapper.commitinsertboard(boardVO);};

    @Override
    public void commitinsertbeforeboardVO(@Param("author_save") String author_save ,@Param("idx") int idx){boardMapper.commitinsertbeforeboard(author_save, idx);};

    @Override
    public void submitinsertboardVO(BoardVO boardVO){ boardMapper.submitinsertboard(boardVO);};

    @Override
    public void submitupdateboardVO(@Param("datatime")Date datatime, @Param("content")String content, @Param("board_bool") String board_bool,@Param("idx") int idx){boardMapper.submitupdateboard(datatime, content, board_bool,idx);}

    @Override
    public void commmitdeletecatcherboardVO(@Param("author_save")String author_save,@Param("board_catcher")String board_catcher,@Param("idx") int idx){ boardMapper.commmitdeletecatcherboard(author_save,board_catcher,idx);};

    @Override
    public BoardVO choiceboardVO(@Param("write_number")int write_number,@Param("board_number") int board_number){return boardMapper.choiceboard(write_number, board_number);};

    @Override
    public void transdeleteboardVO(int board_number){ boardMapper.transdeleteboard(board_number);}

    @Override
    public void transsubmitboardVO(SubmitBoardVO boardVO){boardMapper.transsubmitboard(boardVO);}

    @Override
    public int libraryselectnumberVO(@Param("author_id") String author_id,@Param("page")int page){ return boardMapper.libraryselectnumber(author_id,page);};

    @Override
    public List<SubmitBoardVO> libraryselectVO(@Param("board_Number")String board_Number){return boardMapper.libraryselect(board_Number);}
}
