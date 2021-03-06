package model;


import java.util.Date;

import org.bson.types.ObjectId;

public class Apply {
	private ObjectId _id;
	private ObjectId accountId;
	private ObjectId postId;
	private String title;
	private String luong;
	private String status;
	private String time;
	private String url;

	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLuong() {
		return luong;
	}

	public void setLuong(String luong) {
		this.luong = luong;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Apply() {
	}
	
	public Apply(ObjectId id, ObjectId account_id,ObjectId post_id,String date) {
		this._id = id;
		this.accountId = account_id;
		this.postId = post_id;
	}
	
	public ObjectId getId() {
		return _id;
	}
	
	public ObjectId getAccountId() {
		return accountId;
	}
	
	public void setAccountId(ObjectId accountId) {
		this.accountId = accountId;
	}
	
	public void setId(ObjectId _id) {
		this._id = _id;
	}
	
	public ObjectId getPostId() {
		return postId;
	}
	public void setPostId(ObjectId post_id) {
		this.postId = post_id;
	}
	
	public String HexStringId() {
		return this._id.toHexString();
	}
	
}
