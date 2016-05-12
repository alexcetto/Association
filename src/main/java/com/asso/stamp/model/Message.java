package com.asso.stamp.model;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.Transient;

@Entity
@NamedQuery(name = Message.FIND_ALL, query = "SELECT m FROM Message m")
public class Message implements Serializable {
    
    public final static String FIND_ALL = "Message.findAll";
    
    @Id
    @GeneratedValue
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSent;
    private String userSender;
    @Column(length = 150)
    private String message;
 
    public Date getDateSent() {
        return dateSent;
    }
 
    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public String getUser() {
        return userSender;
    }
 
    public void setUser(String user) {
        this.userSender = user;
    }
}
