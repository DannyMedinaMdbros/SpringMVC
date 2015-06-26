package com.springboot.main.service;

import java.util.Collection;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.main.model.Greeting;
import com.springboot.main.repository.GreetingRepository;

@Service
@Transactional
public class GreetingServiceBean implements GreetingService {
	
	@Autowired
	private GreetingRepository greetingRepository;
	
	@Override
	public Collection<Greeting> findAll() {
		Collection<Greeting> greetings = greetingRepository.findAll();
		return greetings;
	}

	@Override
	public Greeting findOne(Long id) {
		Greeting greeting= greetingRepository.findOne(id);
		return greeting;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Greeting create(Greeting greeting) {
		if(greeting.getId() != null){
			//Cannot create Greeting with specified ID value
			return null;
		}
		
		Greeting savedGreeting = greetingRepository.save(greeting);
		
		//Illustrate the rollback
		if (savedGreeting.getId() == 4L) {
			throw new RuntimeException("Roll me back");
		}
		return savedGreeting;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Greeting update(Greeting greeting) {
		Greeting greetingPersisted = findOne(greeting.getId());
		if (greetingPersisted == null) {
			// Cannot update Greeting that hasn't been persisted
			return null;
			
		}
		Greeting updatedGreeting = greetingRepository.save(greeting);
		return updatedGreeting;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Long id) {
		greetingRepository.delete(id);;
	}

}
