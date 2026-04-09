package awsintegrations;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.cloudwatchlogs.model.CreateLogGroupRequest;
import software.amazon.awssdk.services.cloudwatchlogs.model.CreateLogStreamRequest;
import software.amazon.awssdk.services.cloudwatchlogs.model.InputLogEvent;
import software.amazon.awssdk.services.cloudwatchlogs.model.PutLogEventsRequest;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.auth.credentials.*;

public class AddLogsToCloudWatch {

	public static void main(String[] args) {

//		CloudWatchLogsClient logsClient = CloudWatchLogsClient.create();
		AwsBasicCredentials awsCreds = AwsBasicCredentials.create(
		        "your_access_key",
		        "your_secret_key"
		);

		CloudWatchLogsClient logsClient = CloudWatchLogsClient.builder()
		        .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
		        .region(software.amazon.awssdk.regions.Region.AP_SOUTH_2)
		        .build();
		
		String logGroupName = "demo-log-group";
		String logStreamName = "demo-log-stream";

// Create Log Group
		logsClient.createLogGroup(CreateLogGroupRequest.builder().logGroupName(logGroupName).build());

// Create Log Stream
		logsClient.createLogStream(
				CreateLogStreamRequest.builder().logGroupName(logGroupName).logStreamName(logStreamName).build());

// Put Log Event
		InputLogEvent logEvent = InputLogEvent.builder().message("Test log from automation framework")
				.timestamp(System.currentTimeMillis()).build();

		PutLogEventsRequest request = PutLogEventsRequest.builder().logGroupName(logGroupName)
				.logStreamName(logStreamName).logEvents(logEvent).build();

		logsClient.putLogEvents(request);

	}

}
