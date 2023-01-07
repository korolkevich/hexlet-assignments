package exercise;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
// BEGIN
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


class Validator {
    public static List<String> validate (Address address) throws IllegalAccessException {
        List<String> resultValidation = new ArrayList<>();
        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            Object fieldValue = field.get(address);
            String fieldName = field.getName();

            String isNotNull = isFieldNotNull(field, fieldValue);

            if (!isNotNull.equals("")) {
                resultValidation.add(fieldName);
            }
        }
        return resultValidation;
    }

    public static Map<String, List<String>> advancedValidate (Address address) throws IllegalAccessException {
        Map<String, List<String>> resultValidation = new HashMap<>();
        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field: fields) {
            List<String> resultFieldValidation = new ArrayList<>();
            field.setAccessible(true);
            Object fieldValue = field.get(address);
            String fieldName = field.getName();

            String isNotNull = isFieldNotNull(field, fieldValue);
            String isFieldHaveCorrectLength = isFieldHaveCorrectLength(field, fieldValue);

            if (!isNotNull.equals("")) {
                resultFieldValidation.add(isNotNull);
            }

            if (!isFieldHaveCorrectLength.equals("")) {
                resultFieldValidation.add(isFieldHaveCorrectLength);
            }

            if (resultFieldValidation.size() > 0) {
                resultValidation.put(fieldName, resultFieldValidation);
            }

        }
        return resultValidation;
    }

    private static String isFieldNotNull(Field field, Object fieldValue) {
        NotNull isHaveAnnotation = field.getAnnotation(NotNull.class);
        if (isHaveAnnotation != null) {
            if (fieldValue == null) {
                return "can not be null";
            }
        }
        return "";
    }

    private static String isFieldHaveCorrectLength(Field field, Object fieldValue) {
        MinLength isHaveAnnotation = field.getAnnotation(MinLength.class);
        String fieldValueString = String.valueOf(fieldValue);
        if (isHaveAnnotation != null) {
            int minLength = isHaveAnnotation.minLength();
            if (fieldValueString.length() < minLength) {
                return "length less than " + minLength;
            }
        }
        return "";
    }
}
// END
