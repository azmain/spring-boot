package azmain.github.io.bean.user;

public class AuthBean {
    private String token;
    private String username;
    private String useremail;
    private int userid;

    public AuthBean(){}

    public AuthBean(String token, String username, String useremail, int userid) {
        this.token = token;
        this.username = username;
        this.useremail = useremail;
        this.userid = userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
