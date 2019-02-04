package azmain.github.io.bean.network;

import java.util.List;

import azmain.github.io.bean.user.UserBean;

public class ResponseBean {

	public String msg;
	public int code;
	public List<?> list;
	public Object object;
	
	public UserBean userBean;

	public ResponseBean() {}
	
	
	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
}
