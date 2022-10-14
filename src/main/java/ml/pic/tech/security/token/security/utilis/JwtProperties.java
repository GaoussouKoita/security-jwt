package ml.pic.tech.security.token.security.utilis;

public interface JwtProperties {

    public static final String TOKEN_PREFIX="Bearer ";
    public static final String HEADER_STRING="Authorization";
    public static final int EXPIRATE_TIME =1000 * 60 * 60 * 10;
    public static final String  SECRET_KEY = "secret";
}
