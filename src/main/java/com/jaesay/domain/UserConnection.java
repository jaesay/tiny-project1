package com.jaesay.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "userconnection")
@Getter
@Setter
@IdClass(UserConnectionPK.class)
public class UserConnection implements Serializable {

	private static final long serialVersionUID = 8220609934926026668L;

	@Id
    @Column(name = "userid", length = 50, nullable = false)
    private String userId;
 
    @Id
    @Column(name = "providerid", length = 50, nullable = false)
    private String providerId;
 
    @Id
    @Column(name = "provideruserid", length = 50, nullable = false)
    private String providerUserId;
 
    @Column(name = "rank", nullable = false)
    private int rank;
 
    @Column(name = "displayname", length = 255, nullable = true)
    private String displayName;
 
    @Column(name = "profileurl", length = 512, nullable = true)
    private String profileUrl;
 
    @Column(name = "imageurl", length = 512, nullable = true)
    private String imageUrl;
 
    @Column(name = "accesstoken", length = 512, nullable = true)
    private String accessToken;
 
    @Column(name = "secret", length = 512, nullable = true)
    private String secret;
 
    @Column(name = "refreshtoken", length = 512, nullable = true)
    private String refreshToken;
 
    @Column(name = "expiretime", nullable = true)
    private Long expireTime;
}
