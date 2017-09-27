package DAO;
import java.util.*;

import BEAN.IncompleteSentense;

import java.sql.*;

public class QuizDAO 
{
	public static List<IncompleteSentense> DisplayQuestion(Connection conn)
	{
		List<IncompleteSentense> list = new ArrayList<IncompleteSentense>();
		
		String sql = "select * from incompletesentence" ;
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			// đổ dữ liệu vào sql
			while (rs.next())
			{
				int number = rs.getInt("number");
				String question = rs.getString("question");
				String option1 = rs.getString("option1");
				String option2 = rs.getString("option2");
				String option3 = rs.getString("option3");
				String option4 = rs.getString("option4");
				String correctanswer = rs.getString("correctanswer");
				
				
				IncompleteSentense ics = new IncompleteSentense();
				ics.setNumber(number);
				ics.setQuestion(question);
				ics.setOption1(option1);
				ics.setOption2(option2);
				ics.setOption3(option3);
				ics.setOption4(option4);
				ics.setCorrectanswer(correctanswer);
				
				list.add(ics);
				
				
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	
	public static List<IncompleteSentense> GetCorrectAnswer(Connection conn)
	{
		List<IncompleteSentense> list = new ArrayList<IncompleteSentense>();
		
		String sql = "select * from incompletesentence" ;
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			// đổ dữ liệu vào sql
			while (rs.next())
			{
				int number = rs.getInt("number");
				String question = rs.getString("question");
				String option1 = rs.getString("option1");
				String option2 = rs.getString("option2");
				String option3 = rs.getString("option3");
				String option4 = rs.getString("option4");
				String correctanswer = rs.getString("correctanswer");
				
				
				IncompleteSentense ics = new IncompleteSentense();
				ics.setNumber(number);
				ics.setQuestion(question);
				ics.setOption1(option1);
				ics.setOption2(option2);
				ics.setOption3(option3);
				ics.setOption4(option4);
				ics.setCorrectanswer(correctanswer);
				
				list.add(ics);
				
				
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		return list;
	}
	// Trả về số lượng câu hỏi trong sql
	public static int Getcountrow(Connection conn)
	{
		int count = 0;
		
		String sql = "select count(*) from incompletesentence" ;
		PreparedStatement ptmt;
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		return count;
	}
	
}
