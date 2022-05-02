package com.devkinetics.svc.entity.util;

import com.devkinetics.svc.entity.constant.OperationEnum;
import graphql.GraphQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class CodeManagerUtil {

    public static <T> void throwException(OperationEnum operation, Class<T> returnCodeResponse) throws ResponseStatusException, GraphQLException {
        HttpStatus statusCode = null;
        String message = null;

//        try {
//            returnCode = returnCodeResponse.getField("returnCode");
//            statusCode = CodeManagerUtil.getHttpStatusCode(returnCode);
//            message = returnCodeResponse.getField("message");
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }

        switch (operation) {
            case GraphQL:
                throw new GraphQLException(message);

            default:
                throw new ResponseStatusException(statusCode, message);
        }
    }

    public static HttpStatus getHttpStatusCode(Integer returnCode) {

        if (returnCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        if (returnCode == CodeUtil.INVALID_PARAMETER) {
            return HttpStatus.BAD_REQUEST;
        }

        if (returnCode == CodeUtil.UNAUTHORIZED_ACCESS) {
            return HttpStatus.FORBIDDEN;
        }

        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        String returnCodeString = returnCode.toString();
        int statusID = Integer.parseInt(returnCodeString.substring(0, 1));

        // Check if statusID is success
        if (statusID == CodeUtil.RETURN_CODE_STATUS_ID_SUCCESS) {
            statusCode = HttpStatus.OK;
        } else if (statusID == CodeUtil.RETURN_CODE_STATUS_ID_ERROR) {
            statusCode = HttpStatus.EXPECTATION_FAILED;
        }

        return statusCode;
    }

}
