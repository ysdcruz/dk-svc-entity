package com.devkinetics.svc.entity.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

@Slf4j
public class CommonUtil {

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public CommonUtil() {
    }

    // ----------------------------------------------------------------------
    // Create update delete section
    // ----------------------------------------------------------------------

//    public static boolean generatedPDF(List<String> lstHTMLTemplateName, String absoluteFilePath) {
//        log.info("Creating PDF from HTML Template... | {}", absoluteFilePath);
//
//        try {
//            File outputFile = new File(absoluteFilePath);
//
//            if (!outputFile.exists()) {
//                outputFile.createNewFile();
//            }
//
//            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
//            ITextRenderer iTextRenderer = new ITextRenderer();
//
//            // Create page 1
//            iTextRenderer.setDocumentFromString(lstHTMLTemplateName.get(0));
//            iTextRenderer.layout();
//            iTextRenderer.createPDF(fileOutputStream, false);
//
//            // Create the next pages
//            for (int i = 1; i < lstHTMLTemplateName.size(); i++) {
//                iTextRenderer.setDocumentFromString(lstHTMLTemplateName.get(i));
//                iTextRenderer.layout();
//                iTextRenderer.writeNextDocument();
//            }
//
//            iTextRenderer.finishPDF();
//            log.info("Done creating PDF from HTML Template.. | {}", absoluteFilePath);
//            fileOutputStream.close();
//            return true;
//        } catch (Exception ex) {
//            log.info("Error creating the PDF... caught some error: {}", ex.getMessage());
//            return false;
//        }
//    }

    // ----------------------------------------------------------------------
    // Data retrieval section
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Module specific section
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------
    // Generic method section
    // ----------------------------------------------------------------------

    public static String validateObject(Object obj) {

        if (validator.validate(obj).isEmpty()) {
            return null;
        }

        StringBuilder strBuilder = new StringBuilder();
        ConstraintViolation<Object> strConstraintViolation;

        for (ConstraintViolation<Object> objectConstraintViolation : validator.validate(obj)) {
            System.out.println(objectConstraintViolation);
            strConstraintViolation = objectConstraintViolation;
            log.info(strConstraintViolation.getMessage());
            log.info(strConstraintViolation.getPropertyPath().toString());

            strBuilder
                    .append(strConstraintViolation.getPropertyPath())
                    .append(" : ")
                    .append(strConstraintViolation.getMessage())
                    .append(" \n");
        }

        return strBuilder.toString();
    }

    public static String convertPOJOToJsonString(Object objectClass) {
        ObjectMapper objMapper = new ObjectMapper();
        String jsonRequestInString = null;

        try {
            jsonRequestInString = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectClass);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return jsonRequestInString;
    }

    public static String getBaseURL(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        String contextPath = request.getContextPath();
        int serverPort = request.getServerPort();

        StringBuilder strBuilder = new StringBuilder();
        strBuilder
                .append(scheme)
                .append("://")
                .append(serverName);

        if ((serverPort != 80) && (serverPort != 443)) {
            strBuilder
                    .append(":")
                    .append(serverPort);
        }

        strBuilder.append(contextPath);

        if (strBuilder.toString().endsWith("/")) {
            strBuilder.append("/");
        }

        return strBuilder.toString();
    }

    public static String generatedRandomSevenStrings() {
        return String.format("%s", RandomStringUtils.random(7, true, true));
    }

    public static List<String> csvToArrayOfString(String csvString) {
        return new LinkedList<String>(Arrays.asList(csvString.split("\\s*,\\s*")));
//        return Arrays.asList(csvString.split("\\s*,\\s*"));
    }

    public static String convertFirstLetterToUpperCase(String input) {
        if (input == null) {
            return null;
        }

        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static String getValueOfAgeGroup(int ageGroup) {

        String valueOfAgeGroup = null;

        switch (ageGroup) {
            case ConstantUtil.CFG_TICKET_TYPE_AGE_GROUP_KID:
                valueOfAgeGroup = "Kid";
                break;
            case ConstantUtil.CFG_TICKET_TYPE_AGE_GROUP_ADULT:
                valueOfAgeGroup = "Adult";
                break;
            case ConstantUtil.CFG_TICKET_TYPE_AGE_GROUP_SENIOR:
                valueOfAgeGroup = "Senior";
                break;
        }

        return valueOfAgeGroup;
    }

    public static boolean isValidCustomerBookingStatus(Integer status) {
        switch (status) {
            case ConstantUtil.CUSTOMER_BOOKING_STATUS_ADMIN_GENERATED:
            case ConstantUtil.CUSTOMER_BOOKING_STATUS_USED:
            case ConstantUtil.CUSTOMER_BOOKING_STATUS_NOT_USED:
            case ConstantUtil.CUSTOMER_BOOKING_STATUS_UNUSED_BUT_LAPSED:
            case ConstantUtil.CUSTOMER_BOOKING_STATUS_SOLD_TO_OTHERS:
            case ConstantUtil.CUSTOMER_BOOKING_STATUS_REOPENED:
                return true;

            default:
                return false;
        }
    }

    public static boolean sendGetRequestTo(String inputUrl) {
        HttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpResponse httpResponse = httpClient.execute(new HttpGet(inputUrl));
            return httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
        } catch (IOException ex) {
            log.error("Caught some error while doing a request on the URL {} | {}", inputUrl, ex.getMessage());
            return false;
        }
    }

