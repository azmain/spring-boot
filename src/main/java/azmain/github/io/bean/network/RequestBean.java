package azmain.github.io.bean.network;

import azmain.github.io.bean.user.UserBean;

public class RequestBean {

	public UserBean userBean;

	public RequestBean() {}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
