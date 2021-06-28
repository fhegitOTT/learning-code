package com.netcom.nkestate.system.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.netcom.nkestate.common.Constant;
import com.netcom.nkestate.fhhouse.query.bo.ContractPdfBO;
import com.netcom.nkestate.fhhouse.query.vo.ContractPdfSignVO;
import com.netcom.nkestate.fhhouse.query.vo.ContractPdfVO;

public class UploadFileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 2048;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 try {
			receiveFile(request, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 try {
			receiveFile(request, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void receiveFile(HttpServletRequest request, HttpServletResponse response) throws RuntimeException, Exception{
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        fileUpload.setHeaderEncoding("UTF-8");
        InputStream input = null;
        try {
        	String contractID = request.getParameter("contractID")!=null?request.getParameter("contractID"):"";
        	String newPath = ""; 
        	ContractPdfBO cpBo = new ContractPdfBO();
			//获取合同pdf信息
			ContractPdfSignVO cpvo = (ContractPdfSignVO) cpBo.find(ContractPdfSignVO.class, Long.parseLong(contractID));
			if(cpvo != null){
				newPath = cpvo.getPdfPath();
			}
        	
            List<FileItem> fileItemList = fileUpload.parseRequest(request);
            Iterator<FileItem> it = fileItemList.iterator();
            while (it.hasNext()){
                FileItem item = it.next();
                if(!item.isFormField()){
                    try {
                        input = item.getInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String fileNameInSent = item.getName();
                    String fileName = fileNameInSent.substring(fileNameInSent.lastIndexOf("\\") + 1);
                    File newFile = new File(newPath);//新的目录
                    writeFile(input, newFile);

                    response.setStatus(200); // Response 200 OK indicate receive success.
                    response.setContentType("text/html;charset=utf-8");
                    PrintWriter outres = null;
                    try {
                        outres = response.getWriter();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
					File oldFile = new File(Constant.ContractPdfPath+ "/"  + fileName);
					if(oldFile.exists()){
						oldFile.delete();
					}
                    outres.write("Server has received the file succeeded!");
                }
                else{
                    System.out.println("It is FormField item.");
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
		}finally{
			if(input !=null){
				input.close();
			}
		}
    }

    public void writeFile(InputStream in, File destFile) throws IOException{
        OutputStream out = null;
        try{
            out = new BufferedOutputStream(new FileOutputStream(destFile), BUFFER_SIZE);
            in = new BufferedInputStream(in, BUFFER_SIZE);

            int len = 0;
            byte[] buffer = new byte[BUFFER_SIZE];
            while((len = in.read(buffer)) > 0){
                out.write(buffer, 0, len);
            }
            in.close();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
        	if(in != null){
        		in.close();
        	}
        	if(out != null){
        		out.close();
        	}
        }
    }

}
