package us.jonathans.view;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import us.jonathans.interface_adapter.notifyuser.NotificationService;

public class TwilioNotificationService implements NotificationService {
    private final String ACCOUNT_SID;
    private final String AUTH_TOKEN;
    private final String twilioPhoneNumber;

    public TwilioNotificationService(String accountSID, String authToken, String twilioPhoneNumber) {
        this.ACCOUNT_SID = accountSID;
        this.AUTH_TOKEN = authToken;
        this.twilioPhoneNumber = twilioPhoneNumber;
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    @Override
    public void sendNotification(String phoneNumber, String message) {
        try {
            Message sentMessage = Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(twilioPhoneNumber),
                    message
            ).create();
            System.out.println("Message sent! SID: " + sentMessage.getSid());
        } catch (Exception e) {
            System.err.println("Failed to send message: " + e.getMessage());
        }
    }
}