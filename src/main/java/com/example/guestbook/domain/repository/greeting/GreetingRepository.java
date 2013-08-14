package com.example.guestbook.domain.repository.greeting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.guestbook.domain.model.Greeting;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.users.User;

@Repository
public class GreetingRepository {
	public void save(Greeting greeting) {
		Key guestbookKey = KeyFactory.createKey("Guestbook",
				greeting.getGuestbookName());
		Entity entity = new Entity("Greeting", guestbookKey);
		entity.setProperty("user", greeting.getUser());
		entity.setProperty("date", greeting.getDate());
		entity.setProperty("content", greeting.getContent());

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		datastore.put(entity);
	}

	public List<Greeting> findByGuestbookName(String guestbookName) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Key guestbookKey = KeyFactory.createKey("Guestbook", guestbookName);
		// Run an ancestor query to ensure we see the most up-to-date
		// view of the Greetings belonging to the selected Guestbook.
		Query query = new Query("Greeting", guestbookKey).addSort("date",
				Query.SortDirection.DESCENDING);
		List<Entity> entities = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(5));
		List<Greeting> greetings = new ArrayList<>(entities.size());
		for (Entity entity : entities) {
			User user = (User) entity.getProperty("user");
			String content = (String) entity.getProperty("content");
			Date date = (Date) entity.getProperty("date");
			greetings.add(new Greeting(guestbookName, user, content, date));
		}
		return greetings;
	}
}
