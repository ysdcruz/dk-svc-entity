DROP TABLE IF EXISTS cfg_app;

CREATE TABLE cfg_app (
  pkAppId BIGINT NOT NULL AUTO_INCREMENT,
   name VARCHAR(255),
   description VARCHAR(255),
   email VARCHAR(255),
   facebookLink VARCHAR(255),
   twitterLink VARCHAR(255),
   copyright VARCHAR(255),
   primaryColor VARCHAR(255),
   secondaryColor VARCHAR(255),
   appUrl VARCHAR(255),
   paypalWebProfile VARCHAR(255),
   paymentSuccessRedirectUrl VARCHAR(255),
   paymentCancelledRedirectUrl VARCHAR(255),
   paymentFailedRedirectUrl VARCHAR(255),
   merchantId VARCHAR(255),
   publicKey VARCHAR(255),
   secretKey VARCHAR(255),
   isActive BOOLEAN,
   domains VARCHAR(255),
   logoUrl VARCHAR(255),
   iconUrl VARCHAR(255),
   contactUsUrl VARCHAR(255),
   smtpServer VARCHAR(255),
   smtpPort INT,
   smtpUsername VARCHAR(255),
   smtpPassword VARCHAR(255),
   createdAt TIMESTAMP NOT NULL,
   updatedAt TIMESTAMP NOT NULL,
   CONSTRAINT pk_cfg_app PRIMARY KEY (pkAppId)
);

DROP TABLE IF EXISTS cfg_group;

CREATE TABLE cfg_group (
  pkGroupId BIGINT NOT NULL AUTO_INCREMENT,
   name VARCHAR(255),
   description VARCHAR(255),
   image VARCHAR(255),
   coverPhoto VARCHAR(255),
   dialingCode VARCHAR(255),
   contactNumber VARCHAR(255),
   latitude DOUBLE,
   longitude DOUBLE,
   currency VARCHAR(255),
   isCommunity BOOLEAN,
   isActive BOOLEAN,
   paymentGateway1 BOOLEAN,
   paymentGateway2 BOOLEAN,
   paymentGateway3 BOOLEAN,
   paymentGateway4 BOOLEAN,
   paymentGateway5 BOOLEAN,
   paymentDirectBankTransferCash BOOLEAN,
   paymentGateway1MinimumRegistrationFee DOUBLE,
   paymentDirectBankTransferCashMinRegistrationFee DOUBLE,
   CONSTRAINT pk_cfg_group PRIMARY KEY (pkGroupId)
);