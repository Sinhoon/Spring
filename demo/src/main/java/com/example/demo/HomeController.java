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

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "sendView";
	}

	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public String fileUpload(MultipartHttpServletRequest multipartRequest, HttpServletResponse response , Model model) {
		String filePath = "c://mywork/final/User/";
		HandlerFile handlerFile = new HandlerFile(multipartRequest, filePath);
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd-HH-mm-ss");
		Date time = new Date();
		String nowdate = format1.format(time);
		
		
		String id = "0";
		
		
		Map<String, List<String>> fileNames = handlerFile.getUploadFileName(id,nowdate);
		String fileName = handlerFile.getFileFullPath();
		Client client = new Client(fileName);
		JsonObject result = client.getResult();
		String js;
		ServletOutputStream out;

		//System.out.println(result.get("data"));
		//System.out.println(result.get("score"));
		//System.out.println(result.get("imgpath"));
		
		
		
		model.addAttribute("imgpath",  "file://"+result.get("imgpath").toString());
		
		return "result";
		
		/*
		Image image = null;
		File sourceimage = new File(result.get("imgpath").toString());
		try {
			 image = ImageIO.read(sourceimage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(image);
		*/

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
