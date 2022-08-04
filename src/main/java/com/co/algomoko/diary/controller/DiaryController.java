package com.co.algomoko.diary.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.co.algomoko.admin.domain.AdminVO;
import com.co.algomoko.admin.paging.PaginationUser;
import com.co.algomoko.diary.domain.DiaryVO;
import com.co.algomoko.diary.domain.DiaryVO1;
import com.co.algomoko.diary.domain.DiaryVO2;
import com.co.algomoko.diary.domain.Diarypage;
import com.co.algomoko.diary.domain.RecipeVO;
import com.co.algomoko.diary.domain.ReqVO;
import com.co.algomoko.diary.mapper.DiaryMapper;
import com.co.algomoko.food.domain.FoodVO;
import com.co.algomoko.food.mapper.FoodMapper;

import ch.qos.logback.core.rolling.helper.ArchiveRemover;
import groovyjarjarantlr4.v4.parse.ANTLRParser.action_return;
import lombok.Data;

@RequestMapping("/diary")
@Controller
public class DiaryController {
   
   @Autowired DiaryMapper dao;
   @Autowired FoodMapper dao1;
   
   @RequestMapping("")
    public String sicmain(Model model, DiaryVO diaryVO,Authentication authentication){
//      Calendar calendar= Calendar.getInstance();
//      calendar.set(Calendar.HOUR_OF_DAY, 0);
//      calendar.set(Calendar.MINUTE, 0);
//      calendar.set(Calendar.SECOND, 0);
//      calendar.set(Calendar.MILLISECOND, 0);
	   Date date = new Date();
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   String std = sdf.format(date);
      UserDetails mid = (UserDetails) authentication.getPrincipal();
      diaryVO.setMid(mid.getUsername());
      diaryVO.setDdate(date);
      
      
      model.addAttribute("todaysic",dao.findDay(diaryVO));
      model.addAttribute("resultCal",dao.jukcal(diaryVO));
      
      
        return "contents/diary/sicmain";
    }
   @RequestMapping("cal")
    public String cal(){
        return "contents/diary/cal";
    }
  
