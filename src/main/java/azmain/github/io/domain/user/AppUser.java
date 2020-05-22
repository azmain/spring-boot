package azmain.github.io.domain.user;

import azmain.github.io.domain.role.AppUserRole;

import java.util.Collections;
import java.util.List;

public class AppUser {

    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String address;
    private String mobileNo;

    private List<AppUserRole> roles;

    public String getUsername() {
        return username;
    }

    public AppUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public AppUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public AppUser setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AppUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AppUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AppUser setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public AppUser setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public List<AppUserRole> getRoles() {
        if(roles == null){
            return Collections.emptyList();
        }
        return roles;
    }

    public AppUser setRoles(List<AppUserRole> roles) {
        this.roles = roles;
        return this;
    }
}
