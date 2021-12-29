package com.saeyan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import oracle.net.aso.e;

@WebServlet("/upload2.do")
public class MultiUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String savePath = "upload";
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType = "UTF-8";

		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);

		try {
			MultipartRequest multi = new MultipartRequest(request, 
					uploadFilePath, 
					uploadFileSizeLimit, 
					encType,
					new DefaultFileRenamePolicy());
			Enumeration files = multi.getFileNames();

			while(files.hasMoreElements()) {
				String file = (String)files.nextElement();
				String file_name = multi.getFilesystemName(file);

				// �ߺ��� ������ ���ε� �� �� �� ���ϸ��� �ٲ��.
				String ori_file_name = multi.getOriginalFileName(file);
				out.print("<br> ���ε�� ���ϸ� : <a href='" + savePath + "/" + file_name + "'>" + file_name + "</a>");
				out.print("<br> ���� ���ϸ� : " + ori_file_name);
				out.print("<hr>");
			}

		} catch (Exception e) {
			System.out.println("���� �߻� : " + e);

		}

	}

}
