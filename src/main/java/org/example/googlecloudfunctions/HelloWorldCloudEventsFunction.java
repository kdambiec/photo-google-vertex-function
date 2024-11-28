package org.example.googlecloudfunctions;

import jakarta.enterprise.context.ApplicationScoped;

import com.google.cloud.functions.CloudEventsFunction;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.PartMaker;
import com.google.cloud.vertexai.generativeai.ResponseStream;

import io.cloudevents.CloudEvent;

@ApplicationScoped
public class HelloWorldCloudEventsFunction implements CloudEventsFunction {

    private static final String PROJECT_ID = "<your project id>";
    private static final String LOCATION = "<location>";
    private static final String IMAGE_URI = "<gcs uri to your image>";

    @Override
    public void accept(CloudEvent cloudEvent) throws Exception {
        System.out.println("Receive event Id: " + cloudEvent.getId());
        System.out.println("Receive event Subject: " + cloudEvent.getSubject());
        System.out.println("Receive event Type: " + cloudEvent.getType());
        System.out.println("Receive event Data: " + new String(cloudEvent.getData().toBytes()));
    }
}
