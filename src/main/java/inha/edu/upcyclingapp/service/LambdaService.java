package inha.edu.upcyclingapp.service;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class LambdaService {

    private final AWSLambda awsLambda;

    public LambdaService() {
        this.awsLambda = AWSLambdaClientBuilder.standard().build();
    }

    public String invokeLambda(String functionName, String payload) {
        InvokeRequest invokeRequest = new InvokeRequest()
                .withFunctionName(functionName)
                .withPayload(payload);

        InvokeResult invokeResult = awsLambda.invoke(invokeRequest);
        return new String(invokeResult.getPayload().array(), StandardCharsets.UTF_8);
    }
}