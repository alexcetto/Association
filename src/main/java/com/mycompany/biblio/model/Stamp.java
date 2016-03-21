/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.biblio.model;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;


@Entity
@NamedQuery(name = Stamp.FIND_ALL, query = "SELECT b FROM Stamp b")
public class Stamp implements Serializable {
   public final static String FIND_ALL = "Stamp.findAll";
   public final static String Del_SOM = "Stamp.delete";

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String title;
    private Float price;
    private String country;
    @Column(length = 2000)
    private String description;
    private String reference;
    private String image;
    @Transient
    private Boolean selected;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Stamp() {
    }

    public Stamp(String title, Float price, String country, String description, String ref, String image) {
        this.title = title;
        this.price = price;
        this.country = country;
        this.description = description;
        this.reference = ref;
        this.image = image;
        
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRef() {
        return reference;
    }

    public void setRef(String ref) {
        this.reference = ref;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String img) {
        this.image = image;
    }

    public Boolean isSelected() {
        return selected;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}

