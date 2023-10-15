

# API - SPRING BOOT - EXAMEN

**Pourquoi spring boot:**

Spring Boot facilite la configuration et le démarrage de projet en générant un squelette prêt à l'emploi avec les dépendances et la configuration souhaitées.

# DEMARRER UN NOUVEAU PROJET

Initialiser le projet avec le générateur https://start.spring.io/

* Project : spécifier le nom du projet et le nom du package de base.

* Language : choisir le langage de programmation parmi Java, Kotlin ou Groovy.

* Spring Boot Version : sélectionner la version spécifique de Spring Boot que vous souhaitez utiliser.

* Project Metadata : ajouter des informations supplémentaires sur le projet, telles que la description, le nom du groupe, l'artefact, etc.

* Packaging : choisir le type de packaging pour votre projet, tel que Jar ou War.

* Java : spécifier la version de Java que vous souhaitez utiliser (par exemple, Java 11 ou Java 17).

* Dependencies : C'est l'une des options les plus importantes, car cela permet de sélectionner les dépendances spécifiques que vous souhaitez inclure dans votre projet Spring Boot. Par exemple, vous pouvez choisir les dépendances pour Spring Web, Spring Data JPA, Spring Security, Thymeleaf, etc.

* Packaging Options : spécifier des options supplémentaires de packaging pour votre projet, telles que le nom du fichier jar, la version du projet, les propriétés de manifeste, etc.

* Spring Boot DevTools : offre des fonctionnalités de rechargement automatique pour le développement.

* Spring Boot Actuator : fournit des endpoints pour la surveillance et la gestion de l'application.



# LES DEPENDANCES

