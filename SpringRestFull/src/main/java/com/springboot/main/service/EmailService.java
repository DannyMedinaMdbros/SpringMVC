package com.springboot.main.service;

import java.util.concurrent.Future;

import com.springboot.main.model.Greeting;

public interface EmailService {
	Boolean send(Greeting greeting);
	
	void sendAsync(Greeting greeting);
	
	Future<Boolean> sendAsyncWithResult(Greeting greeting);
}
