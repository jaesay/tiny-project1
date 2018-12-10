package com.jaesay.domain;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of="providerUserId")
public class UserConnectionPK implements Serializable {

	private static final long serialVersionUID = -5869187892178769375L;

    private String userId;
    private String providerId; 
    private String providerUserId;
}