   @RequestMapping("redetail")
       public String redetail(@RequestParam("rname") String rname ,Model model , RecipeVO recipeVO,Authentication authentication){
         
      UserDetails mid = (UserDetails) authentication.getPrincipal();
      recipeVO.setMid(mid.getUsername());
      
      
      
      
      	
      
      	String[] rearry = dao.onelist(recipeVO).get(0).getRrecipe().split(",");
      	
      	List<String> rearrys = new ArrayList<String>();
      		for(int i=0;i< rearry.length;i++) {
      			if(rearry[i].equals("")||rearry[i].isBlank()||rearry[i].isEmpty()) {
				continue;
				
			}rearrys.add(rearry[i]);
      			
			
      	}
      		
			
			
         
         model.addAttribute("rrecp",dao.onelist(recipeVO));
         model.addAttribute("redetail",dao.redetail(recipeVO));
         model.addAttribute("rname",rname);
         model.addAttribute("rearrys",rearrys);
         return "contents/diary/redetail";
      
      
      
    }
   @RequestMapping("remodify") 
	public String remodify(HttpServletResponse response,RecipeVO recipeVO,Authentication authentication)throws IOException, ParseException { 
		UserDetails mid = (UserDetails) authentication.getPrincipal();
		recipeVO.setMid(mid.getUsername());
		
		recipeVO.setNick(dao.tomem(recipeVO.getMid()));
		
		dao.redelete(recipeVO);
		System.out.println("삭제");
		dao.rededelete(recipeVO);
		
		int cal = 0;//총칼
		int carb = 0;//총탄
		int prot = 0;//총단
		int rfat = 0;//총지
				
		String[] fings = recipeVO.getFing().split(",");
		List<String> ddnameList = new ArrayList<String>();	
		DiaryVO res = new DiaryVO();
		for(int i=0;i< fings.length;i++) {
			
			ddnameList.add(fings[i]);
			
			
		}
		
		  for(int i=0;i< ddnameList.size();i++) { 
			  res = dao.fonlist(fings[i]);
			
			 recipeVO.setMid(mid.getUsername());
			 recipeVO.setFing(res.getDdname());
			 recipeVO.setCal(res.getCal());
			 recipeVO.setAamount(res.getAmount());
			  dao.redeinsert(recipeVO);
			  cal = cal+res.getCal();
			 carb = carb+res.getCarb();
			 prot = prot+res.getProt();
			 rfat = rfat+res.getFat();
			  		  
		  }
		  recipeVO.setCal(cal);
		  recipeVO.setCarb(carb);
		  recipeVO.setProt(prot);
		  recipeVO.setRfat(rfat);
		  
		  
		  dao.reinsert(recipeVO);

               return "redirect:/diary/myre"; 

	}
   @RequestMapping("succ")
    public String succ(){
      
        return "contents/diary/succ";
    }
//   @RequestMapping("todaysic")
//    public String todaysic(Model model, DiaryVO diaryVO,Authentication authentication) throws ParseException{
//      Date date = new Date();
//      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//      String std = sdf.format(date);
//      
//      UserDetails mid = (UserDetails) authentication.getPrincipal();
//      diaryVO.setMid(mid.getUsername());
//      diaryVO.setDdate(date);
//      
//      System.out.println("넘이옴");
//      
//      
//      diaryVO.setDddo("aa");
//      model.addAttribute("aade",dao.detail(diaryVO));
//      diaryVO.setDddo("bb");
//      model.addAttribute("bbde",dao.detail(diaryVO));
//      diaryVO.setDddo("cc");
//      model.addAttribute("tcal",dao.tcal(diaryVO.getMid()));
//      model.addAttribute("ccde",dao.detail(diaryVO));
//      model.addAttribute("std",std);
//      model.addAttribute("resultCal",dao.resultCal(diaryVO));
//      
//      
//      return "contents/diary/daysic";
//    }
   
   @RequestMapping(value="todaysic") 
   public String todaysic(Model model, DiaryVO diaryVO,Authentication authentication ,HttpServletResponse response)
      throws IOException, ParseException{
	   Date date = new Date();
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     String std = sdf.format(date);
     
     UserDetails mid = (UserDetails) authentication.getPrincipal();
     diaryVO.setMid(mid.getUsername());
     diaryVO.setDdate(date);
     
     System.out.println("넘어옴");
     System.out.println(dao.tcal(diaryVO.getMid()));
     DiaryVO2 diaryVO2 = new DiaryVO2();
    
     
     diaryVO.setDddo("aa");
     model.addAttribute("aade",dao.detail(diaryVO));
     diaryVO.setDddo("bb");
     model.addAttribute("bbde",dao.detail(diaryVO));
     diaryVO.setDddo("cc");
     model.addAttribute("tcal",dao.tcal(diaryVO.getMid()));
     model.addAttribute("ccde",dao.detail(diaryVO));
     model.addAttribute("std",std);
     
     
     if(dao.resultCal(diaryVO).isEmpty()) {
   	  diaryVO2.setMid(mid.getUsername());
   	  diaryVO2.setDdate(date);
   	  diaryVO2.setDddo("aa");
         model.addAttribute("aade",dao.detail(diaryVO));
         diaryVO2.setDddo("bb");
         model.addAttribute("bbde",dao.detail(diaryVO));
         diaryVO2.setDddo("cc");
         model.addAttribute("tcal",dao.tcal(diaryVO.getMid()));
         model.addAttribute("ccde",dao.detail(diaryVO));
         model.addAttribute("std",std);
         model.addAttribute("resultCal",dao.resultCal1(diaryVO2));
         
     }else {
   	  model.addAttribute("resultCal",dao.resultCal(diaryVO));
	}
	
     
        	 
   	    
     
     
     
     
     
     
     
     return "contents/diary/daysic";
   }
   
   
   
   
   
