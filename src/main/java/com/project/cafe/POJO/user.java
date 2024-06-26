package com.project.cafe.POJO;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

//@NamedQuery(name = "user.findByEmailId", query = "select u from user u where u.email =: email")

//@NamedQuery(name = "user.getAllUser", query = "select new com.project.cafe.wrapper.UserWrapper(u.id,u.name,u.email,u.contactNumber,u.status) from user u where u.role='user'")

//@NamedQuery(name = "user.updateStatus", query = "update user u set u.status=:status where u.id=:id")



@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "user")
public class user implements Serializable{
	
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
        
    @Column(name = "name")
    private String name;

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status;
    
    @Column(name = "role")
    private String role;


}
