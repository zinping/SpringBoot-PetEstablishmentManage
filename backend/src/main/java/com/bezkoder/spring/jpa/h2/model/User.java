package com.bezkoder.spring.jpa.h2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column(name = "birthday")
  private String birthday;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "member_type")
  private boolean member_type; // false: customer, true: owner

  public User() {

  }

  public User(String name, String surname, String birthday, String email, String password, boolean member_type) {
    this.name = name;
    this.surname = surname;
    this.birthday = birthday;
    this.email = email;
    this.password = password;
    this.member_type = member_type;
  }

  public long getId() {
      return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
      this.name = name;
  }
  
  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
      this.surname = surname;
  }
  
  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
      this.birthday = birthday;
  }
  
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
      this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
      this.password = password;
  }
  
  public boolean getMemberType() {
    return member_type;
  }

  public void setMemberType(boolean member_type) {
      this.member_type = member_type;
  }

//   @Override
//   public String toString() {
//     return "User [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
//   }

}
