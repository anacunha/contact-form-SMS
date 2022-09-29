package com.example.handlingformsubmission;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Component("DynamoDbEnhanced")
public class DynamoDbEnhanced {

    public void injectDynamoItem(final Form form) {

        final Region region = Region.US_EAST_1;
        final DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
                .region(region)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        try {
            DynamoDbEnhancedClient dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
                    .dynamoDbClient(dynamoDbClient)
                    .build();

            final DynamoDbTable<FormItems> dynamoDbTable = dynamoDbEnhancedClient.table("Form", TableSchema.fromBean(FormItems.class));
            final FormItems formItems = new FormItems();
            formItems.setId(form.getId());
            formItems.setName(form.getName());
            formItems.setMessage(form.getBody());
            formItems.setTitle(form.getTitle());

            final PutItemEnhancedRequest<FormItems> request = PutItemEnhancedRequest.builder(FormItems.class)
                    .item(formItems)
                    .build();

            dynamoDbTable.putItem(request);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
