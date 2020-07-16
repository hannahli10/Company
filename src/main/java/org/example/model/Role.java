package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="allowed_resource")
    private String allowedResource;
    @Column(name="allowed_create")
    private Boolean allowedCreate;
    @Column(name="allowed_read")
    private Boolean allowedRead;
    @Column(name="allowed_update")
    private Boolean allowedUpdate;
    @Column(name="allowed_delete")
    private Boolean allowedDelate;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public void addUser(User user){
        this.users.add(user);
        user.getRoles().add(this);
    }
    public void removeUser(User user){
        this.users.remove(user);
        user.getRoles().remove(this);
    }

    public void setId(Long id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setAllowedResource(String allowedResource){this.allowedResource = allowedResource;}
    public void setAllowedCreate(Boolean allowedCreate){this.allowedCreate = allowedCreate;}
    public void setAllowedRead(Boolean allowedRead){this.allowedRead = allowedRead;}
    public void setAllowedUpdate(Boolean allowedUpadte){this.allowedUpdate = allowedUpadte;}
    public void setAllowedDelate(Boolean allowedDelate){this.allowedDelate = allowedDelate;}
    public void setUsers(Set users){this.users = users;}


    public Long getId(){return id;}
    public String getName(){return name;}
    public String getAllowedResource(){return allowedResource;}
    public Boolean getAllowedCreate(){return allowedCreate;}
    public Boolean getAllowedRead(){return allowedRead;}
    public Boolean getAllowedUpdate(){return allowedUpdate; }
    public Boolean getAllowedDelate(){return allowedDelate;}
    public Set getUsers() {return users;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(name, role.name) &&
                Objects.equals(allowedResource, role.allowedResource) &&
                Objects.equals(allowedCreate, role.allowedCreate) &&
                Objects.equals(allowedRead, role.allowedRead) &&
                Objects.equals(allowedUpdate, role.allowedUpdate) &&
                Objects.equals(allowedDelate, role.allowedDelate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, allowedResource, allowedCreate, allowedRead, allowedUpdate, allowedDelate);

    }
}
