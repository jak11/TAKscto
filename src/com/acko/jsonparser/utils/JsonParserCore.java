package com.acko.jsonparser.utils;

import java.lang.reflect.Array;
import java.util.Arrays;

import com.acko.jsonparser.exception.ParseException;
import com.acko.jsonparser.validator.JsonParserValidator;

import static com.acko.jsonparser.utils.Tokens.DICT_BEGIN;
import static com.acko.jsonparser.utils.Tokens.LIST_BEGIN;
import static com.acko.jsonparser.utils.Tokens.STRING_BEGIN;

public class JsonParserCore
{
    private static JsonParserValidator jsonParserValidator
                    = new JsonParserValidator();

    public String[] parse(String input) throws ParseException
    {
        String[] result = new String[2];

        input = input.trim();
        char startChar = input.charAt(0);

        switch (startChar) {
            case STRING_BEGIN:
                result = parseString(input);
                break;
            case DICT_BEGIN:
                parseString(input);
                break;
            case LIST_BEGIN:
                parseList(input);
                break;
            default:
                if(Character.isDigit(startChar)){
                    result = parseNumber(input);
                } else {
                    throw new ParseException("Unparseble string");
                }
        }
        return result;
    }

    public String[] parseString(String s) throws ParseException
    {
        jsonParserValidator.validate(s);
        String[] result = new String[2];
        StringBuilder sb = new StringBuilder(s.length());
        int index = 1;

        while (s.charAt(index) != '"') {
            sb.append(s.charAt(index));
            index++;
        }
        result[0] = sb.toString();
        result[1] = s.substring(index + 1, s.length() );

       return result;
    }

    public Object[] parseList(String s) throws ParseException
    {
        int index = 0;
        StringBuilder sb = new StringBuilder(s.length());

        for (int i = 0; i < s.length(); i++) {
            if(i == s.length() -1 && s.charAt(i) != Tokens.LIST_END){
                throw new ParseException("Unparsable string");
            }
            sb.append(s.charAt(i));
            index++;
        }

        String[] result = new String[2];
        String unparsedString = s.substring(index -1 , s.length() -1);
        result[1] = unparsedString;

        //StringBuilder sb = new StringBuilder();
        //sb.append(Tokens.LIST_BEGIN);
        String interMdeiateRes = sb.toString().substring(1, sb.length() - 1);
        String[] listItem = interMdeiateRes.split(String.valueOf(Tokens.LIST_DELIMITER));
        String[] parsedResult = new String[listItem.length];

        String[] parseOutput;
        index = 0;
        for (String item: listItem ) {
            parseOutput = parse(item);
            if(parseOutput[1] != null && parseOutput[1] != ""){
                throw new ParseException("Unparsable string");
            }
            parsedResult[index++] = parseOutput[0];
        }

        result[0] = String.join(String.valueOf(Tokens.LIST_DELIMITER), parsedResult);
        return result;

    }
    public String[] parseNumber(String s) throws ParseException
    {
        jsonParserValidator.validate(s);
        String[] result = new String[2];
        StringBuilder sb = new StringBuilder(s.length());
        int index = 0;
        while (Character.isDigit(s.charAt(index))) {
            sb.append(s.charAt(index));
            index++;
        }
        result[0] = sb.toString();
        result[1] = s.substring(index, s.length());
        return result;
    }
    public static String parseDict(String s){
        return null;
    }
}
