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
    @Column(name = "pk_group_id")
    private Long pkGroupId;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "cover_photo")
    private String coverPhoto;

    @Column(name = "dialing_code")
    private String dialingCode;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "currency")
    private String currency;

    @Column(name = "is_community")
    private Boolean isCommunity;

    @Column(name = "is_active")
    private Boolean isActive;

    @NotNull
    @Column(name = "payment_gateway_1")
    private Boolean paymentGateway1;

    @NotNull
    @Column(name = "payment_gateway_2")
    private Boolean paymentGateway2;

    @NotNull
    @Column(name = "payment_gateway_3")
    private Boolean paymentGateway3;

    @NotNull
    @Column(name = "payment_gateway_4")
    private Boolean paymentGateway4;

    @NotNull
    @Column(name = "payment_gateway_5")
    private Boolean paymentGateway5;

    @NotNull
    @Column(name = "payment_direct_bank_transfer_cash")
    private Boolean paymentDirectBankTransferCash;

    @Column(name = "payment_gateway_1_minimum_registration_fee")
    private Double paymentGateway1MinimumRegistrationFee;

    @Column(name = "payment_direct_bank_transfer_cash_min_registration_fee")
    private Double paymentDirectBankTransferCashMinRegistrationFee;
}
