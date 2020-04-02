package azmain.github.io.repository.schema;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = SchemaConstant.ROLE_TABLE_NAME)
public class Role {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ROLE_NAME")
    @Size(max = 50)
    private String roleName;

    public long getId() {
        return id;
    }

    public Role setId(long id) {
        this.id = id;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public Role setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }
}