   //0000000
   
   
   @RequestMapping(value="daysic") 
    public String daysic(@RequestParam("date") String date, Model model, DiaryVO diaryVO,Authentication authentication ,HttpServletResponse response)
       throws IOException, ParseException{
      DiaryVO2 diaryVO2 = new DiaryVO2();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = date;
      String std = date;
      Date date1 = new Date(sdf.parse(strDate).getTime());
      UserDetails mid = (UserDetails) authentication.getPrincipal();
      diaryVO.setMid(mid.getUsername());
      diaryVO.setDdate(date1);
      
     
      
      
      diaryVO.setDddo("aa");
      model.addAttribute("aade",dao.detail(diaryVO));
      diaryVO.setDddo("bb");
      model.addAttribute("bbde",dao.detail(diaryVO));
      diaryVO.setDddo("cc");
      model.addAttribute("tcal",dao.tcal(diaryVO.getMid()));
      model.addAttribute("ccde",dao.detail(diaryVO));
      model.addAttribute("std",std);
      
      
      if(dao.resultCal(diaryVO).isEmpty()) {
    	  diaryVO2.setMid(mid.getUsername());
    	  diaryVO2.setDdate(date1);
    	  diaryVO2.setDddo("aa");
          model.addAttribute("aade",dao.detail(diaryVO));
          diaryVO2.setDddo("bb");
          model.addAttribute("bbde",dao.detail(diaryVO));
          diaryVO2.setDddo("cc");
          model.addAttribute("tcal",dao.tcal(diaryVO.getMid()));
          model.addAttribute("ccde",dao.detail(diaryVO));
          model.addAttribute("std",std);
          model.addAttribute("resultCal",dao.resultCal1(diaryVO2));
          
      }else {
    	  model.addAttribute("resultCal",dao.resultCal(diaryVO));
	}
	
      
         	 
    	    
      
      
      
      
      
      
      
      return "contents/diary/daysic";
    }
   @RequestMapping("weeklybest")
    public String weeklybest(Model model,RecipeVO recpvo,Authentication authentication){
      UserDetails mid = (UserDetails) authentication.getPrincipal();
      recpvo.setMid(mid.getUsername());
      
      List<RecipeVO>ranlist = new ArrayList<RecipeVO>();
      ranlist = dao.rerank(recpvo);
      Collections.shuffle(ranlist);
		System.out.println(ranlist);
		model.addAttribute("rank",ranlist);
        return "contents/diary/weeklybest";
    }
   @RequestMapping("write")
    public String write(Authentication authentication){
        return "contents/diary/write";
    }
   @RequestMapping("writema")
    public String writema(Authentication authentication){
        return "contents/diary/writema";
    }
   @RequestMapping("modify")
    public String modify(Model model, DiaryVO diaryVO, 
          @RequestParam("dddo") String dddo,
          @RequestParam("date") String date,Authentication authentication) throws ParseException{
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = date;
      
      Date date1 = new Date(sdf.parse(strDate).getTime());
      UserDetails mid = (UserDetails) authentication.getPrincipal();
      diaryVO.setMid(mid.getUsername());
      
      diaryVO.setDdate(date1);
      diaryVO.setDddo(dddo);
       
      
      model.addAttribute("modify",dao.detail(diaryVO));
      model.addAttribute("con",dao.con(diaryVO));
      model.addAttribute("dat",date);
      return "contents/diary/modify";
        
    }
   @RequestMapping("modifyde")
public String modifyde(HttpServletResponse response,DiaryVO diaryVO,DiaryVO1 diaryVO1,Authentication authentication)throws IOException, ParseException { 
      UserDetails mid = (UserDetails) authentication.getPrincipal();
      diaryVO.setMid(mid.getUsername());
      
      
      dao.diaryde(diaryVO);
      dao.detade(diaryVO);
      dao.insert(diaryVO);
 
      
      
      
      
      //칼로리
      String[] cals= diaryVO1.getCal().split(",");
      int[] nums = new int[cals.length];
      
      	for(int i=0;i< cals.length;i++) {
          
      		nums[i] = Integer.parseInt(cals[i]);
                
       }
      	
      	
      String[] amounts = diaryVO1.getAmount().split(",");
      int[] ams = new int[amounts.length];
      
      for(int i=0;i< amounts.length;i++) {
    	  
    	  ams[i] = Integer.parseInt(amounts[i]);
    	  
       }
      
      String[] ddnames = diaryVO.getDdname().split(",");
      List<String> ddnameList = new ArrayList<String>();   
      DiaryVO res = new DiaryVO();
      for(int i=0;i< ddnames.length;i++) {
         
         ddnameList.add(ddnames[i]);
         
         
      }

        for(int i=0;i< ddnameList.size();i++) { 
           res = dao.fonlist(ddnames[i]);
           
           res.setMid(mid.getUsername());
           res.setDdate(diaryVO.getDdate());
           res.setDddo(diaryVO.getDddo());
           res.setDdname(ddnames[i]);
           res.setCal(nums[i]);
           res.setAmount(ams[i]);
           dao.insertdetail(res);
        }

                return "redirect:/diary"; 

   }
   @RequestMapping("delete")
public String delete(HttpServletResponse response,DiaryVO diaryVO,Authentication authentication)throws IOException, ParseException { 
      UserDetails mid = (UserDetails) authentication.getPrincipal();
      diaryVO.setMid(mid.getUsername());
      dao.diaryde(diaryVO);
      dao.detade(diaryVO);
      
      
                return "redirect:/diary"; 

   }
   @RequestMapping("redelete") 
   public String redelete(HttpServletResponse response,RecipeVO recipeVO,Authentication authentication)throws IOException, ParseException { 
	   System.out.println("왔다");
	   UserDetails mid = (UserDetails) authentication.getPrincipal();
      recipeVO.setMid(mid.getUsername());
      recipeVO.setNick(dao.tomem(recipeVO.getMid()));
      
      dao.redelete(recipeVO);
      dao.rededelete(recipeVO);
      

                return "redirect:/diary/myre"; 

   }
   
