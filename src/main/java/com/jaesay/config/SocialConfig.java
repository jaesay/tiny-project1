package com.jaesay.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import com.jaesay.Social.ConnectionSignUpImpl;
import com.jaesay.repository.MemberRepository;

@Configuration
@EnableSocial
@PropertySource("classpath:config/oauth2-config.properties")
public class SocialConfig implements SocialConfigurer {

	private boolean autoSignUp = false;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {

		try {
			this.autoSignUp = Boolean.parseBoolean(env.getProperty("social.auto-signup"));
		} catch (Exception e) {
			this.autoSignUp = false;
		}

		GoogleConnectionFactory gfactory = new GoogleConnectionFactory(env.getProperty("google.client.id"),
				env.getProperty("google.client.secret"));

		gfactory.setScope(env.getProperty("google.scope"));

		cfConfig.addConnectionFactory(gfactory);
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {

		JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,
				connectionFactoryLocator,

				Encryptors.noOpText());

		if (autoSignUp) {
			ConnectionSignUp connectionSignUp = new ConnectionSignUpImpl(memberRepository);
			usersConnectionRepository.setConnectionSignUp(connectionSignUp);
		} else {
			usersConnectionRepository.setConnectionSignUp(null);
		}
		return usersConnectionRepository;
	}

	@Bean
	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator,
			ConnectionRepository connectionRepository) {
		return new ConnectController(connectionFactoryLocator, connectionRepository);
	}
}
