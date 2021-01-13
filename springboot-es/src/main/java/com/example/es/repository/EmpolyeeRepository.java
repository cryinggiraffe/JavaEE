package com.example.es.repository;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.stereotype.Repository;

@Repository
public class EmpolyeeRepository {
    public JestClient getJestCline(){
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("192.168.195.130:9200")
                .multiThreaded(true)
                .build());
        return factory.getObject();
    }
}
