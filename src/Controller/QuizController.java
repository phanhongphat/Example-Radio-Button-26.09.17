package Controller;

import java.io.IOException;
import java.util.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.AnswerUser;
import BEAN.IncompleteSentense;
import DAO.QuizDAO;
import DB.DBConnection;

@WebServlet("/QuizController")
public class QuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public QuizController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Connection conn = DBConnection.CreateConecction();
		int countrow = QuizDAO.Getcountrow(conn);
		
		//List chứa đáp án đúng
		List<IncompleteSentense> listcorrectanswer = QuizDAO.GetCorrectAnswer(conn);
		
		//List chứa đáp án của users
		List<AnswerUser> listansweruser = new ArrayList<AnswerUser>();
		
		for (int i =1; i <= countrow; i ++)
		{
			String answeruser = request.getParameter("ans["+i+"]");
			
			if(answeruser != null)
			{
				AnswerUser au = new AnswerUser();
				au.setNumber(i);
				au.setAnswer(answeruser);
				
				listansweruser.add(au);
			}
			else 
			{
				request.setAttribute("msg","Bạn Chưa Làm Hết Các Câu Hỏi");
				List<IncompleteSentense> list = QuizDAO.DisplayQuestion(conn);
				
				request.setAttribute("listincomplete",list);
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/View/Quiz.jsp");
				rd.forward(request, response);	
			}
			
		}	
			request.setAttribute("listcorrectanswer", listcorrectanswer);
			request.setAttribute("listansweruser", listansweruser);
		
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/View/Result.jsp");
			rd.forward(request, response);	
		
	}

}
