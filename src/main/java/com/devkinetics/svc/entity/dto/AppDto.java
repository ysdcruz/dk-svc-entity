package com.devkinetics.svc.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class AppDto {
    private Long pkAppId;
    private String name;
    private String description;
    private String email;
    private String facebookLink;
    private String twitterLink;
    private String appUrl;
    private String paypalWebProfile;
    private Boolean isActive;
    private List<String> domains;
    private String logoUrl;
    private String iconUrl;
    private String contactUsUrl;
    private String smtpServer;
    private Integer smtpPort;
    private String smtpUsername;
    private String smtpPassword;
}
