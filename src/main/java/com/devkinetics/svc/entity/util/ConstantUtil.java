package com.devkinetics.svc.entity.util;

public class ConstantUtil {

    public static final Long DEFAULT_CURRENT_USER_ID = 0L;
    public static final long DEFAULT_AUTH_EXPIRATION = 432_000_000; // 10 days | MS units

    public static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    public static final String DEFAULT_DATE_FORMAT = "EEE, MMM dd yyyy";
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    public static final int CFG_OTHER_CHARGES_CHARGE_TYPE_PER_TICKET = 0;
    public static final int CFG_OTHER_CHARGES_CHARGE_TYPE_PER_TRANSACTION = 1;

    public static final int CFG_TICKET_CLASSIFICATION_TICKET_LAYOUT_DEFAULT = 1;
    public static final int CFG_TICKET_CLASSIFICATION_TICKET_LAYOUT_TRANSPORT = 2;

    public static final int CFG_TICKET_TYPE_AGE_GROUP_ALL = 0;
    public static final int CFG_TICKET_TYPE_AGE_GROUP_KID = 1;
    public static final int CFG_TICKET_TYPE_AGE_GROUP_ADULT = 2;
    public static final int CFG_TICKET_TYPE_AGE_GROUP_SENIOR = 3;

    public static final String CFG_TICKET_TYPE_DAY_MONDAY = "MON";
    public static final String CFG_TICKET_TYPE_DAY_TUESDAY = "TUE";
    public static final String CFG_TICKET_TYPE_DAY_WEDNESDAY = "WED";
    public static final String CFG_TICKET_TYPE_DAY_THURSDAY = "THU";
    public static final String CFG_TICKET_TYPE_DAY_FRIDAY = "FRI";
    public static final String CFG_TICKET_TYPE_DAY_SATURDAY = "SAT";
    public static final String CFG_TICKET_TYPE_DAY_SUNDAY = "SUN";

    public static final int CFG_TICKET_TYPE_PRICE_CLASSIFICATION_FIXED = 1;
    public static final int CFG_TICKET_TYPE_PRICE_CLASSIFICATION_MULTI_PRICE = 2;
    public static final int CFG_TICKET_TYPE_PRICE_CLASSIFICATION_PROVIDED = 3;

    public static final int CFG_TICKET_TYPE_STATUS_ACTIVE = 0;
    public static final int CFG_TICKET_TYPE_STATUS_INACTIVE = 1;
    public static final int CFG_TICKET_TYPE_USABLE_COUNT_DATEBOUND = 0;
    public static final int CFG_TICKET_TYPE_USABLE_COUNT_ONE_TIME_USE = 1;
    public static final int CFG_TICKET_TYPE_USABLE_COUNT_LIFETIME = 999;
    public static final int CFG_TICKET_TYPE_MAX_COUNT_PER_EVENT_UNLIMITED = -1;

    public static final int CUSTOMER_BOOKING_STATUS_ADMIN_GENERATED = -1;
    public static final int CUSTOMER_BOOKING_STATUS_USED = 0;
    public static final int CUSTOMER_BOOKING_STATUS_NOT_USED = 1;
    public static final int CUSTOMER_BOOKING_STATUS_UNUSED_BUT_LAPSED = 2;
    public static final int CUSTOMER_BOOKING_STATUS_SOLD_TO_OTHERS = 3;
    public static final int CUSTOMER_BOOKING_STATUS_REOPENED = 4;

    public static final int EVENTORVENUE_STATUS_ACTIVE = 0;
    public static final int EVENTORVENUE_STATUS_INACTIVE = 1;

    public static final String PAYPAL_PAYMENT_INTENT_SALE = "sale";
    public static final String PAYPAL_PAYMENT_INTENT_AUTHORIZE = "authorize";
    public static final String PAYPAL_PAYMENT_INTENT_ORDER = "order";

    public static final String PAYPAL_PAYMENT_METHOD_PAYPAL = "paypal";
    public static final String PAYPAL_PAYMENT_METHOD_CREDIT_CARD = "credit_card";
    public static final String PAYPAL_PAYMENT_METHOD_BANK = "bank";

    public static final String PAYMENT_STATUS_SUCCESS = "S";
    public static final String PAYMENT_STATUS_FAILURE = "F";
    public static final String PAYMENT_STATUS_CANCELLED = "C";
    public static final String PAYMENT_STATUS_AUTHORIZED = "A";
    public static final String PAYMENT_STATUS_REFUND = "R";
    public static final String PAYMENT_STATUS_PENDING = "P";
    public static final String PAYMENT_STATUS_UNKNOWN = "U";
    public static final String PAYMENT_STATUS_CHARGEBACK = "K";
    public static final String PAYMENT_STATUS_VOID = "V";

    public static final String PAYPAL_STATE_APPROVED = "approved";

    public static final String PAYPAL_TRANSACTION_STATE_COMPLETED = "completed";

    public static final String TABLE_NAME_CFG_APP = "cfg_app";
    public static final String TABLE_NAME_CFG_TICKET_TYPE = "cfg_ticket_type";
    public static final String TABLE_NAME_EVENTORVENUE = "eventorvenue";
    public static final String TABLE_NAME_COMPANY = "cfgAppEntity";
    public static final String TABLE_NAME_MULTIPRICE = "multiprice";

    public static final int TEMP_RESERVED_SLOT_STATUS_PENDING = 0;
    public static final int TEMP_RESERVED_SLOT_STATUS_SUBMITTED = 1;
    public static final int TEMP_RESERVED_SLOT_STATUS_COMPLETED = 3;
    public static final int TEMP_RESERVED_SLOT_STATUS_EXPIRED = 4;

    public static final int TICKET_GENERATION_STATUS_PENDING = 0;
    public static final int TICKET_GENERATION_STATUS_EMAIL_SENT = 1;
    public static final int TICKET_GENERATION_STATUS_DRAGONPAY_EMAIL_SENT = 2;

    public static final String UNIX_IMAGE_PATH = "/var/www/filemgr/images";
    public static final String UNIX_FILE_PATH = "/var/www/filemgr/docs";
    public static final String UNIX_TICKET_PATH = "/var/www/filemgr/tickets";
    public static final String WINDOWS_IMAGE_PATH = "C:\\filemgr\\images";
    public static final String WINDOWS_FILE_PATH = "C:\\filemgr\\docs";
    public static final String WINDOWS_TICKET_PATH = "C:\\filemgr\\tickets";

    /**
     * Enums
     */

    public enum TICKET_TYPE {
        ALL,
        RESERVATION,
        NON_RESERVATION
    }

}
