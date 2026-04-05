package com.works.configs;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<HashMap<String, Object>> errors = parseError(e.getFieldErrors());
        return ResponseEntity.badRequest().body(errors);
    }

    private List<HashMap<String, Object>> parseError(List<FieldError> fieldErrors) {
        List<HashMap<String, Object>> errors = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            HashMap<String, Object> errorMap = new HashMap<>();
            errorMap.put("field", error.getField());
            errorMap.put("message", error.getDefaultMessage());
            errorMap.put("rejectedValue", error.getRejectedValue());
            errors.add(errorMap);
        }
        return errors;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex)
    {
        String paramName = ex.getName();
        Object value = ex.getValue();
        Class<?> requiredType = ex.getRequiredType();

        String message = "";

        if(requiredType != null)
        {
            if(requiredType == Long.class || requiredType == Integer.class
            || requiredType == long.class || requiredType == int.class)     //Hocaya bildir
            {
                message = paramName + " must be numerical value. Value sent: " + value;
            }
            else if(requiredType == Boolean.class)
            {
                message = paramName + " must be true or false. Value sent: " + value;
            }
            else
            {
                message = "The value for the " + paramName + " is invalid. Value sent: " + value;
            }
        }
        else
        {
            message = "Invalid parameter: " + paramName;
        }

        Map<String, Object> error = new HashMap<>();
        error.put("success", false);
        error.put("message", message);
        return ResponseEntity.badRequest().body(error);
    }

    public enum ID_POS
    {
        FIRST,
        LAST
    }

    public static ResponseEntity handleInvalidId(ID_POS idPos, long id)
    {

        String message = "";

        if(idPos.equals(ID_POS.FIRST))
        {
            message = "The small value entered could not be found: " + id;
        }
        else if(idPos.equals(ID_POS.LAST))
        {
            message = "The great value entered could not be found: " + id;
        }

        Map<String, Object> hm = new HashMap<>();
        hm.put("Success", false);
        hm.put("Message", message);
        return ResponseEntity.badRequest().body(hm);

    }

}
