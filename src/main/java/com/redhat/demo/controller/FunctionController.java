package com.redhat.demo.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/fn")
public class FunctionController {
	
	static RestTemplate restTemplate = new RestTemplate();

//	http://localhost:8080/fn/fib?lv=44
//	curl -d "lv=2" -X POST http://localhost:8080/fn/fib
	@RequestMapping(value = "/fib", method = RequestMethod.GET,params = {"lv"})
	public String runFibLv(@RequestParam(value = "lv") int lv,@RequestHeader Map<String, String> headers) throws UnknownHostException {

		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date1 = new Date();
		
		
		System.out.println("GET--------------------------------------------------");
		
		headers.forEach((key, value) -> {
			 System.out.println(String.format("Header '%s' = %s", key, value));
		    });
		 
		System.out.println("Start time:"+format.format(date1));
		System.out.println( "My jost Name is :" + InetAddress.getLocalHost().getHostName());
		System.out.println( "Lv:" +lv );
		
		System.out.println("--------------------------------------------------");
		
		 
		
		fib(lv);
		
		Date date2 = new Date();
		System.out.println("End time:"+format.format(date2));
		Long difference = date2.getTime() - date1.getTime(); 
		
		return  difference.toString() + "ms";
	}
	
	@RequestMapping(value = "/fib", method = RequestMethod.POST,params = {"lv"})
	public String runPostFibLv(@RequestParam(value = "lv") int lv,@RequestHeader Map<String, String> headers) throws UnknownHostException {

		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date1 = new Date();
		
		
		System.out.println("POST--------------------------------------------------");
		
		headers.forEach((key, value) -> {
			 System.out.println(String.format("Header '%s' = %s", key, value));
		    });
		 
		System.out.println("Start time:"+format.format(date1));
		System.out.println( "My jost Name is :" + InetAddress.getLocalHost().getHostName());
		System.out.println( "Lv:" +lv );
		
		System.out.println("--------------------------------------------------");
		
		 
		

		String fooResourceUrl
		  = "http://rest-backend:8080/fn/fib?lv=1";
		ResponseEntity<String> response
		  = restTemplate.getForEntity(fooResourceUrl, String.class);
		
		System.out.println(response.getBody().toString());
		

		Date date2 = new Date();
		System.out.println("End time:"+format.format(date2));
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
