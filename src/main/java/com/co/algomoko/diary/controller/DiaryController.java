package com.co.algomoko.diary.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.diary.domain.DiaryVO;
import com.co.algomoko.diary.domain.RecipeVO;
import com.co.algomoko.diary.domain.ReqVO;
import com.co.algomoko.diary.mapper.DiaryMapper;
import com.co.algomoko.food.domain.FoodVO;
import com.co.algomoko.food.mapper.FoodMapper;

import groovyjarjarantlr4.v4.parse.ANTLRParser.action_return;


@Controller
public class DiaryController {
	
	@Autowired DiaryMapper dao;
	@Autowired FoodMapper dao1;
	
	@RequestMapping("sicmain")
    public String sicmain(Model model, DiaryVO diaryVO){
		Calendar calendar= Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		diaryVO.setDdate(calendar.getTime());
		diaryVO.setMid("user12");
		System.out.println(diaryVO);
		model.addAttribute("todaysic",dao.findDay(diaryVO));
		model.addAttribute("resultCal",dao.jukcal(diaryVO));
		
		
        return "contents/diary/sicmain";
    }
	@RequestMapping("cal")
    public String cal(){
        return "contents/diary/cal";
    }
	@RequestMapping("myre")
    public String myre(){
        return "contents/diary/myre";
    }
	@RequestMapping("/succ")
    public String succ(){
		
        return "contents/diary/succ";
    }
	@RequestMapping("/todaysic")
    public String todaysic(Model model, DiaryVO diaryVO){
		Calendar calendar= Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		diaryVO.setDdate(calendar.getTime());
		diaryVO.setMid("user12");
		
		
		
		model.addAttribute("todaysic",dao.sicDay(diaryVO));
		model.addAttribute("resultCal",dao.resultCal(diaryVO));
		model.addAttribute("ddd",calendar.getTime());
		return "contents/diary/todaysic";
    }
	
	
	@RequestMapping(value="daysic") 
    public String daysic(@RequestParam("date") String date, Model model, DiaryVO diaryVO, HttpServletResponse response)
    	throws IOException, ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = date;
		
		Date date1 = new Date(sdf.parse(strDate).getTime());
		
		diaryVO.setMid("user12");
		diaryVO.setDdate(date1);
		System.out.println(dao.custom(diaryVO));
		
		model.addAttribute("todaysic",dao.custom(diaryVO));
		model.addAttribute("resultCal",dao.resultCal(diaryVO));
		
		
		return "contents/diary/daysic";
    }
	@RequestMapping("weeklybest")
    public String weeklybest(Model model,RecipeVO recpvo){
		
		model.addAttribute("rank",dao.rerank(recpvo));
		
		
        return "contents/diary/weeklybest";
    }
	@RequestMapping("write")
    public String write(){
        return "contents/diary/write";
    }
	@RequestMapping("writema")
    public String writema(){
        return "contents/diary/writema";
    }
	@RequestMapping("modify")
    public String modify(Model model, DiaryVO diaryVO, String dddo){
		Calendar calendar= Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		diaryVO.setDdate(calendar.getTime());
		diaryVO.setMid("user12");
			
		
		diaryVO.setDddo(dddo);
		model.addAttribute("todaysic",dao.detail(diaryVO));
		model.addAttribute("con",dao.con(diaryVO));
		System.out.println(dddo);
		return "contents/diary/modify";
        
    }
	
	@RequestMapping("weekwrite")
    public String weekwrite(){
        return "contents/diary/weekwrite";
    }
	
	@RequestMapping("insert") 
	public String insert(DiaryVO frm)throws IOException, ParseException { 
		frm.setMid("user12");
		
		System.out.println(frm.getAmount());
		System.out.println(frm.getDdname());
		System.out.println(frm.getCal());
		
		
                return "contents/diary/succ"; 
		
        
        
	}
   
	
	@RequestMapping("/flist")
	@ResponseBody
	public List<FoodVO> flist(@RequestParam("btnbtn") String btnbtn, Model model){
		
		FoodVO foodvo = new FoodVO();
		foodvo.setIng(btnbtn);
		
		return dao1.fList(foodvo);
		
    }
	
	
}