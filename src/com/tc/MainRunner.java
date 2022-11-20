package com.tc;

import com.tc.console.ConsoleLogger;
import com.tc.console.Logger;
import com.tc.models.Contact;
import com.tc.models.User;
import com.tc.services.CallService;
import com.tc.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class MainRunner {

    public static void main(String[] args) {
        Logger logger = new ConsoleLogger();
        UserService userService = new UserService();
        CallService callService = new CallService(logger, userService);

        User user = new User(1, "Mukul", 9909888089L);
        User user1 = new User(2, "Jack", 9909888080L);
        User user2 = new User(3, "Mohan", 9909888088L);
        User user3 = new User(4, "Ankit", 9909888020L);
        User user4 = new User(5, "Lokesh", 8700000000L);

        userService.createUser(user);
        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);
        userService.createUser(user4);

        logger.log("All users created successfully");

        Contact user1Contact = new Contact("Somya", 9900000000L);
        Contact user2Contact = new Contact("Mukesh", 9900000001L);
        Contact user3Contact = new Contact("Rajesh", 9900000002L);
        Contact user4Contact = new Contact("Gugu", 9900000009L);

        userService.importContact(user, Set.of(user1Contact));
        userService.importContact(user1, Set.of(user2Contact, user3Contact));
        userService.importContact(user2, Set.of(user3Contact, user4Contact));

        logger.log("contact imported successfully");
        callService.blockPhone(user, 9900000009L);
        callService.call(user, 9900000009L);

        callService.markAsMissedCall(user1, user.getPhoneNumber());
        callService.markAsMissedCall(user1, user2.getPhoneNumber());
        callService.markAsMissedCall(user1, user3.getPhoneNumber());
        callService.markAsMissedCall(user1, user.getPhoneNumber());

        logger.log(user1.getPhoneInfo().getMissedCalls());

        callService.clearMissedCall(user1,user.getPhoneNumber());
        logger.log(user1.getPhoneInfo().getMissedCalls());

        callService.blockPhone(user1, user.getPhoneNumber());
        callService.call(user1, user.getPhoneNumber());
        callService.markAsMissedCall(user1, user.getPhoneNumber());

        callService.unblockPhone(user1, user3.getPhoneNumber());

        callService.call(user1, user.getPhoneNumber());

        callService.unblockPhone(user1, user.getPhoneNumber());

        callService.call(user1, user.getPhoneNumber());

        callService.call(user4, 9699000000L);

    }
}
