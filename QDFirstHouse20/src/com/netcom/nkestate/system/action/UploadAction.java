package com.netcom.nkestate.system.action;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@RequestMapping(value = "system")
public class UploadAction {

	@RequestMapping("springUpload")
	public String springUpload(HttpServletRequest request) throws IllegalStateException, IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(multipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator iter = multiRequest.getFileNames();
			while (iter.hasNext()){
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if(file != null){
					String path = request.getSession().getServletContext().getRealPath("temp");
					String fileName = System.currentTimeMillis() + "" + file.getOriginalFilename();
					System.out.println(path);
					File targetFile = new File(path, fileName);
					if(!targetFile.exists()){
						targetFile.mkdirs();
					}
					file.transferTo(targetFile);
				}
			}
		}
		return "temp0";
	}
}
