package com.acko.jsonparser.serviceimpl;

import com.acko.jsonparser.service.Parser;
import com.acko.jsonparser.exception.ParseException;
import com.acko.jsonparser.utils.JsonParserCore;

import static com.acko.jsonparser.utils.Tokens.*;

/**
 * Parse the json string
 */
public class JsonParser implements Parser<String>
{

    @Override
    public Object[] parse(String input) throws ParseException
    {
        if(input == null || input.isEmpty()){
            throw new IllegalArgumentException("Invalid input");
        }

        JsonParserCore jsonParserCore = new JsonParserCore();
        return jsonParserCore.parse(input);
    }
}
