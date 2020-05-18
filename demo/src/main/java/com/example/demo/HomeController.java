package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;

@Controller
public class HomeController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("serverTime");
		return "sendView";
	}

	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public void fileUpload(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		// ?��붽��뜲�씠�꽣 ��?��?���듃

		String filePath = "c://mywork/final/User/";

		HandlerFile handlerFile = new HandlerFile(multipartRequest, filePath);
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd-HH-mm-ss");
		Date time = new Date();
		
		String nowdate = format1.format(time);
		String id = "0";
		
		Map<String, List<String>> fileNames = handlerFile.getUploadFileName(id,nowdate);
		
		System.err.println(fileNames.toString());
	
		String fileName = handlerFile.getFileFullPath();
		
		Client client = new Client(fileName);
		JsonObject result = client.getResult();
		String js;
		ServletOutputStream out;

		System.out.println(result.get("data"));
		System.out.println(result.get("score"));
		System.out.println(result.get("imgpath"));
		/*
		try {
			response.setContentType("text/html; charset=UTF-8");
			out = response.getOutputStream();
				
			if (result.equals("null") || result.equals("fail")) {
				js = "<script>history.back(); alert('Result : Error! Page Reload!');</script>";
			} else {
				js = "<script>alert('Result : "+result+"'); location.href='https://www.google.co.kr/search?q=" + result + "'</script>";
			}
			
			out.println(js);
			out.flush();
			
		} catch(Exception e) {
			e.printStackTrace();
		}// catch
		*/
		
	}// fileUpload

}
