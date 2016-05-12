/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.asso.stamp.model;


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
    private int releaseyear;
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

    public Stamp(String title, Float price, String country, String description, String ref, String image, int releaseyear) {
        this.title = title;
        this.price = price;
        this.country = country;
        this.description = description;
        this.reference = ref;
        this.image = image;
        this.releaseyear = releaseyear;
        
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

    public String getReference() {
        return reference;
    }

    public void setReference(String ref) {
        this.reference = ref;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String img) {
        this.image = img;
    }
    
    public int getReleaseyear() {
        return releaseyear;
    }
    public void setReleaseyear(int releaseyear) {
        this.releaseyear = releaseyear;
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

