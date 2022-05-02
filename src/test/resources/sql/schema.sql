--------------- DROP TABLES
DROP TABLE IF EXISTS "cfg_app" CASCADE;
DROP TABLE IF EXISTS "cfg_entity" CASCADE;
DROP TABLE IF EXISTS "company" CASCADE;

--------------- CREATE TABLES AND FIELDS
CREATE TABLE cfg_app(
pk_cfg_app_id BIGINT auto_increment PRIMARY KEY,
  name character varying NOT NULL,
  description character varying,
  image character varying,
  email_address character varying NOT NULL,
  facebook_link character varying,
  twitter_link character varying,
  copyright character varying,
  primary_color character varying,
  secondary_color character varying,
  app_url character varying NOT NULL,
  paypal_web_profile character varying,
  payment_success_redirect_url character varying,
  payment_cancelled_redirect_url character varying,
  payment_failed_redirect_url character varying,
  merchant_id character varying NOT NULL,
  public_key character varying NOT NULL,
  secret_key character varying NOT NULL,
  isactive BOOLEAN DEFAULT FALSE,
  creation_date TIMESTAMP WITH time zone NOT NULL,
  lastupdate_date TIMESTAMP WITH time zone NOT NULL,
  domains character VARYING,
  icon_url character varying, -- favicon
  company_name character varying,
  tin character varying, -- Tax Identification Number
  address character varying,
  contact_us_url character varying,
  smtp_server character varying,
  smtp_port integer,
  smtp_username character varying,
  smtp_password character varying
) AS
  (SELECT *
  FROM CSVREAD ('src/test/resources/data/cfg_app.csv'));

CREATE TABLE cfg_entity(
  pk_cfg_entity_id BIGINT auto_increment PRIMARY KEY,
  name character varying NOT NULL,
  description character varying,
  image character varying, -- flag, etc.
  cover_photo character varying,
  dialing_code character varying,
  contact_details_form integer, -- 1 - PH, US, etc. | 2 - SG
  latitude DOUBLE precision NOT NULL, -- center_lat
  longitude DOUBLE precision NOT NULL, -- center_lng
  currency character varying,
  iscommunity BOOLEAN NOT NULL,
  isactive BOOLEAN NOT NULL,
  payment_gateway_1 BOOLEAN NOT NULL DEFAULT FALSE, -- Dragonpay
  payment_gateway_2 BOOLEAN NOT NULL DEFAULT FALSE,
  payment_gateway_3 BOOLEAN NOT NULL DEFAULT FALSE,
  payment_gateway_4 BOOLEAN NOT NULL DEFAULT FALSE,
  payment_gateway_5 BOOLEAN NOT NULL DEFAULT FALSE,
  payment_direct_bank_transfer_cash BOOLEAN NOT NULL DEFAULT FALSE,
  payment_gateway_1_minimum_registration_fee DOUBLE precision,
  payment_direct_bank_transfer_cash_min_registration_fee DOUBLE precision
) AS
  (SELECT *
  FROM CSVREAD ('src/test/resources/data/cfg_entity.csv'));


CREATE TABLE company (
  pk_company_id BIGINT auto_increment PRIMARY KEY,
  fk_appuser_id BIGINT NOT NULL,
  company_name character varying(50) NOT NULL,
  contact_number character varying(150),
  email character varying(50)NOT NULL,
  dti_sec_no character varying(50),
  creation_date TIMESTAMP WITH time zone NOT NULL,
  lastupdate_date TIMESTAMP WITH time zone NOT NULL,
  logo character varying,
  tin character varying,
  address character varying
) AS
(
SELECT
  pk_company_id,
  fk_appuser_id,
  company_name,
  contact_number,
  email,
  dti_sec_no,
  creation_date,
  lastupdate_date,
  logo,
  tin,
  address
FROM CSVREAD ('src/test/resources/data/company.csv'));

COMMIT;--------------- POPULATE TABLES
--INSERT INTO cfg_app
--  SELECT *
--  FROM CSVREAD('src/test/resources/data/cfg_app.csv');
--INSERT INTO cfg_entity
--  SELECT *
--  FROM CSVREAD('src/test/resources/data/cfg_entity.csv');
--INSERT INTO company
--  SELECT *
--  FROM CSVREAD('src/test/resources/data/company.csv');
--
--COMMIT;

