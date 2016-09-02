package com.example.lukekramer.assignment6.domain.client;

/**
 * Created by student on 2016/03/31.
 */
public class ClientPremium extends ClientType {

    @Override
    public String handleRequest(long request) {
        if (request >= 50000) {
            return "premiumwage";
        } else {
            if (nextType != null) {
                return nextType.handleRequest(request);
            }
            return "Client not placed";
        }


    }
}
