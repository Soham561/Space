import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties config = new Properties();
        // Cloud-ready: load from environment variables
        config.setProperty("nasa.api.key", System.getenv("API_KEY"));
        config.setProperty("email.sender", System.getenv("SENDER_EMAIL"));
        config.setProperty("email.password", System.getenv("APP_PASSWD"));
        config.setProperty("email.recipient", System.getenv("RECEIVER_EMAIL"));

        String fact = ApiFetcher.fetchSpaceFact(config);
        EmailSender.sendEmail("Today's Space Fact", fact, config);
    }
}
