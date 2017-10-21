package net.sonworks.hellospring;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sonworks.hellospring.service.HomeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private HomeService homeService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/**
	 *  文字列を返すAPIサンプル
	 * @return
	 */
	@RequestMapping(value="/string", method=RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody String getString() {
		logger.info("##### getString()");
		return "Hello, Spring!";
	}
	
	/**
	 * 配列を返すAPIサンプル
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody Map<String, Object> getList() {
		logger.info("##### getList()");
		Map<String, Object> list = new HashMap<String, Object>();
		list.put("test", "list");
		return list;
	}

	/**
	 * 引数を返すAPIサンプル
	 * @param param1
	 * @return
	 */
	@RequestMapping(value="/param/{param1}", method=RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody String getParameter(@PathVariable("param1") String param1) {
		logger.info("##### getParameter() param1=" + param1);
		return param1;
	}
	
    /**
     * DBに接続して検索結果を返す
     * 
     * @return
     */
    @RequestMapping(value = "/search/db", method = RequestMethod.GET, headers="Accept=application/json")
    public @ResponseBody String searchDb() {
        String result = homeService.searchDb();
        return result;
    }
}
