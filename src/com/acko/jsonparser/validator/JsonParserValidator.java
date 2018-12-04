package com.acko.jsonparser.validator;

import com.acko.jsonparser.exception.ParseException;

public class JsonParserValidator
{
    public void validate(String input) throws ParseException
    {
        if(input.contains(" ")){
            throw new ParseException("Whitespace not allowed");
        }

        if(input.contains("\\")){
            throw new ParseException("Backspace not allowed");
        }
    }
}
