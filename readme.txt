        ########### INTEGRATION SECURITE SUR JWT ############

-Ajouter les dependances security-boot-stater, jjwt et jaxb-api
-Creer les classe de domaine, repository de Utilisateur et Role.
    Utilisateur contient de Collection de Role
-Creer une classe de service AccountService qui definit les fonctionnalites
    CRUD de Utilisateur et Role.
    En plus elle implemente UserDetailsService en redefinissant
        UserDetails loadUserByUsername(String username){
            //Retourne un User avec son username, son password et ses roles
        }
-Creer une classe @EnableWebSecurity SecurityConfig qui definie deux beans :
    SecurityFilterChain securityFilterChain(HttpSecurity http){
        Filtre les requetes suivant les paths, les roles ...
        Desactiver le Csrf
        Modifie la politique de creation de session en STATELESS
        Puis retourne une instance dans le context

    }
    DaoAuthenticationProvider authenticationProvider(){
        //Construire et retourner une instance de DaoAut... dans le context
    }
-Creer une classe @Configuration qui definie(Ou definir le bean dans la classe principale) :
     PasswordEncoder passwordEncoder(){
        Retourne une instance de ByCryt... dans le contexte
     }

-Creer une classe @Service qui definie des methodes de creation, extraction,
    validation... de token base sur le JJWT

-

-Creer deux classe:
    AuthRequest avec les attributs username et password
    AuthResponse avec l'attribut jwt

-Creer une classe de filtre @Component qui etend OncePerRequestFilter
    Elle redefinit l'unique methode
    Elle recupere le token dans le champ Authorization depuis header de la requete
    Recupere le username dans le token (s'il n'est pas null)
    Cherche le User a partir du username et verifie que son token est valide
    Authentifie le User et verifie s'il a role d'executer la requete
    Puis on passe au filtre suivant

-Ajouter un  addFilterBefore(objet du filtre Creer, UsernamePasswordAuthenticationFilter.class)






        ####### INTEGRATION DE SWAGGER 2.9.2 ##############

-Ajouter les dependances springfox-swagger2 et springfox-swagger-ui
-Annoter la classe main de @EnableSwagger2
-Ajouter celle ligne dans application.properties :
    spring.mvc.pathmatch.matching-strategy=ant_path_matcher
-Autoriser les ressources : {/v2/**, /swagger-ui.html, /swagger-resources/**}

-Optionnel : Annoter une classe ressource de @Api("mon message")
            et une methode ressource de @ApiOperation("mon message")

-Se connecter avec http://localhost:8080/swagger-ui.html
