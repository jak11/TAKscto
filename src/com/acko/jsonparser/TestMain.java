package com.acko.jsonparser;

import com.acko.jsonparser.exception.ParseException;
import com.acko.jsonparser.service.Parser;
import com.acko.jsonparser.serviceimpl.JsonParser;

public class TestMain
{
    public static void main(String[] args)
    {
        System.out.println("Starting Parser");
        Parser<String> jsonParser = new JsonParser();
        try {

            String[] input =  generateInput();
            Object[] result;
            for (String in: input) {
                result = jsonParser.parse(in);
                System.out.println("Input : " + in  + " Output ( " + result[0] + " )," + result[1]);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static String[] generateInput(){
        return new String[]{
                "\"1234\"ababbc",
                "1234ababbc",
                "[\"a\",\"b\"]"
        };
    }
}
