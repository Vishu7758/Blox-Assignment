package org.vishal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class JSONParser {
    private int index;

    public Object parse(String json) {
        index = 0;
        return parseValue(json);
    }

    private Object parseValue(String json) {
        skipWhitespace(json);
        char c = json.charAt(index);
        if (c == '{') {
            return parseObject(json);
        } else if (c == '[') {
            return parseArray(json);
        } else if (c == '"') {
            return parseString(json);
        } else if (c == 't' || c == 'f') {
            return parseBoolean(json);
        } else if (c == 'n') {
            return parseNull(json);
        } else {
            return parseNumber(json);
        }
    }

    private Map<String, Object> parseObject(String json) {
        Map<String, Object> map = new HashMap<>();
        index++; // Skip '{'
        while (json.charAt(index) != '}') {
            skipWhitespace(json);
            String key = parseString(json);
            skipWhitespace(json);
            index++; // Skip ':'
            Object value = parseValue(json);
            map.put(key, value);
            skipWhitespace(json);
            if (json.charAt(index) == ',') {
                index++;
            }
        }
        index++; // Skip '}'
        return map;
    }

    private List<Object> parseArray(String json) {
        List<Object> list = new ArrayList<>();
        index++; // Skip '['
        while (json.charAt(index) != ']') {
            skipWhitespace(json);
            Object value = parseValue(json);
            list.add(value);
            skipWhitespace(json);
            if (json.charAt(index) == ',') {
                index++;
            }
        }
        index++; // Skip ']'
        return list;
    }

    private String parseString(String json) {
        StringBuilder sb = new StringBuilder();
        index++; // Skip opening quote
        while (json.charAt(index) != '"') {
            sb.append(json.charAt(index));
            index++;
        }
        index++; // Skip closing quote
        return sb.toString();
    }

    private Boolean parseBoolean(String json) {
        if (json.startsWith("true", index)) {
            index += 4;
            return true;
        } else if (json.startsWith("false", index)) {
            index += 5;
            return false;
        }
        throw new IllegalArgumentException("Invalid boolean value");
    }

    private Object parseNull(String json) {
        if (json.startsWith("null", index)) {
            index += 4;
            return null;
        }
        throw new IllegalArgumentException("Invalid null value");
    }

    private Number parseNumber(String json) {
        int start = index;
        while (index < json.length() && "0123456789+-.".indexOf(json.charAt(index)) != -1) {
            index++;
        }
        String numStr = json.substring(start, index);
        if (numStr.contains(".")) {
            return new BigDecimal(numStr);
        } else {
            return new BigInteger(numStr);
        }
    }

    private void skipWhitespace(String json) {
        while (index < json.length() && Character.isWhitespace(json.charAt(index))) {
            index++;
        }
    }

    public static void main(String[] args) {
        //took random json from https://codebeautify.org/json-generator for testing
        String jsonString = "{\"too\": false,\"body\": [true,{\"left\": -1800983025,\"straw\": 1755622516,\"those\": -869325466.3555665,\"chosen\": \"spent\",\"powder\": \"eager\",\"soil\": false},\"office\",1237742388,1524667944,-1431239966.9874425],\"system\": true,\"early\": true,\"see\": -1838320125,\"plain\": \"stove\"}";
        Object parsed = (new JSONParser()).parse(jsonString);
        System.out.println(parsed);
    }
}
