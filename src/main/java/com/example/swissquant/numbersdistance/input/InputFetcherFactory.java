package com.example.swissquant.numbersdistance.input;

import com.example.swissquant.numbersdistance.input.imp.FileInputFetcher;
import com.example.swissquant.numbersdistance.model.InputSource;

/**
 * InputFetcher Factory to return InputFetcher object based on input source enum.
 * Currently we only support FileInputFetcher.
 */
public class InputFetcherFactory {
    /**
     * Get InputFetcher object based on strategy passed.
     * @param inputSource
     * @param identifier
     * @return InputFetcher object
     */
    public static InputFetcher getInputFetcher(InputSource inputSource, String identifier) {
        switch (inputSource){
            case FILE: return new FileInputFetcher(identifier);
            // TODO: Implement more input source fetcher.
//            case DATABASE: return new FileInputFetcher(identifier);
//            case CLOUD: return new FileInputFetcher(identifier);
            default: throw new IllegalArgumentException("input source not supported!");
        }
    }
}
