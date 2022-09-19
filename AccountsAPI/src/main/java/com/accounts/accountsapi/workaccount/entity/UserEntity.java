package com.accounts.accountsapi.workaccount.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Entity
@Table
public class UserEntity {
    @Id
    @SequenceGenerator(
            name = "seq_users",
            sequenceName = "seq_users",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_users"
    )
    private Long id;
    private String fullname;
    private String pin;
    private String password;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<AccountEntity> foundersList;

    public UserEntity() {

    }

    public UserEntity(Long id,
                      String fullname,
                      String pin,
                      String password) {
        this.id = id;
        this.fullname = fullname;
        this.pin = pin;
        this.password = password;
    }

    public UserEntity(
            String fullname,
            String pin,
            String password) {
        this.id = id;
        this.fullname = fullname;
        this.pin = pin;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
    public List<AccountEntity> getFoundersList() {
        return foundersList;
    }

    public void setFoundersList(List<AccountEntity> foundersList) {
        this.foundersList = foundersList;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", pin=" + pin +
                ", password='" + password + '\'' +
                '}';
    }
}

