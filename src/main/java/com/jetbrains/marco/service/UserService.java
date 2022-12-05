package com.jetbrains.marco.service;

import com.jetbrains.marco.UserDto;

public class UserService {

    MailService mailService;

    public UserService(MailService mailService) {
        this.mailService = mailService;
    }

    public boolean register(UserDto userDto) {
        boolean welcomeEmailSuccess = mailService.sendWelcomeEmail(userDto);
        // some other stuff here
        return welcomeEmailSuccess;
    }
}
