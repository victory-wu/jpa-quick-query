package vip.qkjl.search.interpreter.param;

import vip.qkjl.constant.SearchConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParameterInterpreter implements BaseParameterInterpreter<String, Date> {

    @Override
    public Date apply(String value) throws ParseException {
        for (String pattern : SearchConstant.datePatterns) {
            if (value.length() == pattern.length()){
                return new SimpleDateFormat(pattern).parse(value);
            }
        }
        throw new ParseException("时间格式不支持！", 0);
    }
}
