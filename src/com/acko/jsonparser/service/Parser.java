package com.acko.jsonparser.service;

import com.acko.jsonparser.exception.ParseException;

public interface Parser<T>
{
    Object[] parse(T t) throws IllegalArgumentException, ParseException;
}
