package com.example.sms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends AbstractEntity{
    private static final long serialVersionUID = -2613192309551646528L;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    private String passcode;

    private String email;
}
