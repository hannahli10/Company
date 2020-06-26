package org.example.model;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name ="employees")
public class Employee {
    public Employee(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column (name = "first_name")
    private String firstName;
    @Column (name = "last_name")
    private String lastName;
    @Column (name = "email")
    private String email;
    @Column (name = "address")
    private String address;

//    @OneToMany(mappedBy = "employee",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
//    private Set<Account> accounts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public void setId(long id)
    {
        this.id =id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setFirstName(String firstName)
    {
        this.firstName =firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setDepartment(Department department){
        this.department = department;
    }



    public Long getId()
    {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
}


