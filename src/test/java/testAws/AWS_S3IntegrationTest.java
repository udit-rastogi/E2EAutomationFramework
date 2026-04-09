package testAws;

import awsintegrations.Download_S3Object;
import components.SecretsManager;
import org.testng.annotations.Test;

import software.amazon.awssdk.regions.Region;
import utilities.LoadConfig;

import java.util.Date;

public class AWS_S3IntegrationTest {
	protected LoadConfig config;

	final String component = System.getProperty("component", "awsconfiguration");

	@Test(groups = {"aws"})
	public void validate_S3Integration() {
		config = new LoadConfig(component);
		String accessKey = SecretsManager.getAwsAccessKey();
//		String accessKey = config.getComponentValue("iamuser","accessKey");
		String secretKey = SecretsManager.getAwsSecretKey();
//		String secretKey = config.getComponentValue("iamuser","secretKey");
		String objectName = config.getComponentValue("s3","objectName");
		String destination = config.getComponentValue("s3","destinationPath");
		String updatedDestinationPath = destination.replace("${fileName}","objectFile"+ System.currentTimeMillis());
		String bucketName = config.getComponentValue("s3","bucketName");
		Region region = Region.of(config.getComponentValue("region"));

		System.out.println("Access Key is : " + accessKey);

		Download_S3Object s3ObjDownload = new Download_S3Object();
		s3ObjDownload.downloadS3Object(accessKey,secretKey,region,bucketName,objectName,updatedDestinationPath);

	}
}
