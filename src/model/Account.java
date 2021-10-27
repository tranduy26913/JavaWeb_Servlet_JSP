package model;


import java.util.Iterator;
import javax.servlet.http.Cookie;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class Account extends Model {
	protected ObjectId _id;
	protected String username;
	protected String password;
	protected String email;
	protected String typeUser;
	public String getTypeUser() {
		return typeUser;
	}
	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}
	public ObjectId getId() {
		return _id;
	}
	public void setId(ObjectId id) {
		this._id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Account() {

	}

	public Account(String username, String password, String email,String typeUser) {
		this.setId(new ObjectId());
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
		this.setTypeUser(typeUser);
	}
	
	public Account(ObjectId id,String username, String password, String email,String typeUser) {
		this.setId(id);
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
		this.setTypeUser(typeUser);
	}
	
	public static boolean isLogged(Cookie[] cookies)
	{
		if (cookies == null)
			return false;
		String UUID = "";
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("UUID"))
				UUID = cookie.getValue();
		}
		Confirm confirm=Model.CONFIRM.find(Filters.eq("uuid", UUID)).first();
		if(confirm!=null)
			return true;	
		return false;
	}
	
	public static ObjectId getAccountIdFromCookie(Cookie[] cookies) {
		if(cookies==null)
			return null;
		String UUID="";
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("UUID"))
				UUID=cookie.getValue();
		}
		if(UUID.equals(""))
			return null;
		Confirm confirm=Model.CONFIRM.find(Filters.eq("uuid", UUID)).first();
		if(confirm!=null)
			return confirm.getAccountId();
		return null;
	}
	
	public static ObjectId getAccountIdFromUsername(String username) {		
		Account acc=Model.ACCOUNT.find(Filters.eq("username", username)).first();
		if(acc!=null)
			return acc.getId();
		return null;
	}
	
	public static Account getAccountFromAccountId(ObjectId accountId) {
		Account acc=ACCOUNT.find(Filters.eq("_id",accountId)).first();
		return acc;
	}
	
	public static boolean isEmployer(Cookie[] cookies) {
		if(cookies==null)
			return false;
		ObjectId accountId=Account.getAccountIdFromCookie(cookies);
		Account account=Account.getAccountFromAccountId(accountId);
		
		
		if(account!=null)
			if(account.getTypeUser().equals("EMPLOYER"))
				return true;
		return false;
	}
	
	public void Insert() {
		ACCOUNT.insertOne(this);
	}
	
	public static void ChangePassword(String username,String password) {
		ACCOUNT.updateOne(Filters.eq("username",username), Updates.set("password", password));
	}
}
