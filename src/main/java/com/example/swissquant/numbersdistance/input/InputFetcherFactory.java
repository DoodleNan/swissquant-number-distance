package com.example.swissquant.numbersdistance.input;

import com.example.swissquant.numbersdistance.input.imp.FileInputFetcher;
import com.example.swissquant.numbersdistance.model.InputSource;

public class InputFetcherFactory {
    public static InputFetcher getInputFetcher(InputSource inputSource, String identifier) {
        switch (inputSource){
            case FILE: return new FileInputFetcher(identifier);
            case DATABASE: return new FileInputFetcher(identifier);
            case CLOUD: return new FileInputFetcher(identifier);
            default: throw new IllegalArgumentException("input source not supported!");
        }
    }
}
