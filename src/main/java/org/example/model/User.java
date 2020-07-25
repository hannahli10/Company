package org.example.model;

//import org.hibernate.mapping.Set;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Set;
import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;
    @Column(name ="name")
    private String name;
    @Column(name ="password")
    private String password;
    @Column(name ="secret_key")
    private String secretKey;
    @Column(name ="first_name")
    private String firstName;
    @Column(name ="last_name")
    private String lastName;
    @Column(name = "email")
    private String email;



    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(name = "users_role",
        joinColumns = { @JoinColumn(name = "user_id")},
        inverseJoinColumns = { @JoinColumn(name = "role_id")}
    )
    private Set<Role> roles;

    public void addRole(Role role){
        this.roles.add(role);
        role.getUsers().add(this);
    }
    public void removeRole(Role role){
        this.roles.remove(role);
        role.getUsers().remove(this);
    }


    public void setId(Long id){this.id = id;}
    public void setName(String name) { this.name = name; }
    public void setPassword(String password) {this.password = DigestUtils.md5Hex(password.trim());}
    public void setSecretKey(String secretKey) {this .secretKey = secretKey;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setEmail(String email){this.email = email;}
    public void setRoles(Set roles){this.roles = roles;}

    public Long getId(){return id;}
    public String getName(){return name;}
    public String getPassword(){return password; }
    public String getSecretKey(){return secretKey;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getEmail(){return email;}
    public Set getRoles() {return roles;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(secretKey, user.secretKey) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, secretKey, firstName, lastName, email);
    }

}
