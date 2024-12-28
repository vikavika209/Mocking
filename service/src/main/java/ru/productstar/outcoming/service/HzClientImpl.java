package ru.productstar.outcoming.service;

import org.springframework.stereotype.Component;

@Component
public class HzClientImpl implements HzClient{
    @Override
    public String getDataFromService(String parameter) {
        return "In-memory Data for " + parameter;
    }
}
