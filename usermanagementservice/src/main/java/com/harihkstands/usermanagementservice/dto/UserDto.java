package com.harihkstands.usermanagementservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserDto {

    private Long Id;

    @NotEmpty(message = "User first name should not be empty or null")
    private String firstName;
    @NotEmpty(message = "User last name should not be empty or null")
    private String lastName;
    @NotEmpty(message = "User email should not be empty or null")
    @Email(message = "User email should be valid")
    private String email;

    public UserDto() {
    }

    public UserDto(Long id, String firstName, String lastName, String email) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}