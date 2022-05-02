package com.devkinetics.svc.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cfg_group")
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Group implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkGroupId")
    private Long pkGroupId;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "coverPhoto")
    private String coverPhoto;

    @Column(name = "dialingCode")
    private String dialingCode;

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "currency")
    private String currency;

    @Column(name = "isCommunity")
    private Boolean isCommunity;

    @Column(name = "isActive")
    private Boolean isActive;

    @NotNull
    @Column(name = "paymentGateway1")
    private Boolean paymentGateway1;

    @NotNull
    @Column(name = "paymentGateway2")
    private Boolean paymentGateway2;

    @NotNull
    @Column(name = "paymentGateway3")
    private Boolean paymentGateway3;

    @NotNull
    @Column(name = "paymentGateway4")
    private Boolean paymentGateway4;

    @NotNull
    @Column(name = "paymentGateway5")
    private Boolean paymentGateway5;

    @NotNull
    @Column(name = "paymentDirectBankTransferCash")
    private Boolean paymentDirectBankTransferCash;

    @Column(name = "paymentGateway1MinimumRegistrationFee")
    private Double paymentGateway1MinimumRegistrationFee;

    @Column(name = "paymentDirectBankTransferCashMinRegistrationFee")
    private Double paymentDirectBankTransferCashMinRegistrationFee;
}