- Spring Web Web : Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
- Lombok Developer Tools : C'est une bibliothèque Java qui aide à réduire le code et éviter les répétitions comme les getters, setters, etc.
- Spring Data JPA SQL : Utiliser Spring Data et Hibernate pour persister les données dans les magasins SQL avec l'API Java Persistence.
- Spring Boot DevTools Developer Tools : Fournit des redémarrages rapides des applications, LiveReload et des configurations pour une expérience de développement améliorée.
- OAuth2 Client Security : Intégration de Spring Boot pour les fonctionnalités du client Spring Security OAuth2/OpenID Connect. (cas d'un "login with Google" par exemple)
- Spring Security Security : Cadre d'authentification et de contrôle d'accès hautement personnalisable pour les applications Spring.
- MySQL Driver SQL : Pilote JDBC pour MySQL.



# LA STRUCTURE

![img.png](assets.readme/img.png)



# STOCKAGE DES ASSETS
Dans un projet Spring Boot, vous pouvez stocker les fichiers statiques, y compris les images, dans différents endroits en fonction de vos besoins spécifiques.

1. Création d'un dossier `public` à la racine du projet pour les fichiers d'upload , celui-ci sera accessible sans restriction `http://localhost:8080/upload/`;.

![dossier publique](assets.readme/public_assets.png)

2. Création d'un serveur de fichier accessible via une méthode GET. Le dossier `upload` est créé dans `src/main/resources`.

![dossier protégé](assets.readme/private_assets.png)

Controller
````java

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/v1/file/upload")
public class ImageController {

   // Via l'annotation @RequiredArgsConstructor Lombok va générer un constructeur avec un paramètre pour chaque constante (final)
   private final ImageService imageService;

    /* Donc on n'a pas besoin d'injecter le service de cette autre manière
    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
     */

   // TELECHARGEMENT DE FICHIER: Cette méthode renvoi vers le client le fichier demandé.
   // Est demandé en premiere paramètre le nom du sous-dossier d'upload (rubrique) et en deuxième paramètre le nom du fichier recherché.
   // le serveur retourne la réponse avec les données de l'image et les en-têtes
   @GetMapping("/{pathName}/{imageName}")
   public ResponseEntity<byte[]> getImage(@PathVariable String pathName, @PathVariable String imageName) throws IOException {
      return imageService.getImage(pathName, imageName);
   }
}
````

Service
````java
@Service
public class ImageService {

   public ResponseEntity<byte[]> getImage(String pathName, String imageName) throws IOException {
      // Construire le chemin d'accès complet vers l'image demandée
      String fullImagePath = "upload/" + pathName + "/" + imageName;

      // Obtenir le chemin d'accès complet du fichier image depuis le répertoire src/main/resources
      ClassPathResource classPathResource = new ClassPathResource(fullImagePath);
      Path imageFilePath = classPathResource.getFile().toPath();

      // Vérifier si le fichier existe
      if (Files.exists(imageFilePath)) {
         // Lire le contenu du fichier image
         byte[] imageBytes = Files.readAllBytes(imageFilePath);

         // Définir le type de contenu (Content-Type) de la réponse
         String contentType = determineContentType(imageFilePath);

         // Construire les en-têtes de la réponse avec le type de contenu
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.valueOf(contentType));

         // Retourner la réponse avec les données de l'image et les en-têtes
         return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
      } else {
         // Si le fichier image n'existe pas, retourner une réponse 404 (Non trouvé)
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
   }

   // Méthode pour déterminer le type de contenu (Content-Type) de l'image
   private String determineContentType(Path filePath) throws IOException {
      String contentType;
      try {
         contentType = Files.probeContentType(filePath);
      } catch (IOException e) {
         // En cas d'erreur lors de la détermination du type de contenu, on utilise un type par défaut
         contentType = "application/octet-stream";
      }
      return contentType;
   }
}

````







# SECURITE

https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html

Gérer les autorisations sur des routes (endpoints) en utilisant Spring Security. Spring Security est un module de sécurité puissant qui permet de définir des règles d'accès et de contrôler l'accès aux ressources de votre application.

Dans le contexte de Spring Security (la gestion de la sécurité dans les applications Spring), lorsque vous vous connectez avec succès en tant qu'utilisateur authentifié, vous obtenez un "jeton" (ou "token") d'accès qui vous permet d'accéder à certaines ressources protégées ou d'exécuter certaines actions spécifiques. Dans ce cas, vous pouvez dire que l'accès à ces ressources ou actions a été "granted" (accordé) à l'utilisateur.

![](.\assets.readme\authorizationfilter.png)



### Restreindre l'accès à certaines routes en fonction des ROLES

```java
.authorizeHttpRequests(a -> {
                // Sécurise la route pour les utilisateurs avec le rôle ADMIN
                a.requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN");
                // Sécurise la route pour les utilisateurs avec le rôle USER
                a.requestMatchers("/api/v1/user/**").hasAuthority("USER");
                // Autorise toutes les autres requêtes sans nécessiter d'authentification.
                a.anyRequest().permitAll();
            });
```



Exemple de configuration

````
public class
SecurityConfiguration {

    private  final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        
            // Indique que ce HttpSecurity s’applique uniquement aux URL commençant /api/.
            .securityMatcher("/api/**")
            
            // Active la configuration de CORS pour gérer les requêtes depuis des origines différentes.
            .cors()
            
            .and()
            
            // Désactive la protection CSRF qui est généralement désactivée pour les API.
            .csrf().disable()
            
            // Définit les règles d'autorisation pour les requêtes
            // Configure la gestion des sessions.
            .sessionManagement()
            //  Définit la politique de création de sessions comme étant sans état (stateless), ce qui est courant pour les API REST utilisant des tokens d'authentification.
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            
            .and()
            
            // Configure le fournisseur d'authentification que vous avez défini ailleurs dans votre code.
            .authenticationProvider(authenticationProvider)
            
            // Ajoute un filtre personnalisé (jwtAuthFilter) avant le filtre UsernamePasswordAuthenticationFilter, qui gère l'authentification basée sur les noms d'utilisateur et les mots de passe.
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            
            .authorizeHttpRequests(a -> {
                // Sécurise la route pour les utilisateurs authentifiés
                //.requestMatchers("/api/v1/admin/**").authenticated()
                // Sécurise la route pour les utilisateurs avec le rôle ADMIN
                a.requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN");
                // Sécurise la route pour les utilisateurs avec le rôle USER
                a.requestMatchers("/api/v1/user/**").hasAuthority("USER");
                // Autorise toutes les autres requêtes sans nécessiter d'authentification.
                a.anyRequest().permitAll();
            });

        return http.build();
    }
}
````



## Autorisations des demandes

