package com.blgr.blogapp.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String roles = "";

    @Column(nullable = false)
    private boolean isEnabled;

   @OneToMany(mappedBy = "user")
   private List<BlogEntry> stories = new ArrayList<>();

    public User(){}

    public User(String username, String password, String email){
        this.username = username;
        this.password=password;
        this.email = email;
        this.roles="USER";
        this.isEnabled=true;
    }

    public User(String username, String password,String roles, String email,Boolean isEnabled){
        this.username = username;
        this.password=password;
        this.email = email;
        this.roles=roles;
        this.isEnabled=isEnabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getRoles() { return roles; }

    public void setRoles(String roles) { this.roles = roles; }

    public List<BlogEntry> getStories() {return stories;}

    public void setStories(List<BlogEntry> stories) {this.stories = stories;}

    public boolean isEnabled() { return isEnabled; }

    public void setEnabled(boolean enabled) { isEnabled = enabled; }

    public List<String> getRoleList(){
        if(this.roles.length()>0){
            System.out.println("ROLES LIST SIZE:  " + roles);
            return Arrays.asList(this.roles.split(","));}
        System.out.println("ROLES LIST SIZE:  < !!!" + roles);
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                ", stories=" + stories +
                '}';
    }
}
