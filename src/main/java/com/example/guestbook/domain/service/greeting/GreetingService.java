package com.example.guestbook.domain.service.greeting;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.guestbook.domain.model.Greeting;
import com.example.guestbook.domain.repository.greeting.GreetingRepository;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Service
public class GreetingService {
	@Inject
	protected GreetingRepository greetingRepository;

	public void save(Greeting greeting) {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		greeting.setUser(user);
		greeting.setDate(new Date());

		greetingRepository.save(greeting);
	}

	public List<Greeting> findByGuestbookName(String guestbookName) {
		return greetingRepository
				.findByGuestbookName(guestbookName == null ? "default"
						: guestbookName);
	}
}
