package org.example.googlecloudfunctions;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.PartMaker;
import com.google.cloud.vertexai.generativeai.ResponseStream;

public class MyFunction {

    private static final String PROJECT_ID = "karuns-training-and-learning";
    private static final String LOCATION = "us-central1";
    private static final String IMAGE_URI = "gs://karuns-test-bucket/IMG_6116.JPEG";

    public static void main(String[] args) throws Exception{
        try (VertexAI vertexAi = new VertexAI(PROJECT_ID, LOCATION); ) {
            // Vision model must be used for multi-modal input
            GenerativeModel model = new GenerativeModel("gemini-1.5-pro-002", vertexAi);

            GenerateContentResponse response =
                    model.generateContent(ContentMaker.fromMultiModalData(
                            "Please create a caption describing the image",
                            PartMaker.fromMimeTypeAndData("image/jpeg", IMAGE_URI)
                    ));
            System.out.println(response);
            // Do something with the ResponseStream, which is an iterable.
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}