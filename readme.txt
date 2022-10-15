







        ####### INTEGRATION DE SWAGGER 2.9.2 ##############

1-Ajouter les dependances springfox-swagger2 et springfox-swagger-ui
2-Annoter la classe main de @EnableSwagger2
3-Ajouter celle ligne dans application.properties :
    spring.mvc.pathmatch.matching-strategy=ant_path_matcher
4-Autoriser les ressources : {/v2/**, /swagger-ui.html, /swagger-resources/**}

Optionnel : Annoter une classe ressource de @Api("mon message")
            et une methode ressource de @ApiOperation("mon message")

            Se connecter avec http://localhost:8080/swagger-ui.html
