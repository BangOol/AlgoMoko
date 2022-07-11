package com.co.algomoko.diary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.co.algomoko.diary.service.DiaryServiceImpl;


@Controller
public class DiaryController {
	
	DiaryServiceImpl diaryService;
	
	
	@GetMapping("sicmain")
    public String sicmain(){
        return "contents/diary/sicmain";
    }
	@GetMapping("cal")
    public String cal(){
        return "contents/diary/cal";
    }
	@GetMapping("myre")
    public String myre(){
        return "contents/diary/myre";
    }
	@GetMapping("resu")
    public String resu(){
        return "contents/diary/resu";
    }
	@GetMapping("todaysic")
    public String todaysic(){
        return "contents/diary/todaysic";
    }
	@GetMapping("weeklybest")
    public String weeklybest(){
        return "contents/diary/weeklybest";
    }
	@GetMapping("write")
    public String write(){
        return "contents/diary/write";
    }
	@GetMapping("writema")
    public String writema(){
        return "contents/diary/writema";
    }
	
	
	
}
