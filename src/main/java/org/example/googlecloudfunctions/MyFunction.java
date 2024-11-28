package org.example.googlecloudfunctions;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.api.GenerationConfig;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.PartMaker;
import com.google.cloud.vertexai.generativeai.ResponseStream;

public class MyFunction {

    private static final String PROJECT_ID = "karuns-training-and-learning";
    private static final String LOCATION = "us-central1";
    private static final String IMAGE_URI = "gs://karuns-test-bucket/IMG_6116.JPEG";

    public static void main(String[] args) throws Exception {
        try (VertexAI vertexAi = new VertexAI(PROJECT_ID, LOCATION);) {
            GenerationConfig generationConfig = GenerationConfig.newBuilder().
                    setMaxOutputTokens(8192).
                    setTemperature(1).
                    setCandidateCount(1).
                    build();
            // Vision model must be used for multi-modal input
            GenerativeModel model = new GenerativeModel.Builder().
                    setModelName("gemini-1.5-pro-002").
                    setVertexAi(vertexAi).
                    setGenerationConfig(generationConfig).
                    build();
            GenerateContentResponse captionResponse =
                    model.generateContent(ContentMaker.fromMultiModalData(
                            PartMaker.fromMimeTypeAndData("image/jpeg", IMAGE_URI),
                            "Create a caption for the photo. Also describe any clothing worn by the main person in the photo as part of the caption. Exclude any people in the background from the caption."
                    ));
            System.out.println(captionResponse);
            // Do something with the ResponseStream, which is an iterable.
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
