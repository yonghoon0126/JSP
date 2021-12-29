package com.saeyan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		// ���⸦ �ٲ��ָ� �ٿ�޴� ��ΰ� �ٲ�
		String savePath = "upload";

		// �ִ� ���ε� ���� ũ�� 5MB�� ����
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType = "UTF-8";

		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		System.out.println("�������� ���� ���丮 : ");
		System.out.println(uploadFilePath);

		try {
			MultipartRequest multi = new MultipartRequest(request, // request��ü
					uploadFilePath, // �������� ���� ���丮
					uploadFileSizeLimit, // �ִ� ���ε� ũ��
					encType, // ���ڵ� ���

					// ������ �̸��� �����ϸ� ���ο� �̸��� �ο���
					new DefaultFileRenamePolicy());

			// ���ε�� ������ �̸� ���
			String fileName = multi.getFilesystemName("uploadFile");

			if (fileName == null) { // ������ ���ε� ���� �ʾ�����
				System.out.println("������ ���ε� ���� �ʾ���");
			} else { // ������ ���ε� �Ǿ�����
				out.println("<br> �۾��� : " + multi.getParameter("name"));
				out.println("<br> �� &nbsp; �� : " + multi.getParameter("title"));
				out.println("<br> ���ϸ� : " + fileName);
			} // else

		} catch (Exception e) {

			System.out.println("���ܹ߻� : " + e);

		} // catch
	}

}
