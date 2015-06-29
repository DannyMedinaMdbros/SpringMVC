package com.springboot.main.service;

import java.util.Collection;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.springboot.main.model.Greeting;
import com.springboot.main.repository.GreetingRepository;

@Service
public class GreetingServiceBean implements GreetingService {
	
	@Autowired
	private GreetingRepository greetingRepository;
	
	@Override
	public Collection<Greeting> findAll() {
		Collection<Greeting> greetings = greetingRepository.findAll();
		return greetings;
	}

	@Override
	@Cacheable(value= "gretings", key="#id")
	public Greeting findOne(Long id) {
		Greeting greeting= greetingRepository.findOne(id);
		return greeting;
	}

	@Override
	@CachePut(value = "gretings", key = "#result.id")
	public Greeting create(Greeting greeting) {
		if(greeting.getId() != null){
			//Cannot create Greeting with specified ID value
			return null;
		}
		
		Greeting savedGreeting = greetingRepository.save(greeting);
		return savedGreeting;
	}

	@Override
	@CachePut(value = "gretings", key = "#greting.id")
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
	@CacheEvict(value= "gretings", key="#id")
	public void delete(Long id) {
		greetingRepository.delete(id);;
	}
	
	@Override
	@CacheEvict(value= "gretings", allEntries = true)
	public void evictCache() {
		// TODO Auto-generated method stub

	}
}
