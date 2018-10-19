package ru.thirdproject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 *         CREATE TABLE users
 * (
 *     id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
 *     name varchar(255)
 * );
 * CREATE UNIQUE INDEX UK_3g1j96g94xpk3lpxl2qbl985x ON users (name);
 */
@Entity
@Table(name = "users")
public class User implements Serializable { // Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true, updatable = false)
    private String name;

    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "password", unique = false, updatable = true)
    private String password;

    @Column(name = "role", unique = false, updatable = true)
    private String role;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public User() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public User(long id, String name) {
        this.setRole("user");
        this.setId(id);
        this.setName(name);
    }

    public User(String name) {
        this.setRole("user");
        this.setId(-1);
        this.setName(name);
    }

    public User(long id, String name, String password) {
        this.setRole("user");
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(String name, String password) {
        this.setRole("user");
        this.setId(-1);
        this.name = name;
        this.password = password;
    }

 /*   public User(long id, String name, String login, String password) {
        this.setRole("user");
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    } */

    public User(String name, String login, String password) {
        this.setRole("user");
        this.setId(-1);
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(long id, String name, String login, String password, String role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String name, String login, String password, String role) {
        this.setId(-1);
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


}