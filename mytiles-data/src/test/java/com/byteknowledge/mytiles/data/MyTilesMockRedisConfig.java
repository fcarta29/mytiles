package com.byteknowledge.mytiles.data;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ComponentScan("com.byteknowledge.mytiles")
@PropertySources({
	@PropertySource("classpath:/redis.properties"),
	@PropertySource("classpath:/test-redis.properties")
})
public class MyTilesMockRedisConfig extends MyTilesRedisConfig {
	// User this class to override configuration 
	// redis server config and use an embedded version instead for integration testing
	
	
	
}

