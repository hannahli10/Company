package org.example.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table (name ="Accounts")
public class Account {
    public Account(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;   //id
    @Column(name = "account_type")
    private String accountType;
    @Column (name = "balance")
    private BigDecimal balance;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee; //employee same as Employee Class mappedBy ="variable"

    public void setId(long id){
        this.id = id;
    }
    public void setAccountType(String accountType){
        this.accountType = accountType;
    }
    public void setBalance(BigDecimal balance){ this.balance =balance; }
    public void setEmployee(Employee employee){ this.employee = employee;}



    public Long getId() {
        return id;
    }
    public String getAccountType() {
        return accountType;
    }
    public BigDecimal getBalance() {
        return balance;
    }
//    public BigDecimal setBalance() {
//        this.balance = balance;
//        return balance;


}

