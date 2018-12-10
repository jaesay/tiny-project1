package com.jaesay.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
@Table(name = "members", 
        uniqueConstraints = {
        		@UniqueConstraint(name = "member_name_uk", columnNames = "member_name"),
                @UniqueConstraint(name = "member_email_uk", columnNames = "email") })
@ToString
@EqualsAndHashCode(of="memberName")
public class Member {
	@Id
    @GeneratedValue
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "member_name", length = 36, nullable = false)
    private String memberName;
  
    @Column(name = "email", length = 128, nullable = false)
    private String email;
  
    @Column(name = "first_name", length = 36, nullable = true)
    private String firstName;
  
    @Column(name = "last_name", length = 36, nullable = true)
    private String lastName;
  
    @Column(name = "password", length = 128, nullable = false)
    private String password;
  
    @Column(name = "enabled", length = 1, nullable = false)
    private boolean enabled;
    
    @CreationTimestamp
    @Column(name="registration_date", nullable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();
    
    @UpdateTimestamp
    @Column(name="updated_date", nullable = false)
    private LocalDateTime updatedDate = LocalDateTime.now();
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="member_name")
    private List<Role> roles;

}
