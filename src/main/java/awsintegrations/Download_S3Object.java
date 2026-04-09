package awsintegrations;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.core.sync.ResponseTransformer;

import java.nio.file.Paths;

public class Download_S3Object {

    public void downloadS3Object(String accessKey, String secretKey,Region region, String bucketName, String key, String destinationPath){

        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);

        // Create S3 client
        S3Client s3Client = S3Client.builder()
                .region(region) // Change region if needed
                .credentialsProvider(() -> awsCreds)
                .build();

        try {
            // Build request
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            // Download file
            s3Client.getObject(getObjectRequest,
                    ResponseTransformer.toFile(Paths.get(destinationPath)));

            System.out.println("File downloaded successfully to: " + destinationPath);

        } catch (Exception e) {
            System.err.println("Error downloading file: " + e.getMessage());
        } finally {
            s3Client.close();
        }


    }

}
