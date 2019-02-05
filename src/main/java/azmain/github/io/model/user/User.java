package azmain.github.io.model.user;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
public class User {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    
    @NotNull
	private String email;
    @NotNull
	private String username;
    @NotNull
	private String password;
    @Column(columnDefinition="default 0",nullable = false)
	private int isActive;
    @Column(columnDefinition="default 0",nullable = false)
	public int isApproved;
    @Column(columnDefinition="default xyz",nullable = false)
	private String token;
	private String ip;
	@Column(columnDefinition="default 0",nullable = false)
	private int modifiedBy;
	@Column(columnDefinition="default 0",nullable = false)
	private int createdBy;
	
	@Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	private Date modifiedAt;
	private String lastLogin;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
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
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
}