    public static String convertBase64OfByteArrayToString(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }

//    public static byte[] generateQRCode(String data) {
//
//        // data must not be null
//        if (data == null) {
//            return null;
//        }
//
//        try {
//            ByteArrayOutputStream bAOS = new ByteArrayOutputStream();
//            BitMatrix bMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, ConstantUtil.QR_CODE_DEFAULT_WIDTH, ConstantUtil.QR_CODE_DEFAULT_HEIGHT);
//            MatrixToImageWriter.writeToStream(bMatrix, MediaType.IMAGE_PNG.getSubtype(), bAOS, new MatrixToImageConfig());
//            return bAOS.toByteArray();
//        } catch (WriterException | IOException e) {
//            log.error("Error generating qr code | error : {}", e.getMessage());
//            return null;
//        }
//    }

    public static byte[] getImageFromURL(String imageUrl) {

        // TODO: Remove this and do a better approach for trusting the Let's Encrypt's certs
        // Create a new trust manager that trust all certificates
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        // Activate the new trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception ex) {
            log.error("Error retrieving image from URL | error : {}", ex.getMessage());
            return null;
        }

        try {
            URL url = new URL(imageUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(10000); //10 sec timeout
            urlConnection.setReadTimeout(10000);
            urlConnection.connect();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            IOUtils.copy(urlConnection.getInputStream(), byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();
        } catch (Exception ex) {
            log.error("Error retrieving image from URL | error : {}", ex.getMessage());
            return null;
        }
    }

    public static JavaMailSender getJavaMailSender(String smtpServer, int smtpPort, String smtpUsername, String smtpPassword) {
        String smtpUsernameLastCharacter = smtpUsername.substring(smtpUsername.length() - 1);

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smtpServer);
        mailSender.setPort(smtpPort);

        if (smtpUsernameLastCharacter.equals(">")) {
            smtpUsername = StringUtils.substringBetween(smtpUsername, "<", ">");
        }

        mailSender.setUsername(smtpUsername);
        mailSender.setPassword(smtpPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
//        props.put("mail.debug", "true"); // Disable this in prod

        return mailSender;
    }

    public static String encryptText(String text, String secretKey) {
        String encryptedText = null;

        if (text == null || secretKey == null) {
            return null;
        }

        try {
            SecretKeySpec skeyspec = new SecretKeySpec(secretKey.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            encryptedText = new String(encrypted);
        } catch (Exception e) {
            log.error("Error encrypting text | error : {}", e.getMessage());
        }

        return encryptedText;
    }

    public static String decryptEncryptedText(String enctryptedText, String secretKey) {
        String decryptedText = null;

        if (enctryptedText == null || secretKey == null) {
            return null;
        }

        try {
            SecretKeySpec skeyspec = new SecretKeySpec(secretKey.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, skeyspec);
            byte[] decrypted = cipher.doFinal(enctryptedText.getBytes());
            decryptedText = new String(decrypted);
        } catch (Exception e) {
            log.error("Error decrypting encrypted text | error : {}", e.getMessage());
        }

        return decryptedText;
    }

//    public static String objectToQuery() {
//
//    }

//    public static String getPayPalFullSuccessCallbackUrl(String baseUrl, String payPalSuccessCallbackUrl) {
//
//        // baseUrl and payPalSuccessCallbackUrl must not be null
//        if (baseUrl == null || payPalSuccessCallbackUrl == null) {
//            return null;
//        }
//
//        // Check if baseUrl is localhost
//        if (baseUrl.contains("localhost")) {
//            payPalSuccessCallbackUrl = payPalSuccessCallbackUrl.replace("/v1", "");
//        }
//
//        return baseUrl + payPalSuccessCallbackUrl;
//    }
}
