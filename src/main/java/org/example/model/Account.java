package org.example.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table (name ="Accounts")
public class Account {
    public Account(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long Id;   //id
    @Column(name = "account_type")
    private String accountType;
//    @Column (name = "balance")
//    private Double balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee; //employee same as Employee Class mappedBy ="variable"

    public void setId(long id){
        this.Id = id;
    }
    public void setAccountType(String accountType){
        this.accountType = accountType;
    }
//    public void setBalance(Double balance){
//        this.balance =balance;
//    }

    public void setEmployee(Employee employee){
        this.employee = employee;}



    public Long getId() {
        return Id;
    }
    public String getAccountType() {
        return accountType;
    }
//    public Double getBalance() {
//        return balance;
//    }

}

