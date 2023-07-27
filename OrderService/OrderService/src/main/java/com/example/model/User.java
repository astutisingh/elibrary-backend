package com.example.model;

import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "users")
public class User {

  private String userId;
  @NotBlank
  @Size(max = 20)
  private String username;
  @NotBlank
  @Size(max = 50)
  @Email
  private String email;
  
  
  public User() {
	super();
	// TODO Auto-generated constructor stub
}


public User(String userId, String username, String email, String password) {
	  	this.userId = userId;
	    this.username = username;
	    this.email = email;
	  }


public String getUserId() {
	return userId;
}


public void setUserId(String userId) {
	this.userId = userId;
}


public String getUsername() {
	return username;
}


public void setUsername(String username) {
	this.username = username;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}
  
  

}