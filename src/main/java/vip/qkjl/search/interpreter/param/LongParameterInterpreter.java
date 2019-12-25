package vip.qkjl.search.interpreter.param;

public class LongParameterInterpreter implements BaseParameterInterpreter<String, Long> {
    @Override
    public Long apply(String value) {
        String trim = value.trim();
        return Long.parseLong(trim);
    }
}