   @RequestMapping("weekwrite")
    public String weekwrite(Model model, 
          @RequestParam("dddo") String dddo,
          @RequestParam("date") String date,Authentication authentication) throws ParseException{
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = date;
      
      Date date1 = new Date(sdf.parse(strDate).getTime());
      
      model.addAttribute("dddo",dddo);
      model.addAttribute("date",date);
      return "contents/diary/weekwrite";
        
    }
   
   @RequestMapping(value="insert") 
   public String insert(HttpServletResponse response,DiaryVO diaryVO, DiaryVO1 diaryVO1 ,Authentication authentication)throws IOException, ParseException { 
      
	   UserDetails mid = (UserDetails) authentication.getPrincipal();
	      diaryVO.setMid(mid.getUsername());
	     
	      dao.insert(diaryVO);
	 
	      
	      
	      
	      
	      //칼로리
	      String[] cals= diaryVO1.getCal().split(",");
	      int[] nums = new int[cals.length];
	      
	      	for(int i=0;i< cals.length;i++) {
	          
	      		nums[i] = Integer.parseInt(cals[i]);
	                
	       }
	      	
	      	
	      String[] amounts = diaryVO1.getAmount().split(",");
	      int[] ams = new int[amounts.length];
	      
	      for(int i=0;i< amounts.length;i++) {
	    	  
	    	  ams[i] = Integer.parseInt(amounts[i]);
	    	  
	       }
	      
	      String[] ddnames = diaryVO.getDdname().split(",");
	      List<String> ddnameList = new ArrayList<String>();   
	      DiaryVO res = new DiaryVO();
	      for(int i=0;i< ddnames.length;i++) {
	         
	         ddnameList.add(ddnames[i]);
	         
	         
	      }

	        for(int i=0;i< ddnameList.size();i++) { 
	           res = dao.fonlist(ddnames[i]);
	           
	           res.setMid(mid.getUsername());
	           res.setDdate(diaryVO.getDdate());
	           res.setDddo(diaryVO.getDddo());
	           res.setDdname(ddnames[i]);
	           res.setCal(nums[i]);
	           res.setAmount(ams[i]);
	           dao.insertdetail(res);
	        }
	        
	                return "redirect:/diary"; 

   }
   
   
   
   
   @RequestMapping("reinsert") 
   public String reinsert(DiaryVO diaryVO,Authentication authentication)throws IOException, ParseException { 
      
      UserDetails mid = (UserDetails) authentication.getPrincipal();
      diaryVO.setMid(mid.getUsername());
      


                return "contents/diary/reinsert"; 

   }
   
