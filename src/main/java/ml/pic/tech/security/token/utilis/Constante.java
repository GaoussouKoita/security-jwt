package ml.pic.tech.security.token.utilis;

public interface Constante {

    public static final String ADMIN_ROLE = "ADMINISTRATEUR";
    public static final String USER_ROLE = "UTILISATEUR";
    public static final String INVITE_ROLE = "INVITE";


    public static final String WHITE_LIST[] =
            {
                    "/v2/**", "/swagger-ui.html",
                    "/swagger-resources/**", "/webjars**/**",
                    Endpoint.UTILISATEUR_ENDPOINT + Endpoint.AUTHENTIFICATION_ENDPOINT,
                    Endpoint.UTILISATEUR_ENDPOINT + Endpoint.PASSWORD_ENDPOINT
            };

    public static final String WHITE_LIST_UTILISATEUR_ROLE[] = {
            Endpoint.ARTICLE_ENDPOINT,
            Endpoint.PRODUIT_ENDPOINT,
            Endpoint.CATEGORIE_ENDPOINT
    };

    public static final String WHITE_LIST_ADMINISTRATEUR_ROLE[] = {
            Endpoint.UTILISATEUR_ENDPOINT
    };
}
