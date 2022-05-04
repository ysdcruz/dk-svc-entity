package com.devkinetics.svc.entity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cfg_app")
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class App implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkAppId")
    private Long pkAppId;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "facebookLink")
    private String facebookLink;

    @Column(name = "twitterLink")
    private String twitterLink;

    @Column(name = "copyright")
    private String copyright;

    @Column(name = "primaryColor")
    private String primaryColor;

    @Column(name = "secondaryColor")
    private String secondaryColor;

    @NotNull
    @Column(name = "appUrl")
    private String appUrl;

    //    @JsonIgnore
    @Column(name = "paypalWebProfile")
    private String paypalWebProfile;

    @Column(name = "paymentSuccessRedirectUrl")
    private String paymentSuccessRedirectUrl;

    @Column(name = "paymentCancelledRedirectUrl")
    private String paymentCancelledRedirectUrl;

    @Column(name = "paymentFailedRedirectUrl")
    private String paymentFailedRedirectUrl;

    @Column(name = "merchantId")
    private String merchantId;

    @Column(name = "publicKey")
    private String publicKey;

    @Column(name = "secretKey")
    private String secretKey;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "domains")
    private String domains;

    @Column(name = "logoUrl")
    private String logoUrl;

    @Column(name = "iconUrl")
    private String iconUrl;

    @Column(name = "contactUsUrl")
    private String contactUsUrl;

    @Column(name = "smtpServer")
    private String smtpServer;

    @Column(name = "smtpPort")
    private Integer smtpPort;

    @Column(name = "smtpUsername")
    private String smtpUsername;

    @Column(name = "smtpPassword")
    private String smtpPassword;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Manila")
    @Column(name = "createdAt")
    private Date createdAt;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Manila")
    @Column(name = "updatedAt")
    private Date updatedAt;
}
