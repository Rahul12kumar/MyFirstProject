package com.pysch.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

	public static ApplicationContextProvider contextProvider()
	{
		return new ApplicationContextProvider();
	}
}
