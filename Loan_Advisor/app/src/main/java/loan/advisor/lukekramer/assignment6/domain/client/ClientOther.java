package com.example.lukekramer.assignment6.domain.client;

/**
 * Created by student on 2016/03/31.
 */
public class ClientOther extends ClientType {

    @Override
    public String handleRequest(long request) {
        if (request < 10000 & request >= 1000) {
            return "minimumwage";
        } else {
            if (nextType != null) {
                return nextType.handleRequest(request);
            }
            return "Client not placed";
        }
    }
}
