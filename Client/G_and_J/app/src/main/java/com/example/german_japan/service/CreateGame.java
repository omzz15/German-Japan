package com.example.german_japan.service;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;
import com.example.german_japan.model.LambdaRequest;
import com.example.german_japan.model.LambdaResponse;

public interface CreateGame {
    @LambdaFunction
    LambdaResponse createGame(LambdaRequest request);
}
