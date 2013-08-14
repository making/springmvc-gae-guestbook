package com.example.guestbook.app.greeting.app;

import java.util.List;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.guestbook.domain.model.Greeting;
import com.example.guestbook.domain.service.greeting.GreetingService;

@Controller
@RequestMapping("greeting")
public class GreetingController {
	@Inject
	protected GreetingService greetingService;

	@Inject
	protected Mapper beanMapper;

	@ModelAttribute
	public GreetingForm setUpForm(
			@RequestParam(value = "guestbookName", required = false, defaultValue = "default") String guestbookName) {
		GreetingForm form = new GreetingForm();
		form.setGuestbookName(guestbookName);
		return form;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "guestbookName", required = false) String guestbookName,
			Model model) {
		List<Greeting> greetings = greetingService
				.findByGuestbookName(guestbookName);
		model.addAttribute("greetings", greetings);
		return "greeting/list";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(GreetingForm form, Model model,
			RedirectAttributes redirectAttributes) {
		Greeting greeting = beanMapper.map(form, Greeting.class);
		greetingService.save(greeting);
		redirectAttributes.addAttribute("guestbookName",
				form.getGuestbookName());
		return "redirect:/greeting";
	}
}
