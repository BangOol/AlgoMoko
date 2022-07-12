package com.co.algomoko.diary.controller;

import java.time.LocalDateTime;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.co.algomoko.diary.domain.DiaryVO;
import com.co.algomoko.diary.mapper.DiaryMapper;


@Controller
public class DiaryController {
	
	@Autowired DiaryMapper dao;
	
	
	@RequestMapping("sicmain")
    public String sicmain(Model model, DiaryVO diaryVO){
		Calendar calendar= Calendar.getInstance();
		calendar.set(2022, 6, 12);
		diaryVO.setDdate(calendar.getTime());
		diaryVO.setMid("user3");
		model.addAttribute("todaysic",dao.sicDay(diaryVO));
		model.addAttribute("localDateTime",LocalDateTime.now());
		
		
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
		calendar.set(2022, 6, 11);
		diaryVO.setDdate(calendar.getTime());
		diaryVO.setMid("user3");
		model.addAttribute("todaysic",dao.findDay(diaryVO));
		model.addAttribute("localDateTime",LocalDateTime.now());
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
	
	
	
}
