package com.cfy.thymeleaf.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cfy.thymeleaf.dao.MessageRepository;
import com.cfy.thymeleaf.entity.Message;
import com.cfy.thymeleaf.enums.PagesEnum;

/**
*@author    created by ChenFangYa
*@date  2020年1月21日---下午2:21:47
*@action
*/
@Controller
@RequestMapping("/")
public class MessageController {



	private final MessageRepository messageRepository;

	public MessageController(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@GetMapping
	public ModelAndView list() {
		Iterable<Message> messages = this.messageRepository.findAll();
		return new ModelAndView(PagesEnum.MESSAGES_LIST.getPath(), "messages", messages);
	}

	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Message message) {
		return new ModelAndView(PagesEnum.MESSAGES_VIEW.getPath(), "message", message);
	}

	@GetMapping(params = "form")
	public String createForm(@ModelAttribute Message message) {
		return PagesEnum.MESSAGES_FORM.getPath();
	}

	@PostMapping
	public ModelAndView create(@Valid Message message, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView(PagesEnum.MESSAGES_FORM.getPath(), "formErrors", result.getAllErrors());
		}
		message = this.messageRepository.save(message);
		redirect.addFlashAttribute("globalMessage", "Successfully created a new message");
		return new ModelAndView("redirect:/{message.id}", "message.id", message.getId());
	}

	@GetMapping("foo")
	public String foo() {
		throw new IllegalArgumentException("Expected exception in controller");
	}

	@GetMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		this.messageRepository.deleteMessage(id);
		Iterable<Message> messages = this.messageRepository.findAll();
		return new ModelAndView(PagesEnum.MESSAGES_LIST.getPath(), "messages", messages);
	}

	@GetMapping(value = "modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Message message) {
		return new ModelAndView(PagesEnum.MESSAGES_FORM.getPath(), "message", message);
	}


}
