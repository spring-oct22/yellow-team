package com.example.winery.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

public class Region {
    public Set<Wine> getWines() {
        return wines;
    }



   public void setWines(Set<Wine> wines) {
        this.wines = wines;
    }



   @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String country;
    
    @OneToMany(mappedBy = "region")
    private Set<Wine> wines;
    
    public Region() {
        super();
    }



   public Integer getId() {
        return id;
    }



   public void setId(Integer id) {
        this.id = id;
    }



   public String getName() {
        return name;
    }



   public void setName(String name) {
        this.name = name;
    }



   public String getCountry() {
        return country;
    }



   public void setCountry(String country) {
        this.country = country;
    }




    



}
