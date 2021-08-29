package com.redhat.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fn")
public class FunctionController {

//	http://localhost:8080/fn/fib?lv=44
	@RequestMapping(value = "/fib", method = RequestMethod.GET,params = {"lv"})
	public String runFibLv(@RequestParam(value = "lv") int lv) {

		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date1 = new Date();
		
		
		
		fib(lv);
		
		Date date2 = new Date();
		Long difference = date2.getTime() - date1.getTime(); 
		
		return  difference.toString() + "ms";
	}
	


	   

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/get-greeting", method = RequestMethod.GET)
	public String greeting() {
		/**
		 * @LocaleContextHolder.getLocale() Return the Locale associated with the given
		 * user context,if any, or the system default Locale otherwise. This is
		 * effectively a replacement for Locale.getDefault(), able to optionally respect
		 * a user-level Locale setting.
		 */

		return messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());
	}

	@RequestMapping(value = "/get-greeting-name", method = RequestMethod.GET)
	public String greetingWithName() {
		String[] params = new String[] { "Ikhiloya", "today" };
		return messageSource.getMessage("good.morning.name", params, LocaleContextHolder.getLocale());
	}

	public static long fib(int n) {
		if (n == 1)
			return 1;
		else if (n == 2)
			return 1;
		else
			return fib(n - 1) + fib(n - 2);
	}

}
