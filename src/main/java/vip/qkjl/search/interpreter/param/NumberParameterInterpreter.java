package vip.qkjl.search.interpreter.param;

public class NumberParameterInterpreter implements BaseParameterInterpreter<String, Number> {
    @Override
    public Number apply(String value) {
        String trim = value.trim();
        if (trim.contains(".")) {
            return Double.parseDouble(trim);
        }
        return Integer.parseInt(trim);
    }
}
