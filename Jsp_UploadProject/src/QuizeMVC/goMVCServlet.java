package QuizeMVC;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/QuizeMVC/Go")
public class goMVCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	request.setCharacterEncoding("utf-8");
	String country = request.getParameter("country");
	String name = request.getParameter("name");
	String result = null;
	
	if(country == "") {
		result = "������ �����..�׷��� ȯ���մϴ�.";
	}else if(country.equals("Korea")) {
		result = name + "���� �ѱ��� �Դϴ�.";
	}else if(country.equals("English")) {
		result = name + "���� �̱��� �Դϴ�.";
	}else if(country.equals("Japan")) {
		result = name + "���� �Ϻ��� �Դϴ�.";
	}else {
		result = "�ѱ���, �̱���, �Ϻ��ε� �ƴ����� ȯ����.";
	}
	
	request.setAttribute("result", result);
	RequestDispatcher dispatcher = request.getRequestDispatcher("gomvc.jsp");
	dispatcher.forward(request, response);
	
	
	}

}
