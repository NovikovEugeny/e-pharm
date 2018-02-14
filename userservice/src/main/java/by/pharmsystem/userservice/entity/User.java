package by.pharmsystem.userservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 2667084259673754334L;

    @Id
    private long id;
    private String name;
    private String login;
    private String password;
    private String role;

    public User getDTO() {
        this.setPassword(null);
        return this;
    }
}
