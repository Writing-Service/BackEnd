package com.project.writing.Controller;

import java.beans.Encoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.project.writing.Service.interf.BoardService;
import com.project.writing.Service.interf.UserService;
import com.project.writing.VO.BoardVO;
import com.project.writing.VO.SubmitBoardVO;
import com.project.writing.VO.UserVO;
import com.sun.deploy.nativesandbox.comm.Response;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private static int board_number = 0; // 글 번호를 관리하는 static 변수
    private static int library_number = 0; // 라이브러리 시 부르는 변수

	@Resource(name="com.project.writing.Service.impl.UserServiceImpl")
	private UserService userServiceimpl;

    @Resource(name="com.project.writing.Service.impl.BoardServiceImpl")
    private BoardService boardServiceimpl;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("홈페이지를 만듭니다", locale);
		return "home";
	}


	// 로그인 후 인덱스로 이동 - 저장했던 글 목록을 불러옴
	@RequestMapping(value = "/login" , method =  RequestMethod.POST)
	public String login(HttpServletRequest request,Locale locale, Model model, UserVO userVO){
        List<UserVO> user_VO = userServiceimpl.selectuserVO();
        for(UserVO vo : user_VO){
            if(userVO.getId().equals(vo.getId())){
                if(userVO.getPassword().equals(vo.getPassword())){
                    logger.info("로그인 되었습니다",locale);
                    // 로그인 후 글 목록 불러오기
                    List<BoardVO> boardVO = boardServiceimpl.outputboardVO(userVO.getId());
                    int size = boardVO.size();
                    request.getSession().setAttribute("author_id",vo.getId());
                    request.getSession().setAttribute("author",vo.getNickname());
                    model.addAttribute("size",size);
                    model.addAttribute("author",vo.getNickname());
                    model.addAttribute("author_id",vo.getId());
                    model.addAttribute("BoardVO",boardVO);
                    return "index";
                }
            }
        }
		return "home";
	}


    // 글감 받아오는 부분
    @RequestMapping(value = "/reqthread" , method = RequestMethod.POST , produces="application/json;charset=utf8")
    @ResponseBody
    public String req(Locale locale, Model model,HttpSession httpSession) {
        // 작가의 ID를 불러와서 처리
        boolean pre_same = false; // 이전 글 저자와 겹치는지 파악
        boolean same = false; // 글 저자와 겹치는지 파악
        boolean take = false; // 작성하려는 글이 이미 글감으로 불러와져 있는지 파악
        String author_id = httpSession.getAttribute("author_id").toString();
        Random random = new Random(System.currentTimeMillis());
        int idx = Math.abs(random.nextInt(boardServiceimpl.countBoardVO()) + 1); // 현재 작성된 글의 갯수 호출
        BoardVO findBoardDAO = new BoardVO();
        List<BoardVO> boardVO = boardServiceimpl.callboardVO(idx);
        int min = boardVO.size() - 2;
        int max = boardVO.size() ;
        if(min<0)min =0;
        while (take == false) {  // 이미 올라간 글인지 확인
            while (same == false) { // 글의 작가와 겹치는지 확인
                while (pre_same == false) { // 글의 이전 작가와 겹치지 않는지 확인 할것
                    for (int i = min; i < max; i++) {
                        // 마지막인 경우
                        if (i == (boardVO.size() - 1)) {
                            if (!(author_id.equals(boardVO.get(i).getBefore_author_id()))) { // 다를 경우
                                findBoardDAO = boardVO.get(i);
                                pre_same = true;
                                break;
                            }else {
                                idx = Math.abs(random.nextInt(boardServiceimpl.countBoardVO()) + 1);
                                boardVO = boardServiceimpl.callboardVO(idx);
                                pre_same = false;
                                break;
                            }
                        }
                        else {
                                if (!(author_id.equals(boardVO.get(i).getBefore_author_id()))) { // 다를 경우
                                    pre_same = true;
                                } else { // 같을 경우
                                    idx = Math.abs(random.nextInt(boardServiceimpl.countBoardVO()) + 1);
                                    boardVO = boardServiceimpl.callboardVO(idx);
                                    pre_same = false;
                                    break;
                                 }
                        }
                     }
                }
                // 글의 작가와 겹치는지 확인 할 것
                    for (int i = min; i < max; i++) {
                        // 마지막인 경우
                        if (i == (boardVO.size() - 1)) {
                            if (!(author_id.equals(boardVO.get(i).getPre_author_id()))) { // 다를 경우
                                same = true;
                                break;
                            }else {
                                idx = Math.abs(random.nextInt(boardServiceimpl.countBoardVO()) + 1);
                                boardVO = boardServiceimpl.callboardVO(idx);
                                same = false;
                                pre_same = false;
                                break;
                            }
                        }
                        else {
                            if (!(author_id.equals(boardVO.get(i).getPre_author_id()))) { // 다를 경우
                                same = true;
                            } else { // 같을 경우
                                idx = Math.abs(random.nextInt(boardServiceimpl.countBoardVO()) + 1);
                                boardVO = boardServiceimpl.callboardVO(idx);
                                same = false;
                                pre_same = false;
                                break;
                            }
                        }
                    }
                }
            // 글이 작성자가 없는 경우
            if((findBoardDAO.getBoard_catcher() == null) && Integer.parseInt(findBoardDAO.getBoard_bool()) == 2){
                take = true;
                break;
            } // 글이 작성자가 있는 경우
            else{
                idx = Math.abs(random.nextInt(boardServiceimpl.countBoardVO()) + 1);
                boardVO = boardServiceimpl.callboardVO(idx);
                same = false;
                pre_same = false;
                take = false;
            }
        }

        // 날짜는 GMT+0900으로 계산됨 (mysql은 DB)
        boardServiceimpl.insertboolVO(author_id,Integer.parseInt(findBoardDAO.getIdx()));
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        String idx_id = "thread_id" + findBoardDAO.getIdx();
        jsonObject.put("thread_id",idx_id);
        jsonObject.put("author",jsonObject2);
        jsonObject2.put("name",findBoardDAO.getAuthor().toString());
        jsonObject.put("date",findBoardDAO.getDatatime().toString());
        jsonObject.put("datetime",findBoardDAO.getDatatime().toString());
        jsonObject.put("content", findBoardDAO.getContent().toString());
        jsonObject.put("idx",findBoardDAO.getIdx().toString());
        logger.info("글을 받아옵니다",locale);
        return jsonObject.toString();
}



    // 첫 글쓰기 기능
    @RequestMapping(value = "/firstwrite", method = RequestMethod.GET)
    public String first_write(Locale locale, Model model,HttpSession httpSession) {
        String author = httpSession.getAttribute("author").toString();
        model.addAttribute("author",author);
        return "firstwrite";
    }

    // 첫 글 쓴 이후 완료 기능
    @RequestMapping(value = "/firstwriting", method = RequestMethod.POST)
    public String first_writing(Locale locale, Model model,HttpSession httpSession,String author_save) {
        BoardVO boardVO = new BoardVO();
        String author_id = httpSession.getAttribute("author_id").toString();
        String author = httpSession.getAttribute("author").toString();
        Date date = new Date();
        int CountBoard = board_number + 1;
        model.addAttribute("author",author);
        boardVO.setAuthor(author);
        boardVO.setAuthor_id(author_id);
        boardVO.setDatatime(date);
        boardVO.setContent(author_save);
        boardVO.setPre_author_id(author_id);
        boardVO.setWrite_number(Integer.toString(1));
        boardVO.setBoard_number(Integer.toString(CountBoard));
        boardVO.setBoard_bool(Integer.toString(2));
        board_number ++;
        boardServiceimpl.submitinsertboardVO(boardVO);
        return "index";
    }

    // 글 작성시 글 작성 페이지로 이동
    @RequestMapping(value = "/writing", method = RequestMethod.GET)
    public String write(Locale locale, Model model,HttpSession httpSession,int idx) {
        String author_id = httpSession.getAttribute("author_id").toString();
        String author = httpSession.getAttribute("author").toString();
        BoardVO boardVO = boardServiceimpl.writelookboardVO(idx);
        model.addAttribute("BoardVO",boardVO);
        model.addAttribute("author",author);
        model.addAttribute("author_id",author_id);
        return "write";
    }
    // 임시저장 기능

    @RequestMapping(value = "/temporary", method = RequestMethod.POST)
    public String temporary(Locale locale, Model model,HttpSession httpSession,String author_save,String idx) {
        String author_id = httpSession.getAttribute("author_id").toString();
	    boardServiceimpl.tempinsertboardVO(author_save,Integer.parseInt(idx),author_id);
        return "index";
    }

    // 작성완료 기능
    @RequestMapping(value = "/commit", method = RequestMethod.POST)
    public String commit(Locale locale, Model model,HttpSession httpSession,String author_save,String idx) {
        String author_id = httpSession.getAttribute("author_id").toString();
        String author = httpSession.getAttribute("author").toString();
        BoardVO beforeboardVO = boardServiceimpl.writelookboardVO(Integer.parseInt(idx));
        BoardVO boardVO = new BoardVO();
        int temp = 1;
        String temp1 = Integer.toString(temp);

        int tempnumber = Integer.parseInt(beforeboardVO.getWrite_number()) + 1;
        String temp2 =  Integer.toString(tempnumber);

        // 값 집어넣기
        boardVO.setAuthor(author);
        boardVO.setAuthor_id(author_id);
        boardVO.setContent(author_save);
        boardVO.setBefore_author_id(beforeboardVO.getAuthor_id());
        boardVO.setPre_author_id(author_id);
        boardVO.setWrite_number(temp2);
        boardVO.setBoard_number(beforeboardVO.getBoard_number());
        boardVO.setBoard_bool(temp1);
        boardServiceimpl.commitinsertbeforeboardVO(author_save,Integer.parseInt(idx));
        boardServiceimpl.commitinsertboardVO(boardVO);
        return "index";
    }


    // 임시 저장 시에 뷰로 해당 화면을 만들어 주는 것
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String go_index(Locale locale, Model model,HttpSession httpSession) {
        String author_id = httpSession.getAttribute("author_id").toString();
        String author = httpSession.getAttribute("author").toString();
        List<BoardVO> boardVO = boardServiceimpl.outputboardVO(author_id);
        int size = boardVO.size();
        model.addAttribute("size",size);
        model.addAttribute("author",author);
        model.addAttribute("author_id",author_id);
        model.addAttribute("BoardVO",boardVO);
        return "index";
    }

    // 제출 기능
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(Locale locale, Model model,HttpSession httpSession,String author_save,String idx) {
        String author_id = httpSession.getAttribute("author_id").toString();
        String author = httpSession.getAttribute("author").toString();
	    BoardVO beforeboardVO = boardServiceimpl.writelookboardVO(Integer.parseInt(idx));
	    int write_number = Integer.parseInt(beforeboardVO.getWrite_number()) + 1;
        int board_number = Integer.parseInt(beforeboardVO.getBoard_number());
        int temp = 2;
        BoardVO tempboardVO = boardServiceimpl.choiceboardVO(write_number,board_number);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
        BoardVO boardVO = new BoardVO();
        //기존의 작성완료 글이 있는지를 확인하여서 상태를 파악함
        // 기존의 글이 있을시에 업데이트
        try{
                String A = tempboardVO.getIdx();
                boardServiceimpl.submitupdateboardVO(date,author_save,Integer.toString(temp),Integer.parseInt(tempboardVO.getIdx()));
                boardServiceimpl.commmitdeletecatcherboardVO(null,null,Integer.parseInt(idx));


                // 글 7개일시 제출
                if(write_number == 7){
                    List<BoardVO> submitVO = boardServiceimpl.callboardVO(board_number);

                    for(int j = 0; j < 7 ; j++) {
                        SubmitBoardVO sboardVO = new SubmitBoardVO();
                        sboardVO.setAuthor(submitVO.get(j).getAuthor());
                        sboardVO.setAuthor_id(submitVO.get(j).getAuthor_id());
                        sboardVO.setWrite_number(submitVO.get(j).getWrite_number());
                        sboardVO.setBoard_number(submitVO.get(j).getBoard_number());
                        sboardVO.setDatatime(submitVO.get(j).getDatatime());
                        sboardVO.setVote(null);
                        sboardVO.setStar(null);
                        boardServiceimpl.transsubmitboardVO(sboardVO);
                    }
                    boardServiceimpl.transdeleteboardVO(board_number);
                }
                return "index";

        }
        catch(Exception e){ // 기존의 글이 없을시 새롭게 세팅함
            boardVO.setAuthor(author);
            boardVO.setAuthor_id(author_id);
            boardVO.setDatatime(date);
            boardVO.setContent(author_save);
            boardVO.setBefore_author_id(beforeboardVO.getAuthor_id());
            boardVO.setPre_author_id(author_id);
            boardVO.setWrite_number(Integer.toString(write_number));
            boardVO.setBoard_number(Integer.toString(board_number));
            boardVO.setBoard_bool(Integer.toString(temp));
            boardServiceimpl.submitinsertboardVO(boardVO);
            boardServiceimpl.commmitdeletecatcherboardVO(null,null,Integer.parseInt(idx));
           // 기존의 글은 삭제하고 입력
            if(write_number == 7) {
                List<BoardVO> submitVO = boardServiceimpl.callboardVO(board_number);

                for (int j = 0; j < 7; j++) {
                    SubmitBoardVO sboardVO = new SubmitBoardVO();
                    sboardVO.setDatatime(submitVO.get(j).getDatatime());
                    sboardVO.setVote(null);
                    sboardVO.setStar(null);
                    sboardVO.setAuthor(submitVO.get(j).getAuthor());
                    sboardVO.setAuthor_id(submitVO.get(j).getAuthor_id());
                    sboardVO.setWrite_number(submitVO.get(j).getWrite_number());
                    sboardVO.setBoard_number(submitVO.get(j).getBoard_number());
                    boardServiceimpl.transsubmitboardVO(sboardVO);

                }
                boardServiceimpl.transdeleteboardVO(board_number);
            }

            return "index";
        }

    }


    // 글 삭제 기능
    @RequestMapping(value = "/writing", method = RequestMethod.POST)
    public String delete(Locale locale, Model model,HttpSession httpSession,String author_save,String idx) {
        return "write";
    }



    // 라이브러리 이동
    @RequestMapping(value = "/library", method = RequestMethod.GET)
    public String library(Locale locale, Model model,HttpSession httpSession) {
        String author = httpSession.getAttribute("author").toString();
        String author_id = httpSession.getAttribute("author_id").toString();
        int BoardNumber = boardServiceimpl.libraryselectnumberVO(author_id,library_number);
        int Library_Number = BoardNumber;
        List<SubmitBoardVO> submitBoardVO = boardServiceimpl.libraryselectVO(Integer.toString(Library_Number));
        model.addAttribute("SubmitBoardVO",submitBoardVO);
        model.addAttribute("author",author);
        return "library";
    }

    // 뉴스피드 이용
    @RequestMapping(value = "/newsfeed", method = RequestMethod.GET)
    public String newsfeed(Locale locale, Model model,HttpSession httpSession) {
        String author = httpSession.getAttribute("author").toString();
        model.addAttribute("author",author);
	    return "newsfeed";
    }



}
