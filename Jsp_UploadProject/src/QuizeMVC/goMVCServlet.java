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
		result = "국적이 없어요..그래도 환영합니다.";
	}else if(country.equals("Korea")) {
		result = name + "님은 한국인 입니다.";
	}else if(country.equals("English")) {
		result = name + "님은 미국인 입니다.";
	}else if(country.equals("Japan")) {
		result = name + "님은 일본인 입니다.";
	}else {
		result = "한국인, 미국인, 일본인도 아니지만 환영해.";
	}
	
	request.setAttribute("result", result);
	RequestDispatcher dispatcher = request.getRequestDispatcher("gomvc.jsp");
	dispatcher.forward(request, response);
	
	
	}

}
