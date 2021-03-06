package controller;

import java.io.IOException;
import java.net.HttpCookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;

import DAO.AccountDAO;
import DAO.ConfirmDAO;
import model.Account;
import model.Confirm;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="/home";
		Cookie[] cookies=request.getCookies();
		MongoClient mongoClient=(MongoClient)request.getServletContext().getAttribute("MONGODB_CLIENT");
		AccountDAO accountDAO=new AccountDAO(mongoClient);
		ObjectId accountId=accountDAO.getAccountIdFromCookie(cookies);
		HttpSession session = request.getSession();
		
		if(accountId!=null) {
			ConfirmDAO confirmDAO=new ConfirmDAO(mongoClient);
			confirmDAO.DeleteConfirm(accountId);
			session.invalidate();
		}
		url = request.getContextPath() + "/home"; 
		response.sendRedirect(url);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
