package com.bezkoder.spring.jpa.h2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ESTABLISHMENTS")
public class Establishment {

    @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
  
    @Column(name = "userid")
    private String userid;
  
    @Column(name = "title")
    private String title;
  
    @Column(name = "description")
    private String description;
  
    @Column(name = "address")
    private String address;

    @Column(name = "image")
    private String image;

    @Column(name = "open")
    private String open;

    @Column(name = "close")
    private String close;

    public Establishment() {

  }

  public Establishment(String userid, String title, String description, String address, String image, String open, String close) {
      this.userid = userid;
      this.title = title;
      this.description = description;
      this.address = address;
      this.image = image;
      this.open = open;
      this.close = close;
  }
  
  public long getId() {
    return id;
  }
  
  public String getUser() {
    return userid;
  }

  public void setUser(String userid) {
      this.userid = userid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
      this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
      this.description = description;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
  
  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
  
  public String getOpen() {
    return open;
  }

  public void setOpen(String open) {
    this.open = open;
  }
  
  public String getClose() {
    return close;
  }

  public void setClose(String close) {
      this.close = close;
  }
  
}