   @RequestMapping("reinsertde") 
   public String reinsertde(HttpServletResponse response,RecipeVO recipeVO,Authentication authentication)throws IOException, ParseException { 
	   int cal = 0;//총칼
		int carb = 0;//총탄
		int prot = 0;//총단
		int rfat = 0;//총지
		UserDetails mid = (UserDetails) authentication.getPrincipal();
		recipeVO.setMid(mid.getUsername());
		recipeVO.setNick(dao.tomem(recipeVO.getMid()));
		
		String[] fings = recipeVO.getFing().split(",");
		
		List<String> ddnameList = new ArrayList<String>();	
		DiaryVO res = new DiaryVO();
		
			
		
		
		
		
		for(int i=0;i< fings.length;i++) {
			
			ddnameList.add(fings[i]);
			
			
		}
		
		  for(int i=0;i< ddnameList.size();i++) { 
			  res = dao.fonlist(fings[i]);
			 System.out.println(ddnameList);
			 recipeVO.setMid(mid.getUsername());
			 recipeVO.setFing(res.getDdname());
			 recipeVO.setCal(res.getCal());
			 recipeVO.setAamount(res.getAmount());
			  dao.redeinsert(recipeVO);
			  cal = cal+res.getCal();
			 carb = carb+res.getCarb();
			 prot = prot+res.getProt();
			 rfat = rfat+res.getFat();
			  		  
		  }
		  recipeVO.setCal(cal);
		  recipeVO.setCarb(carb);
		  recipeVO.setProt(prot);
		  recipeVO.setRfat(rfat);
		
		  
		  dao.reinsert(recipeVO);

               return "redirect:/diary/myre"; 

	}
   
   
   @RequestMapping("flist")
   @ResponseBody
   public List<FoodVO> flist(@RequestParam("btnbtn") String btnbtn, Model model){
      
      FoodVO foodvo = new FoodVO();
      foodvo.setIng(btnbtn);
      
      return dao1.fList(foodvo);
      
    }
//   @RequestMapping("myre")
//   public String myre(Model model , RecipeVO recipeVO,Authentication authentication){
//     UserDetails mid = (UserDetails) authentication.getPrincipal();
//     recipeVO.setMid(mid.getUsername());
//     
//     model.addAttribute("rlist",dao.rlist(recipeVO));
//     
//     
//     return "contents/diary/myre";
//  }
   @RequestMapping("myre")
   public ModelAndView moveUserList(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                              @RequestParam(value = "cntPerPage", defaultValue = "5") int cntPerPage,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                              @RequestParam(value = "type", defaultValue = "null") String type,
                              @RequestParam(value = "keyword", defaultValue = "null") String keyword,Model model,RecipeVO recipeVO,Authentication authentication) throws Exception{
       ModelAndView modelAndView = new ModelAndView();
       UserDetails mid = (UserDetails) authentication.getPrincipal();
       recipeVO.setMid(mid.getUsername());
       
       
       

       Diarypage Diarypage = new Diarypage(currentPage, cntPerPage, pageSize);
       Diarypage.setKeyword(keyword);
       Diarypage.setType(type);
       Diarypage.setMid(recipeVO.getMid());
       /*
       전체 회원 수를 입력하여 0보다 컸을 때
       전체 페이지 수, 리스트의 첫 페이지 번호, 마지막 번호, ROW_NUM의 첫, 마지막 값,
       이전 페이지 존재 여부, 다음 페이지 존재 여부를 확인하고, paginationUser에 넣는다.
       */
    // 전체 회원 수
       int listCnt = dao.TableCount(recipeVO.getMid());
       //view 단에서 받은 현재 페이지, 페이지 당 출력 페이지 개수, 화면 하단 페이지 사이즈 가져와서 입력.
       Diarypage.setTotalRecordCount(listCnt);
       modelAndView.addObject("pagination", Diarypage); // 값을 paginantion으로 뿌림.
       modelAndView.addObject("Alllist", dao.myrecipelist(Diarypage)); // 회원 전체 데이터를 뿌림.
       modelAndView.addObject("rlist",dao.rlist(recipeVO));
       modelAndView.setViewName("contents/diary/myre");
      
       return modelAndView;
   }
   @RequestMapping("bestre") 
   public String bestre(@RequestParam("rname") String rname ,Model model , RecipeVO recipeVO,Authentication authentication,HttpServletResponse response)throws IOException, ParseException { 
	   recipeVO.setMid("admin");
	   
	   
	   String[] rearry = dao.onelist(recipeVO).get(0).getRrecipe().split(",");
	  	
	  	List<String> rearrys = new ArrayList<String>();
	  		for(int i=0;i< rearry.length;i++) {
	  			if(rearry[i].equals("")||rearry[i].isBlank()||rearry[i].isEmpty()) {
				continue;
				
			}rearrys.add(rearry[i]);
	  			
			
	  	}
	  		

	  		

	 
	 
   model.addAttribute("rrecp",dao.onelist(recipeVO));
   model.addAttribute("redetail",dao.redetail(recipeVO));
   model.addAttribute("rname",rname);
   model.addAttribute("rearrys",rearrys);
   return "contents/diary/bestre";



}
   @RequestMapping("download")
   public void download(@RequestParam("rname") String rname ,Model model , RecipeVO recipeVO,Authentication authentication,HttpServletResponse response)throws IOException, ParseException { 
//       Workbook wb = new HSSFWorkbook();
	   List<RecipeVO> reexList = dao.reexli(recipeVO);
	   System.out.println(recipeVO);
 		System.out.println(dao.reexli(recipeVO));

       Workbook wb = new XSSFWorkbook();
       Sheet sheet = wb.createSheet("레시피");
       Row row = null;
       Cell cell = null;
       int rowNum = 0;

       // Header
       String[] headerna = {"레시피 명","칼로리","조리법"};
       row = sheet.createRow(rowNum++);
       for(int i=0;i<headerna.length;i++) {
       		        
       cell = row.createCell(i);
       cell.setCellValue(headerna[i]);
               
       }
       // Body
       for (RecipeVO re : reexList) {
       	row = sheet.createRow(rowNum++);
       	cell = row.createCell(0);
       	cell.setCellValue(re.getRname());
       	
       	cell = row.createCell(1);
       	cell.setCellValue(re.getCal());
       	
       	cell = row.createCell(2);
       	cell.setCellValue(re.getRrecipe());
       	
       		        	
       	
       }

       // 컨텐츠 타입과 파일명 지정
       response.setContentType("ms-vnd/excel");
//       response.setHeader("Content-Disposition", "attachment;filename=example.xls");
       response.setHeader("Content-Disposition", "attachment;filename=Recipe.xlsx");

       // Excel File Output
       wb.write(response.getOutputStream());
       wb.close();
   }
   
   
   

		
   
}