package azmain.github.io.repository.schema;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = SchemaConstant.USER_TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"USER_NAME","EMAIL"})
})
public class UserEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME")
    @Size(max = 50)
    private String name;

    @Column(name = "USER_NAME")
    @Size(min = 4, max = 11)
    private String userName;

    @Column(name = "EMAIL")
    @Email
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Column(name = "IS_BLOCKED")
    private Boolean isBlocked;

    @Column(name = "ACTIVATION_URL")
    private String activationUrl;

    @ManyToMany
    @JoinTable(name = "SECU_USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    )
    private List<Role> roles;

    public long getId() {
        return id;
    }

    public UserEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserEntity setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public Boolean getActive() {
        return isActive;
    }

    public UserEntity setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public UserEntity setBlocked(Boolean blocked) {
        isBlocked = blocked;
        return this;
    }

    public String getActivationUrl() {
        return activationUrl;
    }

    public UserEntity setActivationUrl(String activationUrl) {
        this.activationUrl = activationUrl;
        return this;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }
}
