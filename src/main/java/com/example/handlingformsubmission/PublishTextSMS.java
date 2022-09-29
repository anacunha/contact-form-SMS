package com.example.handlingformsubmission;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.SnsException;

@Component("PublishTextSMS")
public class PublishTextSMS {

    public void sendMessage(final String id) {
        final Region region = Region.US_EAST_1;
        final AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(
                "AKIAIOSFODNN7EXAMPLE",
                "je7MtGbClwBF/2Zp9Utk/h3yCo8nvbEXAMPLEKEY");
        final SnsClient snsClient = SnsClient.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();

        final String message = "New item with ID " + id + " added to DynamoDB table";
        final String phoneNumber = "+16175550100";

        try {
            final PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .phoneNumber(phoneNumber)
                    .build();

            snsClient.publish(request);
        } catch (final SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}
