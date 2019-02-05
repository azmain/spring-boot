package azmain.github.io.bean.user;

import java.util.Date;

public class UserBean {
	
	private int id;
	private String email;
	private String username;
	private String password;
	private int isActive;
	public int isApproved;
	private String token;
	private String ip;
	private int modifiedBy;
	private int createdBy;
	
	private Date createdAt;
	private Date modifiedAt;
	private String lastLogin;
	
	
	public UserBean() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	public int getIsActive() {
		return isActive;
	}


	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}


	public int getIsApproved() {
		return isApproved;
	}


	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public int getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public int getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getModifiedAt() {
		return modifiedAt;
	}


	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}


	public String getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	
	
}
