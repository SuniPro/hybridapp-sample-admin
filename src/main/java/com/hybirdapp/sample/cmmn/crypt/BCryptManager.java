package com.hybirdapp.sample.cmmn.crypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptManager extends BCryptPasswordEncoder {

	private BCryptManager () {
		
	}
	
	private static interface Singleton {
		final BCryptManager INSTANCE = new BCryptManager();
	}
	
	public static BCryptManager getInstance(){
	    return Singleton.INSTANCE;
	}
}
