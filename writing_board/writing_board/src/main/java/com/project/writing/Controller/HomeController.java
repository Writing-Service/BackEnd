package com.project.writing.Controller;

import java.beans.Encoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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


	// 로그인 후 인덱스로 이동
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	public String login(HttpServletRequest request, Locale locale, Model model, UserVO userVO){
        List<UserVO> uservo = userServiceimpl.selectuserVO();
        for(UserVO vo : uservo){
            if(userVO.getId().equals(vo.getId())){
                if(userVO.getPassword().equals(vo.getPassword())) {
                    logger.info("로그인 되었습니다!.", locale);
                    String ID = vo.getId();
                    String Nickname = vo.getNickname();
                    List<BoardVO> boardVO = boardServiceimpl.selectBoardVO(ID);
                    request.getSession().setAttribute("ID",vo.getId());
                    request.getSession().setAttribute("Nickname",vo.getNickname());
                    model.addAttribute("Boardinformation",boardVO);
                    model.addAttribute("Nickname",Nickname);
                    model.addAttribute("ID",ID);
                    // 그사람의 게시글 확인하기
                    return "index";
                }
            }
        }
		return "home";
	}
    // 글 추가 시 작동되는 Controller
    @RequestMapping(value = "/reqthread" , method = RequestMethod.POST , produces="application/json;charset=utf8")
    @ResponseBody
    public String req(Locale locale, Model model) {

	    BoardVO boardVO = boardServiceimpl.testVO("test1");

	    JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        String Test = "test" + value_id;
        value_id++;
        jsonObject.put("thread_id",Test);
        jsonObject.put("author",jsonObject2);
        jsonObject2.put("name","names");
        jsonObject.put("datetime",boardVO.getDatatime().toString());
        jsonObject.put("content","테스트");
	    logger.info("test",locale);
	    return jsonObject.toString();
    }

    // 라이브러리 이동
    @RequestMapping(value = "/library", method = RequestMethod.GET)
    public String library(Locale locale, Model model) {

        return "library";
    }

    // 글 작성시 글 작성 페이지로 이동
    @RequestMapping(value = "/writing", method = RequestMethod.GET)
    public String write(Locale locale, Model model, int idx) {
        UserVO userVO = userServiceimpl.selectestVO(Integer.toString(idx));
        String Temp = userVO.getNickname();
        model.addAttribute("Author",Temp);
        logger.info("홈페이지를 만듭니다", locale);
        return "write";
    }

    // 뉴스피드 이용
    @RequestMapping(value = "/newsfeed", method = RequestMethod.GET)
    public String newsfeed(Locale locale, Model model) {
        return "newsfeed";
    }



}
