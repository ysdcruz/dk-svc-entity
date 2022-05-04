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
    @Column(name = "pk_app_id")
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

    @Column(name = "facebook_link")
    private String facebookLink;

    @Column(name = "twitter_link")
    private String twitterLink;

    @Column(name = "copyright")
    private String copyright;

    @Column(name = "primary_color")
    private String primaryColor;

    @Column(name = "secondary_color")
    private String secondaryColor;

    @NotNull
    @Column(name = "app_url")
    private String appUrl;

    //    @JsonIgnore
    @Column(name = "paypal_web_profile")
    private String paypalWebProfile;

    @Column(name = "payment_success_redirect_url")
    private String paymentSuccessRedirectUrl;

    @Column(name = "payment_cancelled_redirect_url")
    private String paymentCancelledRedirectUrl;

    @Column(name = "payment_failed_redirect_url")
    private String paymentFailedRedirectUrl;

    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "public_key")
    private String publicKey;

    @Column(name = "secret_key")
    private String secretKey;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "domains")
    private String domains;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "contact_us_url")
    private String contactUsUrl;

    @Column(name = "smtp_server")
    private String smtpServer;

    @Column(name = "smtp_port")
    private Integer smtpPort;

    @Column(name = "smtp_username")
    private String smtpUsername;

    @Column(name = "smtp_password")
    private String smtpPassword;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Manila")
    @Column(name = "created_at")
    private Date createdAt;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Manila")
    @Column(name = "updated_at")
    private Date updatedAt;
}
