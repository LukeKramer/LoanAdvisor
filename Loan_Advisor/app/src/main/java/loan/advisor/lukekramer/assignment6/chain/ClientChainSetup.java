package com.example.lukekramer.assignment6.chain;

import com.example.lukekramer.assignment6.domain.client.ClientGeneral;
import com.example.lukekramer.assignment6.domain.client.ClientOther;
import com.example.lukekramer.assignment6.domain.client.ClientPremium;
import com.example.lukekramer.assignment6.domain.client.ClientType;

/**
 * Created by student on 2016/03/31.
 */
public class ClientChainSetup {

    public static String getClientType(long value){
        ClientType chain = setUpChain();
        return chain.handleRequest(value);
    }

    public static ClientType setUpChain(){
        ClientType a = new ClientPremium();
        ClientType b = new ClientGeneral();
        ClientType c = new ClientOther();
        a.setNextType(b);
        b.setNextType(c);
        return a;
    }
}
