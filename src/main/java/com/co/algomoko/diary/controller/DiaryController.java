package com.co.algomoko.diary.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.diary.domain.DiaryVO;
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
		diaryVO.setMid("user3");
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
	@RequestMapping("resu")
    public String resu(){
        return "contents/diary/resu";
    }
	@RequestMapping("/todaysic")
    public String todaysic(Model model, DiaryVO diaryVO){
		Calendar calendar= Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		diaryVO.setDdate(calendar.getTime());
		diaryVO.setMid("user3");
		
		
		
		model.addAttribute("todaysic",dao.sicDay(diaryVO));
		model.addAttribute("resultCal",dao.resultCal(diaryVO));
		model.addAttribute("ddd",calendar.getTime());
		return "contents/diary/todaysic";
    }
	@RequestMapping("weeklybest")
    public String weeklybest(){
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
		diaryVO.setMid("user3");
			
		
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
	@RequestMapping("weekwrite2")
    public String weekwrite2(){
        return "contents/diary/weekwrite2";
    }
	
	@RequestMapping("/flist")
	@ResponseBody
	public List<FoodVO> flist(@RequestParam("btnbtn") String btnbtn, Model model){
		System.out.println(btnbtn);
		FoodVO foodvo = new FoodVO();
		foodvo.setIng(btnbtn);
		return dao1.fList(foodvo);
    }
	
	
}
