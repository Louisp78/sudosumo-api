package com.sudosumo.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;


@Controller
public class UserCron {
   	private static final Logger log = LoggerFactory.getLogger(UserCron.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	private final UserRepository userRepository;

	@Autowired
	public UserCron(UserRepository repository) {
		this.userRepository = repository;
	}


	@Scheduled(cron = "0 0 0 * * *") // Every day at midnight
	public void refillUserLives() {
		log.info("The users lives will now being refilled", dateFormat.format(new Date()));
		userRepository.refillLifesForEveryone();
	} 
}
