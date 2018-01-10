package com.naver.news.view.relation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author yongseog.yun
 * 
 */
@RequestMapping("/view/relation")
@Controller
public class ViewController {

	@RequestMapping("/index")
	public ModelAndView view() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}

}
