package com.springboot.main.service;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.springboot.main.model.Greeting;

@Service
public class EmailServiceBean implements EmailService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Boolean send(Greeting greeting) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendAsync(Greeting greeting) {
		// TODO Auto-generated method stub

	}

	@Override
	public Future<Boolean> sendAsyncWithResult(Greeting greeting) {
		// TODO Auto-generated method stub
		return null;
	}

}