- `permitAll` - La demande ne nécessite aucune autorisation et est un point de terminaison public ; Notez que dans ce cas, [`Authentication` n’est](https://docs.spring.io/spring-security/reference/servlet/authentication/architecture.html#servlet-authentication-authentication) jamais récupérée à partir de la session
- `denyAll` - La demande n’est en aucun cas autorisée; Notez que dans ce cas, `Authentication` n’est jamais récupérée à partir de la session
- `hasAuthority` - La demande nécessite que `Authentication` ait [un `GrantedAuthority`](https://docs.spring.io/spring-security/reference/servlet/authorization/architecture.html#authz-authorities) qui correspond à la valeur donnée
- `hasRole` - Un raccourci pour `hasAuthority` qui préfixe `ROLE_` ou tout ce qui est configuré comme préfixe par défaut
- `hasAnyAuthority` - La demande nécessite que `Authentication` ait un `GrantedAuthority` qui correspond à l’une des valeurs données
- `hasAnyRole` - Un raccourci pour `hasAnyAuthority` qui préfixe `ROLE_` ou tout ce qui est configuré comme préfixe par défaut
- `access` - La demande utilise ce `AuthorizationManager` personnalisé pour déterminer l’accès
- 

## HttpSécurité (Configuration par default)

https://docs.spring.io/spring-security/reference/servlet/configuration/java.html

Jusqu’à présent, notre [`WebSecurityConfig`](https://docs.spring.io/spring-security/reference/servlet/configuration/java.html#jc-hello-wsca) ne contient que des informations sur la façon d’authentifier nos utilisateurs. Comment Spring Security sait-elle que nous voulons exiger que tous les utilisateurs soient authentifiés ? Comment Spring Security sait-elle que nous voulons prendre en charge l’authentification basée sur les formulaires ? En fait, il existe une classe de configuration (appelée `SecurityFilterChain`) qui est appelée en arrière-plan. Il est configuré avec l’implémentation par défaut suivante :

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	http
		.authorizeRequests(authorize -> authorize
			.anyRequest().authenticated()
		)
		.formLogin(withDefaults())
		.httpBasic(withDefaults());
	return http.build();
}
```

La configuration par défaut (illustrée dans l’exemple précédent) :

- Garantit que toute demande adressée à notre application nécessite que l’utilisateur soit authentifié

- Permet aux utilisateurs de s’authentifier avec une connexion basée sur un formulaire

- Permet aux utilisateurs de s’authentifier avec l’authentification HTTP Basic

  

### Configuration perso avec JWT à modifier

````java
// src/main/java/com/api/mushroom/configuration/SecurityConfiguration.java

package com.api.mushroom.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private  final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
````



### Configurer la protection CSRF

Spring Security protège par défaut contre les attaques CSRF, le `CsrfToken` est nécessaire chaque fois qu’une demande est effectuée avec une [méthode HTTP non sécurisée](https://docs.spring.io/spring-security/reference/features/exploits/csrf.html#csrf-protection-idempotent), telle qu’un POST. En outre, il est nécessaire pour toute requête qui  restitue le jeton à la réponse, telle qu’une page Web avec une balise  <`<form>`> qui inclut une `<input>` masquée pour le jeton CSRF.

	@Configuration
	@EnableWebSecurity
	public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			// ...
			.csrf(Customizer.withDefaults());
		return http.build();
		}
	}
https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#servlet-csrf-configure-disable



### CORS

La méthode cors()  ajoutera le CorsFilter fourni par Spring au contexte de l'application, en contournant les vérifications d'autorisation pour les requêtes OPTIONS.

````java
 @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors()
            .disable()
            ...
            ;
        return http.build();
    }
````

#### Ajouter une configuration cors

````
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200") //l'URL du frontend Angular
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("*");
            }
        };
    }
}
````

#### OU utiliser les annotation sur la classe ou méthode du contrôleur (@CrossOrigin )

````java
@RestController
@RequiredArgsConstructor
++ @CrossOrigin
@RequestMapping("api/v1/admin/mushroom")
public class MushroomCrudController {
   ...
}
````

````java
   public class MushroomCrudController {
    // Via l'annotation @RequiredArgsConstructor Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    final MushroomService mushroomService;

    // GET - Récupère un tableau d'enregistrement
    @CrossOrigin("http://example.com")
    @GetMapping(name = "/")
    public Iterable<MushroomEntity> getAll() {
        return mushroomService.getAll();
    }
````



### GESTION DES ROLES



````
++ @PreAuthorize("hasAuthority('ADMIN')")
@GetMapping("/{id}")
public ResponseEntity<DvdGetDto> findById(@PathVariable("id") Long id) throws DvdNotFoundException 	{
	...
}
````

OU BIEN

````
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
++ @EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
        
````

````
++ @Secured("ADMIN")
@GetMapping("/{id}")
public ResponseEntity<DvdGetDto> findById(@PathVariable("id") Long id) throws DvdNotFoundException 	{
	...
}
````

````
@AuthenticationPrincipal : Cette annotation permet d'injecter l'utilisateur actuellement authentifié en tant qu'argument d'une méthode. Vous pouvez ensuite accéder aux détails de l'utilisateur pour prendre des décisions basées sur son rôle ou ses autorisations.

````



### Configuration de spring boot

Configuration du fichier `application.yml`

### Création du model

création des classes d'Entité, elles représentent les tables de la BDD  les propriétes représentent les colonnes.

----- ALIMENTER AVEC UN EXEMPLE D'ENTITE

### Génération de la base de données

----- ALIMENTER AVEC UN EXEMPLE D'ENTITE

### Création de la couche DAO - repository

Création de la couche DAO =repository

----- ALIMENTER AVEC UN EXEMPLE D'ENTITE


### Couche de services

----- ALIMENTER AVEC UN EXEMPLE D'ENTITE



## MapStruct : Mapper les données d'une couche à l'autre

Le service ne connait pas le dto (donc on mappe le dto en service dans le controller ou le modelRepo en modelService dans le service)

Controler: Dto => Service: modelService => RepositorymodelRepository (entity)

#### Configuration de MapStruct `pom.xml`

````
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct</artifactId>
    <version>1.5.5.Final</version>
</dependency>

<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct-processor</artifactId>
    <version>1.5.5.Final</version>
</dependency>

...

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <excludes>
                    <exclude>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                    </exclude>
                </excludes>
            </configuration>
        </plugin>
        <!--	plug-in MapStruct	-->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>${maven-compiler-plugin.version}</version>
            <configuration>
                <release>${java.version}</release>
                <annotationProcessorPaths>
                    <path>
                        <groupId>org.mapstruct</groupId>
                        <artifactId>mapstruct-processor</artifactId>
                        <version>1.5.5.Final</version>
                    </path>
                    <path>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                        <version>${lombok.version}</version>
                    </path>
                    <path>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok-mapstruct-binding</artifactId>
                        <version>0.2.0</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
        <!--			-->
    </plugins>
</build>
````

### Le UserGetDto (Controller)

````
// src/main/java/com/api/mushroom/controller/user/UserGetDTO.java

@Data
@NoArgsConstructor
public class UserGetDTO {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String pseudo;
    private Collection<? extends GrantedAuthority> authorities;
    private String lastname;
    private String firstname;
    private String email;
    private String filename;
}
````

### Le UserGetDtoMapper

````
// src/main/java/com/api/mushroom/controller/user/UserGetDtoMapper.java

package com.api.mushroom.controller.user;

import com.api.mushroom.service.user.UserServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserGetDtoMapper {
    UserGetDtoMapper INSTANCE = Mappers.getMapper(UserGetDtoMapper.class);

    UserGetDTO userServiceModelToUserGetDTO(UserServiceModel userServiceModel);
    UserServiceModel UserGetDTOToUserServiceModel(UserGetDTO userGetDTO);
}
````





### Le  UserServiceModel 

````
````



#### Créer l'interface de mappage

````
import com.api.mushroom.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    
    //@Mapping(target = "role", ignore = true) // Ignore le champ email
    UserGetDTO userEntityToUserGetDTO(UserEntity userEntity);
}
````

#### Compiler avec maven, mapStruct va générer les classes de mapper dans le dossier Target

 `clean package`





# Requête SQL avec Spring Boot et JPA



## Méthode avec @NamedQuery

ENTITY

````
// src/main/java/com/api/mushroom/entity/MushroomEntity.java


@NamedQuery(name = "MushroomEntity.findAllByVisibility", query = "SELECT m FROM MushroomEntity m WHERE m.visibility = :visibility")
public class MushroomEntity {
	...
}
````

````
// POUR REGROUPER PLUSIEURS REQUETES
@NamedQueries({
    @NamedQuery(name = "MushroomEntity.findAllByVisibility", query = "SELECT m FROM MushroomEntity m WHERE m.visibility = :visibility"),
    @NamedQuery(name = "MushroomEntity.findBySlug", query="SELECT m FROM MushroomEntity m WHERE m.slug=:slug")
})
````



CONTROLLER

````
// src/main/java/com/api/mushroom/controller/MushroomController.java

public class MushroomController {
    ...
    // GET - Retourne un tableau d'objets - liste de tous les enregistREments validé par l'administrateur pour la publication.
    @GetMapping(name = "/")
        public Iterable<MushroomEntity> findAllByVisibility() {
        return mushroomService.findAllByVisibility(true);
    }
    ...
}
````



PUIS **SOLUTION N° 1**

SERVICE

````
@Service
public class MushroomService {
	...
    // GET - Retourne un tableau d'objets - liste de tous les enregistrements validés par l'administrateur pour la publication.
    public List<MushroomEntity> findAllByVisibility(boolean isVisible) {
    	return mushroomJpaRepository.findAllByVisibility(isVisible);
    }
    ...
}
````

REPOSITORY

````
// src/main/java/com/api/mushroom/repository/MushroomJpaRepository.java

@Repository
public interface MushroomJpaRepository extends JpaRepository<MushroomEntity, Long> {

	List<MushroomEntity> findAllByVisibility(boolean visibility);
	
	...
}
````



OU **SOLUTION N° 2**

````
Query<MushroomEntity> query = session.createNamedQuery("MushroomEntity.findAllIsVisibility", MushroomEntity.class);
query.setParameter("visibility", true");
MushroomEntity result = query.getSingleResult();
````

OU 

````
NativeQuery query = session.getNamedNativeQuery("MushroomEntity.findAllIsVisibility");
query.setParameter("visibility", true");
MushroomEntity result = (MushroomEntity) query.getSingleResult();
````

OU encore avec **l'EntityManager** dans le service sans utiliser l'interface repository

SERVICE

````
@Service
public class MushroomService {

	EntityManager entityManager; // Initialisez votre EntityManager 

	List<MushroomEntity> mushroomsByVisibility = entityManager
	  .createNamedQuery("MushroomEntity.findAllIsVisibility")
      .setParameter("visibility", true)
      .getResultList();
}
````

---


## Méthode avec @Query

Utiliser l’annotation @Query dans Spring Data JPA pour exécuter des requêtes JPQL et SQL natives.

CONTROLLER

````
    // src/main/java/com/api/mushroom/controller/admin/MushroomCrudController.java
    ...
    @GetMapping("/test/{titre}")
    public  Iterable<MushroomEntity> getSearch(@PathVariable("titre") String titre) {
        return mushroomService.getSearch(titre);
    }
    ...
````

SERVICE

````
   // src/main/java/com/api/mushroom/service/MushroomService.java
   ...
   public List<MushroomEntity> getSearch(String titre) {
        return mushroomJpaRepository.getSearch(titre);
    }
    ...
````

REPOSITORY

````
 // src/main/java/com/api/mushroom/repository/MushroomJpaRepository.java
 ...
 @Query("SELECT m FROM MushroomEntity m WHERE m.commonname = :commonname")
    List<MushroomEntity> getSearch(String commonname);
 ...
````

---

## Méthode pour inverser l'état d'une propriété

````
    // src/main/java/com/api/mushroom/controller/admin/MushroomCrudController.java
    
    @RestController
	@RequestMapping("api/v1/admin/mushroom")
	public class MushroomCrudController {
        ...
        @PatchMapping("/publier/{id}")
        public void invertPublish(@PathVariable("id") Long id) {
            mushroomService.invertPublish(id);
        }
        ...
    }
````



````
// src/main/java/com/api/mushroom/service/MushroomService.java

@Service
public class MushroomService {
   ...
   // inverse l'état booleen du champ visibility
    public void invertPublish(Long id){
        mushroomJpaRepository.findById(id)
            .map(mushroom -> {
                boolean isVisible = mushroom.isVisibility();
                mushroom.setVisibility(!isVisible);
                return mushroomJpaRepository.save(mushroom);
            });
    }
    ...
}
````

---

## Autre exemple avec plusieurs filtres

Les :params doivent porter le meme nom que les variables dans la methode du repository
````java
@Entity
@Data
@Table(name = "mushroom")
@NamedQuery(name = "MushroomEntity.findAllByVisibility", query = "SELECT m FROM MushroomEntity m WHERE m.visibility = :visibility AND commonname = :nom " )
public class MushroomEntity {
   ...
}
````

````java
@Repository
public interface MushroomJpaRepository extends JpaRepository<MushroomEntity, Long> {
    List<MushroomEntity> findAllByVisibility(boolean visibility, String nom);
}
````
A SAVOIR

````
Définir l’ordre dans une requête
public Iterable<MushroomEntity> getAll() {
        return mushroomJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "commonname"));
    }
````

## JPA PERSISTANCE DES DONNES EN CASCADE

````
@OneToMany(mappedBy = "mushroomEntity",cascade = CascadeType.PERSIST, orphanRemoval = true)
private List<MediaEntity> medias = new ArrayList<>();
````

L'attribut cascade = CascadeType.PERSIST dans une relation entre entités avec JPA indique que l'opération de persistance (ajout en base de données) effectuée sur l'entité parente sera propagée aux entités enfants associées. Cela signifie que lorsque vous persistez (ajoutez) une entité parente, les entités enfants associées seront également persistées automatiquement.

L'attribut orphanRemoval = true dans une relation entre entités avec JPA signifie que les entités enfants seront automatiquement supprimées de la base de données lorsque elles ne sont plus associées à l'entité parente.

`orphanRemoval = true` et `CascadeType.REMOVE` sont deux mécanismes différents pour gérer la suppression d'entités enfants associées à une entité parente dans une relation JPA. Bien qu'ils semblent similaires, ils ont des comportements légèrement différents :

1. **orphanRemoval = true :**

   L'attribut `orphanRemoval = true` est une fonctionnalité de JPA qui permet de supprimer automatiquement les entités enfants lorsqu'elles sont dissociées de l'entité parente.

2. **CascadeType.REMOVE :**

   `CascadeType.REMOVE` est une option de cascade qui permet de propager l'opération de suppression de l'entité parente aux entités enfants associées.

En résumé, `orphanRemoval = true` se concentre sur la gestion des entités enfants lorsque leur relation avec l'entité parente est rompue (en les retirant de la collection ou en mettant à jour les références), tandis que `CascadeType.REMOVE` se concentre sur la suppression en cascade lorsqu'une opération de suppression est effectuée explicitement sur l'entité parente.


## CRUD

`@PostMapping("")`, `@PutMapping()`, `@PatchMapping()`, `@DeleteMapping()`, `@GetMapping()` : Annotation à utiliser pour mapper une méthode à une requête HTTP POST envoyée à l'URL spécifiée

@PutMapping("/{id}"): Pour effectuer une mise à jour complète de l'entité.

@PatchMapping("/{id}"): Pour effectuer une mise à jour partielle de l'entité.



## ReponseEntity

`ResponseEntity` est une classe de Spring Framework qui permet de personnaliser la réponse HTTP renvoyée par votre contrôleur. Elle offre de nombreuses façons de personnaliser la réponse en fonction de vos besoins. Voici quelques-unes des principales façons d'utiliser `ResponseEntity` :

1. **Réponse avec un objet et un code d'état :**
   
   ```java
   ResponseEntity<MyObject> responseEntity = ResponseEntity.ok(myObject);
   ```

   Vous pouvez utiliser `ResponseEntity.ok(objet)` pour renvoyer un objet avec un code d'état `200 OK`.

2. **Réponse avec un code d'état sans contenu :**

   ```java
   ResponseEntity<Void> responseEntity = ResponseEntity.noContent().build();
   ```

   Vous pouvez utiliser `ResponseEntity.noContent()` pour renvoyer une réponse sans contenu avec un code d'état `204 No Content`.

3. **Réponse avec un code d'état personnalisé :**

   ```java
   ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur de validation");
   ```

   Vous pouvez utiliser `ResponseEntity.status(code d'état)` pour définir un code d'état personnalisé et un corps de réponse.

4. **Réponse avec des en-têtes personnalisés :**

   ```java
   HttpHeaders headers = new HttpHeaders();
   headers.add("Custom-Header", "Valeur personnalisée");
   ResponseEntity<String> responseEntity = new ResponseEntity<>("Contenu", headers, HttpStatus.OK);
   ```

   Vous pouvez utiliser `ResponseEntity` avec des en-têtes personnalisés en créant un objet `HttpHeaders` et en le passant comme troisième argument.

5. **Réponse avec une redirection :**

   ```java
   HttpHeaders headers = new HttpHeaders();
   headers.setLocation(new URI("/nouvelle-page"));
   ResponseEntity<Void> responseEntity = new ResponseEntity<>(headers, HttpStatus.FOUND);
   ```

   Vous pouvez utiliser `ResponseEntity` pour effectuer une redirection en définissant un en-tête `Location` et en utilisant le code d'état approprié, par exemple, `HttpStatus.FOUND` (code 302).

6. **Réponse avec une erreur et un message d'erreur :**

   ```java
   ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ressource introuvable");
   ```

   Vous pouvez utiliser `ResponseEntity` pour renvoyer une réponse d'erreur avec un code d'état approprié et un message d'erreur.

7. **Réponse avec un type générique :**

   ```java
   ResponseEntity<?> responseEntity = ResponseEntity.ok().build();
   ```

   Vous pouvez utiliser `ResponseEntity` avec un type générique non spécifié (`?`) pour renvoyer une réponse de type générique. Cela peut être utile lorsque vous ne connaissez pas à l'avance le type de réponse.

8. **Réponse avec un flux de données :**

   ```java
   InputStream inputStream = new FileInputStream("fichier.pdf");
   HttpHeaders headers = new HttpHeaders();
   headers.setContentType(MediaType.APPLICATION_PDF);
   ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(IOUtils.toByteArray(inputStream), headers, HttpStatus.OK);
   ```

   Vous pouvez utiliser `ResponseEntity` pour renvoyer un flux de données, par exemple, un fichier PDF. Vous pouvez spécifier le type MIME approprié dans les en-têtes.

9. **Réponse avec des cookies :**

   ```java
   HttpHeaders headers = new HttpHeaders();
   headers.add(HttpHeaders.SET_COOKIE, "nom=John; Path=/; Secure; HttpOnly");
   ResponseEntity<String> responseEntity = new ResponseEntity<>("Contenu", headers, HttpStatus.OK);
   ```

   Vous pouvez utiliser `ResponseEntity` pour renvoyer des cookies dans les en-têtes de la réponse.

10. **Réponse avec des en-têtes CORS pour l'API RESTful :**

    ```java
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
    headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS");
    ResponseEntity<String> responseEntity = new ResponseEntity<>("Contenu", headers, HttpStatus.OK);
    ```

    Vous pouvez utiliser `ResponseEntity` pour ajouter des en-têtes CORS (Cross-Origin Resource Sharing) pour autoriser les requêtes provenant de domaines différents dans une API RESTful.

Ces exemples illustrent quelques-unes des nombreuses façons dont vous pouvez personnaliser la réponse HTTP à l'aide de `ResponseEntity` dans Spring Framework. En fonction de vos besoins spécifiques, vous pouvez utiliser différentes méthodes de création de `ResponseEntity` pour adapter la réponse à votre cas d'utilisation.



# A TRIER

## Crud


MushroomEntity existingMushroom = mushroomJpaRepository.findById(id).orElse(null);

        if (existingMushroom == null) {
            return ResponseEntity.notFound().build();
        }



## Authentification d’API via JWT et les Cookies

https://www.ruddy-palvair.fr/articles/java/securiser-une-api-rest-avec-jwt-et-spring-boot/

Un token JWT est transmis à travers les headers d’une requête HTTP. Il  est composé de 3 parties: le header, le payload, la signature. Les trois parties sont encodées en base 64 et concaténées avec un « . » afin de  former un seul élément. C’est donc une manière compacte d’échanger des  informations. La signature permet de s’assurer que le token n’a pas été  altéré depuis sa création.  Pour avoir plus de détails sur le  fonctionnement de JWT et la structure d’un token, tu peux consulter le  standard [RFC 759](https://datatracker.ietf.org/doc/html/rfc7519).

Scénario qui se déroule lorsqu’un utilisateur souhaite accéder à une ressource sécurisée :

1. Un utilisateur s’authentifie avec ses credentials et reçois en retour un Token JWT.
2. L’utilisateur tente d’accéder à une ressource sécurisée en passant le token dans les headers de la requête.
3. L’application extrait le *subject* du token. Dans notre cas, le *subject* correspond au nom de l’utilisateur.
4. L’application va ensuite vérifier que l’utilisateur existe et mettre à jour le  contexte Spring Security avec l’utilisateur authentifié.
5. L’utilisateur est donc autorisé à consommer la ressource qu’il demande.

### Stockage dans un cookies

Générer côté serveur un token, puis envoyer sous forme de Cookie avec la fonction set de la librairie Cookies.

Le Cookie est par la suite automatiquement transmis au serveur par le navigateur a chaque requête.

````
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

// Créer un objet Cookie
String tokenValue = "votre_token_ici";
Cookie tokenCookie = new Cookie("access_token", tokenValue);

// Définir les attributs du cookie
//tokenCookie.setMaxAge(3600); // Durée de vie du cookie en secondes (ici, 1 heure)
//tokenCookie.setPath("/");    // Chemin pour lequel le cookie est valide (ici, tout le site)
tokenCookie.setHttpOnly(true); // Empêche l'accès au cookie via JavaScript côté client
tokenCookie.setSecure(true);   // Le cookie ne peut être envoyé que via HTTPS

// Ajouter le cookie à la réponse HTTP
HttpServletResponse response = ...; // Obtenez l'objet HttpServletResponse
response.addCookie(tokenCookie);


````

### Stockage dans le Web Storage

Le token est renvoyé dans la response HTTP.

Pour le renvoyer par la suite avec chaque requête, on utilise le paramètre *« Authorization »* du header HTTP. 



## Upload de fichiers

Les données de l'image sont renvoyées sous la forme d'un tableau de bytes (byte[]) dans le corps de la réponse HTTP. Voici comment cela fonctionne en détail :Récupère et traite la réponse avec typescript au format JSON.

````javascript
// POST - Ajoute un nouvel enregistrement 
if (this.selectedFile) {
  const formData = new FormData();
  formData.append('file', this.selectedFile);
  formData.append('name', form.value.name);

  console.log("formData: ", formData)

  this.http.post(this.API_ADMIN_BASE_URL + "edibility/", formData).subscribe(
    {
      next: (response) => {
        console.log('message: ', response);
        // redirige vers la liste
        this.router.navigate(["admin/comestibilite/liste"]);
      },
      error: (err) => console.error('Erreur lors du téléchargement du fichier.', err),
      complete: () => console.log('Fichier téléchargé avec succès.')
    });
} else {
  console.log('Le formulaire est invalide.');
}
````



## Upload multiple en une seul requête

 ### coté front

````javascript
const formData: FormData = new FormData();
for (const file of files) {
  formData.append('files', file);
}

const url = 'https://example.com/upload-multiple-files';
this.http.post(url, formData).subscribe(response => {
  console.log(response);
});
````

### coté api

````java
@RestController
@RequestMapping("/api")
public class FileUploadController {

    @PostMapping("/upload-multiple-files")
    public ResponseEntity<String> uploadMultipleFiles(@RequestParam("files") List<MultipartFile> files) {
        // Traitez les fichiers ici
        for (MultipartFile file : files) {
            // Traitez chaque fichier individuellement
        }
        return ResponseEntity.ok("Files uploaded successfully.");
    }
}

````





## REPOSITORY

````java
// Inverse la valeur booléen du champ visibility
public void invertPublish(Long id){
     mushroomJpaRepository.findById(id)
     .map(mushroom -> {
        boolean isVisible = mushroom.isVisibility();
        mushroom.setVisibility(!isVisible);
        return mushroomJpaRepository.save(mushroom);
     });
}
````


````java
//  Si aucune entité EdibilityEntity n'est trouvée avec l'ID spécifié nne exception est lever si la valeur n'est pas présente dans l'optionnel (Optional), une NoSuchElementException sera levée avec le message d'erreur spécifié.

EdibilityEntity edibilityEntity = edibilityJpaRepository.findById(id).orElseThrow(
     () -> new NoSuchElementException("Aucune entité EdibilityEntity trouvée avec l'ID : " + id)
);
````



## Exemple de code

````
    // Inverse la valeur booléen du champ visibility
    public boolean invertPublish(Long id){
        Optional<MushroomEntity> mushroomEntity = mushroomJpaRepository.findById(id);
        if (mushroomEntity.isPresent()){
            MushroomEntity mushroom = mushroomEntity.get();
            boolean isVisible = mushroom.isVisibility();
            mushroom.setVisibility(!isVisible);
            MushroomEntity updatedMushroom = mushroomJpaRepository.save(mushroom);
            return updatedMushroom != null; // on sait que != null car .isPresent() est true
        }
        return false;
    }

AUTRE METHODE

    public boolean invertPublish1(Long id){
        return mushroomJpaRepository.findById(id)
                .map(mushroom -> {
                    boolean isVisible = mushroom.isVisibility();
                    mushroom.setVisibility(!isVisible);
                    MushroomEntity updatedMushroom = mushroomJpaRepository.save(mushroom);
                    return updatedMushroom != null;
                })
                .orElse(false);
    }
````

