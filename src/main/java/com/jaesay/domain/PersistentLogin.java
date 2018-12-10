package com.jaesay.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "persistent_logins")
@Getter
@Setter
public class PersistentLogin {
	@Id
    @Column(name = "series", length = 64, nullable = false)
    private String series;
 
    @Column(name = "username", length = 64, nullable = false)
    private String userName;
 
    @Column(name = "token", length = 64, nullable = false)
    private String token;
 
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_Used", nullable = false)
    private Date lastUsed;
}
