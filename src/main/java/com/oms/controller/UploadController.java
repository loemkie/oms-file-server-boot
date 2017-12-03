package com.oms.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.oms.util.FileUtils;
import com.oms.util.Global;
import com.oms.util.PropertiesUtil;
@Controller
public class UploadController {
	
    /** 
     * 使用Ajax异步上传图片 
     *  
     * @param pic 封装图片对象 
     * @param request 
     * @param response 
     * @throws IOException  
     * @throws IllegalStateException  
     */  
	@RequestMapping("/upload/uploadPic")  
    public void uploadPic(@RequestParam("pic") MultipartFile  pic, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {  
  
        try {  
        	HttpServletRequest httpServletRequest = (HttpServletRequest)request;
            HttpServletResponse httpServletResponse = (HttpServletResponse)response;
            System.out.println("拦截请求: "+httpServletRequest.getServletPath());
            // 获取图片原始文件名  
            String originalFilename = pic.getOriginalFilename();  
            System.out.println(originalFilename);  
          
            // 文件名使用当前时间  
            String name = FileUtils.getName();
            // 获取上传图片的扩展名(jpg/png/...)  
            String extension = FileUtils.getFileExtension(originalFilename);  
              
            // 图片上传的相对路径（因为相对路径放到页面上就可以显示图片）  
            String fileName = name + "." + extension;  
  
            // 图片上传的绝对路径  
//            String url = request.getSession().getServletContext().getRealPath("") + path;  
            //文件路径
            String path = FileUtils.getDir()+fileName;
            String url = PropertiesUtil.getPhotoPath()+path;
            //文件所在的文件夹
            File dir = new File(PropertiesUtil.getPhotoPath()+FileUtils.getDir());  
            if(!dir.exists()) {  
            	dir.mkdirs();  
            }  
            BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(url)));
            buffStream.write(pic.getBytes());
            buffStream.close();
            // 上传图片  
            //pic.transferTo(new File(url));  
            
            // 将相对路径写回（json格式）  
           // JSONObject jsonObject = new JSONObject();  
            // 将图片上传到本地  
           // jsonObject.put("path", path);  
          
            // 设置响应数据的类型json  
            response.setContentType("application/json; charset=utf-8");  
            // 写回  
            response.getWriter().write("{\"path\":\"/"+Global.PHOTO+path+"\"}");  
  
        } catch (Exception e) {  
            throw new RuntimeException("服务器繁忙，上传图片失败",e);  
        }  
    }  
}
