package com.example.backend.persistence;


import jakarta.persistence.*;

@Entity
@Table(name = "user" ,schema = "tradingdashboard")
public class User {


@Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer user_id ;



private  String username ;
private  String password ;

private  String complete_name ;

private UserStatus status ;


 public Integer getUser_id() {
  return user_id;
 }

 public String getUsername() {
  return username;
 }

 public String getPassword() {
  return password;
 }

 public void setUser_id(Integer user_id) {
  this.user_id = user_id;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public void setPassword(String password) {
  this.password = password;
 }



 public void setStatus(UserStatus status) {
  this.status = status;
 }

 public String getComplete_name() {
  return complete_name;
 }

 public UserStatus getStatus() {
  return status;
 }

 public void setComplete_name(String complete_name) {
  this.complete_name = complete_name;

 }
}
