package com.hand.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hand.ssm.anno.ParamDeal;
import com.hand.ssm.dao.GoodsDao;
import com.hand.ssm.dto.BaseRequest;
import com.hand.ssm.dto.Goods;

@ParamDeal
@RestController
public class GoodsController {
	
	@Autowired
	GoodsDao goodsDao;
	
	static boolean isTrue = true;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/good")
	public String getGood(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response){
		long id = Long.valueOf(request.getParameter("id"));
		
		Goods goods = goodsDao.getGoodByPrimaryKey(id);
		modelAndView.addObject("good", goods);
		return "success";
	}

	@PostMapping("/testIt")
	public String testIt(@RequestBody BaseRequest<String> request) throws Exception{
		logger.info("{}", request.toString());
		isTrue = !isTrue;
		logger.info("ooooooooooo,{}", isTrue);
		if(isTrue) {
			int j = 10/0;
		}
		return "eeee";
	}
	

	@PostMapping("/testIt1")
	public String testIt1() throws Exception{
		isTrue = !isTrue;
		logger.info("ooooooooooo,{}", isTrue);
		if(isTrue) {
			int j = 10/0;
		}
		return "eeee";
	}
}
