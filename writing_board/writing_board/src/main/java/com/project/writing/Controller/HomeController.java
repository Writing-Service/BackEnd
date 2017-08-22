package com.project.writing.Controller;

import java.beans.Encoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.project.writing.Service.interf.BoardService;
import com.project.writing.Service.interf.UserService;
import com.project.writing.VO.BoardVO;
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
    private static int value_id = 0;

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
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
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
        int idx = Math.abs(random.nextInt(boardServiceimpl.countBoardVO()) + 1);
        BoardVO findBoardDAO = new BoardVO();
        List<BoardVO> boardVO = boardServiceimpl.callboardVO(idx);
        while (take == false) {  // 이미 올라간 글인지 확인
            while (same == false) { // 글의 작가와 겹치는지 확인
                while (pre_same == false) { // 글의 이전 작가와 겹치지 않는지 확인 할것
                    for (int i = 0; i < boardVO.size(); i++) {
                        // 마지막인 경우
                        if (i == (boardVO.size() - 1)) {
                            if (!(author_id.equals(boardVO.get(i).getPre_author_id()))) { // 다를 경우
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
                                if (!(author_id.equals(boardVO.get(i).getPre_author_id()))) { // 다를 경우
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
                    for (int i = 0; i < boardVO.size(); i++) {
                        // 마지막인 경우
                        if (i == (boardVO.size() - 1)) {
                            if (!(author_id.equals(boardVO.get(i).getAuthor_id()))) { // 다를 경우
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
                            if (!(author_id.equals(boardVO.get(i).getAuthor_id()))) { // 다를 경우
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
            if(Integer.parseInt(findBoardDAO.getBoard_bool()) == 0){
                take = true;
            } // 글이 작성자가 있는 경우
            else{
                idx = Math.abs(random.nextInt(boardServiceimpl.countBoardVO()) + 1);
                boardVO = boardServiceimpl.callboardVO(idx);
                same = false;
                pre_same = false;
                take = false;
            }
        }

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

    // 라이브러리 이동
    @RequestMapping(value = "/library", method = RequestMethod.GET)
    public String library(Locale locale, Model model) {

        return "library";
    }

    // 뉴스피드 이용
    @RequestMapping(value = "/newsfeed", method = RequestMethod.GET)
    public String newsfeed(Locale locale, Model model) {
        return "newsfeed";
    }



}
