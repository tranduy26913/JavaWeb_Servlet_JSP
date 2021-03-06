package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.Port;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;

import DAO.AccountDAO;
import DAO.ApplyDAO;
import DAO.PostDAO;
import DAO.UserEmployeeDAO;
import DAO.UserEmployerDAO;
import Util.EmailUtility;
import model.Account;
import model.Apply;
import model.UserEmployee;
import model.UserEmployer;
import model.Post;


/**
 * Servlet implementation class DeleteApplyController
 */
@WebServlet(name = "sendmail", urlPatterns = { "/employee/sendmail", "/employer/sendmail"})
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
	private String port;
	private String user;
	private String pass;
	
	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession Session = request.getSession();
		Account acc = (Account) Session.getAttribute("acc");
		String idString=request.getParameter("postId");
		System.out.println(idString);
	
		ObjectId postId = new ObjectId(idString);
		String accountIdString=request.getParameter("accountId");
		ObjectId accountId=new ObjectId();
		MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGODB_CLIENT");
		try {
			if (accountIdString == null)
				accountId = acc.getId();
			else {
				accountId=new ObjectId(accountIdString);
			}
		} catch (Exception e) {
			response.sendRedirect(request.getHeader("referer"));
			return;
		}
		PostDAO postDAO = new PostDAO(mongo);
		Post post = postDAO.GetPostByID(postId);
		ApplyDAO applyDAO = new ApplyDAO(mongo);
		AccountDAO accountDAO = new AccountDAO(mongo);
		
		UserEmployeeDAO userEmployeeDAO = new UserEmployeeDAO(mongo);
		UserEmployee employee = userEmployeeDAO.findEmployeeWithID(accountId);
		UserEmployerDAO userEmployerDAO = new UserEmployerDAO(mongo);
		UserEmployer employer = userEmployerDAO.getUserEmployerFromAccountId(post.getAccountId());

		String resultMessage = "";
		try {
			Account account = accountDAO.getAccountFromAccountId(accountId);
			String noiDungString = "Xin ch??o b???n "+ employee.getFullName()+",\r\n"
					+ "\r\n"
					+ "L???i n??i ?????u ti??n, xin g???i ?????n b???n l???i ch??c s???c kh???e v?? th??nh c??ng !"
					+ "\r\n"
					+ "Ch??c m???ng ban ???? v?????t qua v??ng x??t tuy???n h??? s?? c???a c??ng ty "+ employer.getFullName() +" cho v??? tr??" + post.getExp() +".\r\n"
					+ "\r\n"
					+ "C??ng ty ch??ng t??i nh???n th???y b???n c?? nh???ng ki???n th???c chuy??n m??n ph?? h???p ????? ????p ???ng nh???ng c??ng vi???c cho v??? tr?? m?? ch??ng t??i ??ang tuy???n." 
					+ "V?? th???, ch??ng t??i xin g???i th?? n??y ????? h???n b???n m???t bu???i ph???ng v???n.\r\n"
					+ "\r\n"
					+ "Anh/Ch??? vui l??ng li??n h??? ch??ng t??i ngay khi nh???n ???????c th?? n??y ????? nh???n k??? ho???ch ph???ng v???n s???m nh???t."
					+ "????? bi???t th??m c??c th??ng tin kh??c, b???n c?? th??? li??n h??? v???i ch??ng t??i qua:\r\n"
					+ "\r\n"
					+ "S??? ??i???n tho???i li??n h???: "+employer.getPhone()+ ",?????a ch???: "+ employee.getAddress() +"\r\n"
					+ "\r\n"
					+ "Tr??n tr???ng,"
					+ "\r\n"
					+ employer.getFullName() +"\r\n"
					+ "\r\n"
					+ "Email: "+employer.getEmail() +"\r\n";
			EmailUtility.sendEmail(host, port, user, pass, account.getEmail(), "["+employer.getFullName()+"] TH??NG B??O V?????T QUA V??NG X??T TUY???N H??? S??", noiDungString);
			resultMessage = "The e-mail was sent successfully";
		} 
			catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "H??? th???ng email ??ang g???p tr???c tr???c. Th??? l???i sau ho???c li??n h??? qu???n tr??? vi??n ????? ?????i m???t kh???u";
			request.setAttribute("result", "error");
		} 
			finally {
			request.setAttribute("Message", resultMessage);
			getServletContext().getRequestDispatcher("/Login/forgotpw.jsp").forward(request, response);
		}

		
		request.setAttribute("msg", "G???i th??nh c??ng");
		response.sendRedirect(request.getHeader("referer"));
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
