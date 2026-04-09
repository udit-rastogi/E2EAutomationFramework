package components;

public class SecretsManager {

    public static String getAwsAccessKey() {
        return System.getenv("AWS_ACCESS_KEY");
    }

    public static String getAwsSecretKey() {
        return System.getenv("AWS_SECRET_KEY");
    }

}
