public class notification_factory {

    public Notification getnotification(String type) {
        if (type == null)
            return null;
        if (type == "SMS")
        {
            return new sms_notification();
        }
        else if (type == "Email")
        {
            return new email_notification();
        }
        else if (type == "WhatsApp")
        {
            return new whatsapp_notification();
        }
        return null;
    }
}
