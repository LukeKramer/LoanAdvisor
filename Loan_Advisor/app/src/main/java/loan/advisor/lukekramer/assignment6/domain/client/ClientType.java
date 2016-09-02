package com.example.lukekramer.assignment6.domain.client;

/**
 * Created by student on 2016/03/31.
 */
public abstract class ClientType {

    ClientType nextType;

    public void setNextType(ClientType nextType) {
        this.nextType = nextType;
    }
    public abstract String handleRequest(long request);


}
