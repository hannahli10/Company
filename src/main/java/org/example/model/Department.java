package org.example.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name ="departments")
public class Department {
    public Department(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long Id;   //id
    @Column(name = "name")
    private String name;
    @Column (name = "description")
    private String description;
    @Column (name = "location")
    private String location;

    @OneToMany(mappedBy = "department",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private Set<Employee> employees;

    public void setId(long id){
        this.Id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description =description;
    }
    public void setLocation(String location){
        this.location = location;
    }



    public Long getId() {
        return Id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getLocation() {
        return location;
    }

}

