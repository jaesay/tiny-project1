package com.jaesay.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "roles")
@ToString
@EqualsAndHashCode(of="roleId")
public class Role {
	public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
     
    @Id
    @GeneratedValue
    @Column(name = "role_id", nullable = false)
    private Long roleId;
  
    @Column(name = "role_name", length = 30, nullable = false)
    private String roleName;
}
