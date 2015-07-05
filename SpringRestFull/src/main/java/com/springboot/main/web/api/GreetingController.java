package com.springboot.main.web.api;

import java.util.Collection;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.model.Greeting;
import com.springboot.main.service.EmailService;
import com.springboot.main.service.GreetingService;


@RestController
public class GreetingController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GreetingService greetingService;
	
	@Autowired
	private EmailService emailService;
	
	
	@RequestMapping(value="/api/greetings",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings(){
		
		logger.info("> getGreetings");
		
		Collection<Greeting> greetings = greetingService.findAll();
		
		logger.info("< getGreetings");
		
		return new ResponseEntity <Collection<Greeting>> (greetings, 
				HttpStatus.OK);
	}
	
	@RequestMapping( value = "/api/greetings/{id}",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> getGreeting(@PathVariable("id") Long id) {
		
		Greeting greeting= greetingService.findOne(id);
		if (greeting == null) {
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity <Greeting> (greeting, 
				HttpStatus.OK);
	}	
	@RequestMapping( value = "/api/greetings", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {
		Greeting savedGreeting = greetingService.create(greeting);
		return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);
	}	
	@RequestMapping(value = "/api/greetings/{id}", 
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting) {
		Greeting updatedGreeting = greetingService.update(greeting);
		if (updatedGreeting == null) {
			return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Greeting>(updatedGreeting,HttpStatus.OK);
	}
	@RequestMapping(value = "/api/greetings/{id}", 
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> deleteGreeting(@PathVariable("id") Long id, @RequestBody Greeting greeting) {
		greetingService.delete(id);
		return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
	}
	@RequestMapping( value = "/api/greetings/{id}/send", 
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> sendGreeting(@PathVariable("id") Long id, 
			@RequestParam(value= "wait", defaultValue = "false") boolean waitFroAsyncResult) {
		
		logger.info("> SendGreeting");
		
		Greeting greeting = null;
		try {
			greeting = greetingService.findOne(id);
			if (waitFroAsyncResult) {
				Future<Boolean> asyncResponse = emailService.sendAsyncWithResult(greeting);
				boolean emailSend = asyncResponse.get();
				logger.info("- greeting sent? {}",emailSend);
			}else {
				emailService.sendAsync(greeting);
			}
		} catch (Exception e) {
			logger.info("A problem occurred sending the Greeting.",e);
			return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("< getGreetings");
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}
	
}
