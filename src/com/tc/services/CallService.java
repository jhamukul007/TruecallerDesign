package com.tc.services;

import com.tc.console.Logger;
import com.tc.exceptions.NotExistException;
import com.tc.models.Contact;
import com.tc.models.User;

public class CallService {

    private final Logger logger;
    private final UserService userService;

    public CallService(Logger logger, UserService userService) {
        this.logger = logger;
        this.userService = userService;
    }

    public void call(User user, Long phoneNumber) {
        if (user.getPhoneInfo().isBlocked(phoneNumber)) {
            logger.log("user has been blocked phoneNumber " + phoneNumber);
            return;
        }
        Contact contact = userService.getCallerInfo(phoneNumber);
        if (contact.getNoOfSpams() > 10)
            logger.log("Spam user " + contact.getName() + " calling");
        else{
            String content = contact.getName()  != null ? contact.getName() : contact.getPhone()+"";
            logger.log(content + " calling !!!");
        }

    }

    public void markAsMissedCall(User user, Long phoneNumber) {
        if (user.getPhoneInfo().isBlocked(phoneNumber)) {
            logger.log("user has been blocked phoneNumber " + phoneNumber);
            return;
        }
        Contact contact = userService.getCallerInfo(phoneNumber);
        if (contact.getNoOfSpams() > 10)
            logger.log("Spam user " + contact.getName() + " calling");
        else
            logger.log("user " + contact.getName() + " calling");
        logger.log("Missed call from " + contact.getName());
        user.getPhoneInfo().addMissingPhone(phoneNumber);
    }

    public void clearMissedCall(User user, Long phoneNumber) {
        user.getPhoneInfo().markMissedCallSeen(phoneNumber);
    }

    public void reportSpam(Long phone) {
        Contact contact = userService.getCallerInfo(phone);
        if (contact == null)
            throw new NotExistException("CallId doesn't exist");
        contact.markSpam();
    }

    public void blockPhone(User user, Long phoneNumber) {
        user.getPhoneInfo().blockedPhone(phoneNumber);
        logger.log("Blocked " + phoneNumber + " successfully");
    }

    public void unblockPhone(User user, Long phoneNumber){
        boolean isUnblocked = user.getPhoneInfo().unBlockedPhone(phoneNumber);
        if(isUnblocked)
            logger.log(phoneNumber + " un blocked by user "+ user.getName());
        else
            logger.log(phoneNumber + "never not blocked by user "+ user.getName());
    }

}
