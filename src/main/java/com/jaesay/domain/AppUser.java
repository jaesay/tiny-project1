package com.jaesay.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "App_User", 
        uniqueConstraints = {
                @UniqueConstraint(name = "APP_USER_UK", columnNames = "Email") })
@ToString
@EqualsAndHashCode(of="userName")
public class AppUser {

	@Id
    @Column(name = "User_Name", length = 36, nullable = false)
    private String userName;
  
    @Column(name = "Email", length = 128, nullable = false)
    private String email;
  
    @Column(name = "First_Name", length = 36, nullable = true)
    private String firstName;
  
    @Column(name = "Last_Name", length = 36, nullable = true)
    private String lastName;
  
    @Column(name = "Password", length = 128, nullable = false)
    private String password;
  
    @Column(name = "Enabled", length = 1, nullable = false)
    private boolean enabled;
    
    @CreationTimestamp
    @Column(name="Registration_Date", nullable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();
    
    @UpdateTimestamp
    @Column(name="updated_Date", nullable = false)
    private LocalDateTime updatedDate = LocalDateTime.now();
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="User_Name")
    private List<AppRole> roles;

}
