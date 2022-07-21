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
		diaryVO.setMid("user13");
		
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
		diaryVO.setMid("user13");
		
		
		
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
		String std = date;
		Date date1 = new Date(sdf.parse(strDate).getTime());
		diaryVO.setMid("user13");
		diaryVO.setDdate(date1);
		diaryVO.setDddo("aa");
		model.addAttribute("aade",dao.detail(diaryVO));
		diaryVO.setDddo("bb");
		model.addAttribute("bbde",dao.detail(diaryVO));
		diaryVO.setDddo("cc");
		model.addAttribute("ccde",dao.detail(diaryVO));
		model.addAttribute("std",std);
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
	@RequestMapping("/modify")
    public String modify(Model model, DiaryVO diaryVO, 
    		@RequestParam("dddo") String dddo,
    		@RequestParam("date") String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = date;
		
		Date date1 = new Date(sdf.parse(strDate).getTime());
		
		diaryVO.setMid("user13");
		diaryVO.setDdate(date1);
		diaryVO.setDddo(dddo);
		 
		
		model.addAttribute("modify",dao.detail(diaryVO));
		model.addAttribute("con",dao.con(diaryVO));
		model.addAttribute("dat",date);
		return "contents/diary/modify";
        
    }
	@RequestMapping("/modifyde")
public String modifyde(HttpServletResponse response,DiaryVO diaryVO)throws IOException, ParseException { 
		diaryVO.setMid("user13");
		dao.diaryde(diaryVO);
		dao.detade(diaryVO);
		
		dao.insert(diaryVO);
		String[] ddnames = diaryVO.getDdname().split(",");
		List<String> ddnameList = new ArrayList<String>();	
		DiaryVO res = new DiaryVO();
		for(int i=0;i< ddnames.length;i++) {
			
			ddnameList.add(ddnames[i]);
			
			
		}

		  for(int i=0;i< ddnameList.size();i++) { 
			  res = dao.fonlist(ddnames[i]);
			  
			  res.setMid("user13");
			  res.setDdate(diaryVO.getDdate());
			  res.setDddo(diaryVO.getDddo());
			  dao.insertdetail(res);
		  }

                return "contents/diary/succ"; 

	}
	@RequestMapping("/delete")
public String delete(HttpServletResponse response,DiaryVO diaryVO)throws IOException, ParseException { 
		diaryVO.setMid("user13");
		dao.diaryde(diaryVO);
		dao.detade(diaryVO);
		
		
                return "contents/diary/succ"; 

	}
	
	@RequestMapping("weekwrite")
    public String weekwrite(){
        return "contents/diary/weekwrite";
    }
	
	@RequestMapping(value="insert") 
	public String insert(HttpServletResponse response,DiaryVO diaryVO)throws IOException, ParseException { 
		
		diaryVO.setMid("user13");
		dao.insert(diaryVO);
		
		String[] ddnames = diaryVO.getDdname().split(",");
		List<String> ddnameList = new ArrayList<String>();	
		DiaryVO res = new DiaryVO();
		for(int i=0;i< ddnames.length;i++) {
			
			ddnameList.add(ddnames[i]);
			
			
		}

		  for(int i=0;i< ddnameList.size();i++) { 
			  res = dao.fonlist(ddnames[i]);
			  
			  res.setMid("user13");
			  res.setDdate(diaryVO.getDdate());
			  res.setDddo(diaryVO.getDddo());
			  dao.insertdetail(res);
		  }

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