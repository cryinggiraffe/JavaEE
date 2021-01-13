package com.example.elasticsearch_api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {

    private Integer id;
    private String hostname;
    private String ip;

    public User() {
    }

    public User(Integer id, String hostname, String ip) {
        this.id = id;
        this.hostname = hostname;
        this.ip = ip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", hostname='" + hostname + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
