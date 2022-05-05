DROP TABLE IF EXISTS cfg_app CASCADE;
DROP TABLE IF EXISTS cfg_group CASCADE;

CREATE TABLE cfg_app (
  pk_app_id BIGINT NOT NULL AUTO_INCREMENT,
   name VARCHAR(255),
   description character varying,
   email VARCHAR(255),
   facebook_link VARCHAR(255),
   twitter_link VARCHAR(255),
   copyright VARCHAR(255),
   primary_color VARCHAR(255),
   secondary_color VARCHAR(255),
   app_url VARCHAR(255),
   paypal_web_profile VARCHAR(255),
   payment_success_redirect_url VARCHAR(255),
   payment_cancelled_redirect_url VARCHAR(255),
   payment_failed_redirect_url VARCHAR(255),
   merchant_id VARCHAR(255),
   public_key VARCHAR(255),
   secret_key VARCHAR(255),
   is_active BOOLEAN DEFAULT FALSE,
   domains VARCHAR(255),
   logo_url VARCHAR(255),
   icon_url VARCHAR(255),
   contact_us_url VARCHAR(255),
   smtp_server VARCHAR(255),
   smtp_port INT,
   smtp_username VARCHAR(255),
   smtp_password VARCHAR(255),
   created_at TIMESTAMP WITH time zone NOT NULL,
   updated_at TIMESTAMP WITH time zone NOT NULL,
   CONSTRAINT pk_cfg_app PRIMARY KEY (pk_app_id)
) AS
  (SELECT *
  FROM CSVREAD('classpath:data/cfg_app.csv', 'pk_app_id, name,description,email,facebook_link,twitter_link,copyright,primary_color,secondary_color,app_url,paypal_web_profile,payment_success_redirect_url,payment_cancelled_redirect_url,payment_failed_redirect_url,merchant_id,public_key,secret_key,is_active,domains,logo_url,icon_url,contact_us_url,smtp_server,smtp_port,smtp_username,smtp_password,created_at,updated_at'));

CREATE TABLE cfg_group(
  pk_group_id BIGINT NOT NULL AUTO_INCREMENT,
   name VARCHAR(255),
   description VARCHAR(255),
   image VARCHAR(255),
   cover_photo VARCHAR(255),
   dialing_code VARCHAR(255),
   contact_details_form INT,
   latitude DOUBLE,
   longitude DOUBLE,
   currency VARCHAR(255),
   is_community BOOLEAN NOT NULL DEFAULT FALSE,
   is_active BOOLEAN NOT NULL DEFAULT FALSE,
   payment_gateway_1 BOOLEAN NOT NULL DEFAULT FALSE,
   payment_gateway_2 BOOLEAN NOT NULL DEFAULT FALSE,
   payment_gateway_3 BOOLEAN NOT NULL DEFAULT FALSE,
   payment_gateway_4 BOOLEAN NOT NULL DEFAULT FALSE,
   payment_gateway_5 BOOLEAN NOT NULL DEFAULT FALSE,
   payment_direct_bank_transfer_cash BOOLEAN,
   payment_gateway_1_minimum_registration_fee DOUBLE,
   payment_direct_bank_transfer_cash_min_registration_fee DOUBLE,
   CONSTRAINT pk_cfg_group PRIMARY KEY (pk_group_id)
) AS
  (SELECT *
  FROM CSVREAD('classpath:data/cfg_group.csv', 'pk_group_id,name,description,image,cover_photo,dialing_code,contact_details_form,latitude,longitude,currency,is_community,is_active,payment_gateway_1,payment_gateway_2,payment_gateway_3,payment_gateway_4,payment_gateway_5,payment_direct_bank_transfer_cash,payment_gateway_1_minimum_registration_fee,payment_direct_bank_transfer_cash_min_registration_fee'));

COMMIT;--------------- POPULATE TABLES
