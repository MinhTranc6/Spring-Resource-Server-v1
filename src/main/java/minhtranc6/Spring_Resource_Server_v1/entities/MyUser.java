package minhtranc6.Spring_Resource_Server_v1.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "myuser")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String passWord;
    private String role;

    public MyUser(Long id, String userName, String passWord, String role) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public Long getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassWord() {
        return passWord;
    }
    public String getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String passWord) {
        this.passWord = passWord;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
