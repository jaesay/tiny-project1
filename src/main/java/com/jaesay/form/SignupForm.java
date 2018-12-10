package com.jaesay.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;

import com.jaesay.support.validator.EqualsPropertyValues;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsPropertyValues(property = "password", comparingProperty = "passwordRepeat")
@NoArgsConstructor
public class SignupForm {
	@NotNull
	@Size(max = 36, min = 4)
	private String memberName;
	@NotNull
	@Size(max = 128, min = 5)
	private String email;
	@NotNull
	@Size(max = 36, min = 4)
	private String firstName;
	@NotNull
	@Size(max = 36, min = 1)
	private String lastName;
	@NotNull
	@Size(max = 20, min = 3)
	private String password;
	@NotNull
	@Size(max = 20, min = 3)
	private String passwordRepeat;
	
	private String signInProvider;
    private String providerUserId;
	
	public SignupForm(Connection<?> connection) {
        UserProfile socialUserProfile = connection.fetchUserProfile();
        this.memberName = socialUserProfile.getUsername();
        this.email = socialUserProfile.getEmail();
        this.firstName = socialUserProfile.getFirstName();
        this.lastName = socialUserProfile.getLastName();
 
        ConnectionKey key = connection.getKey();
        this.signInProvider = key.getProviderId();
        this.providerUserId = key.getProviderUserId();
    }
}