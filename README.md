# API springBoot - Projet pour l'EXAMEN

**Pourquoi spring boot:**

Spring Boot facilite la configuration et le démarrage de projet en générant un squelette prêt à l'emploi avec les dépendances et la configuration souhaitées.

## Démarrer un nouveau projets
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


## Les dépendances du projet

- Spring Web Web : Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
- Lombok Developer Tools : C'est une bibliothèque Java qui aide à réduire le code et éviter les répétitions comme les getters, setters, etc.
- Spring Data JPA SQL : Utiliser Spring Data et Hibernate pour persister les données dans les magasins SQL avec l'API Java Persistence.
- Spring Boot DevTools Developer Tools : Fournit des redémarrages rapides des applications, LiveReload et des configurations pour une expérience de développement améliorée.
- OAuth2 Client Security : Intégration de Spring Boot pour les fonctionnalités du client Spring Security OAuth2/OpenID Connect. (cas d'un "login with Google" par exemple)
- Spring Security Security : Cadre d'authentification et de contrôle d'accès hautement personnalisable pour les applications Spring.
- MySQL Driver SQL : Pilote JDBC pour MySQL.


## Structure du projet

![img.png](assets.readme/img.png)

## Stockages des assets dans l'arborescence du projet

Dans un projet Spring Boot, vous pouvez stocker les fichiers statiques, y compris les images, dans différents endroits en fonction de vos besoins spécifiques.

Voici quelques options courantes pour stocker les assets dans un projet Spring Boot avec une API REST :

1. **Répertoire "src/main/resources/static" :** Dans un projet Spring Boot, vous pouvez placer les fichiers statiques tels que les images dans le répertoire "src/main/resources/static". Les fichiers dans ce répertoire sont accessibles publiquement par le serveur et peuvent être servis directement via l'URL. Par exemple, si vous avez un fichier "image.jpg" dans "src/main/resources/static/images", vous pouvez y accéder avec l'URL "http://localhost:8080/images/image.jpg".

2. **Répertoire "src/main/resources/public" :** De la même manière que "src/main/resources/static", vous pouvez également placer les fichiers statiques dans le répertoire "src/main/resources/public". Les fichiers dans ce répertoire sont également accessibles publiquement par le serveur.

3. **Répertoire externe configurable :** Si vous préférez stocker les fichiers statiques en dehors du répertoire du projet, vous pouvez configurer un répertoire externe dans votre application Spring Boot en utilisant des propriétés de configuration. Cela vous permettra de spécifier le chemin d'accès au répertoire où vous stockez les fichiers statiques dans votre application.

4. **Stockage dans le système de fichiers :** Pour une solution plus évolutive et flexible, vous pouvez utiliser un système de stockage de fichiers dédié, comme Amazon S3, Google Cloud Storage ou tout autre service de stockage cloud. Vous pouvez également utiliser des solutions de stockage sur site, comme NFS, CIFS, etc.

Dans tous les cas, assurez-vous que les fichiers statiques sont correctement configurés et accessibles publiquement par le serveur pour que les clients puissent y accéder via les URLs appropriées. Pensez également à configurer correctement les en-têtes de réponse HTTP pour la mise en cache, la compression, etc., selon les besoins de votre application.

## Sécurité

https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html

Gérer les autorisations sur des routes (endpoints) en utilisant Spring Security. Spring Security est un module de sécurité puissant qui permet de définir des règles d'accès et de contrôler l'accès aux ressources de votre application.

Dans le contexte de Spring Security (la gestion de la sécurité dans les applications Spring), lorsque vous vous connectez avec succès en tant qu'utilisateur authentifié, vous obtenez un "jeton" (ou "token") d'accès qui vous permet d'accéder à certaines ressources protégées ou d'exécuter certaines actions spécifiques. Dans ce cas, vous pouvez dire que l'accès à ces ressources ou actions a été "granted" (accordé) à l'utilisateur.

![](.\assets.readme\authorizationfilter.png)



```java
http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers("/resource/**").hasAuthority("USER")
        .anyRequest().authenticated()
    )
```

```java
http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(HttpMethod.GET).hasAuthority("read")
        .requestMatchers(HttpMethod.POST).hasAuthority("write")
        .anyRequest().denyAll()
    )
```

````
import static jakarta.servlet.DispatcherType.*;

import static org.springframework.security.authorization.AuthorizationManagers.allOf;
import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasAuthority;
import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@Bean
SecurityFilterChain web(HttpSecurity http) throws Exception {
	http
		// ...
		.authorizeHttpRequests(authorize -> authorize                                  (1)
            .dispatcherTypeMatchers(FORWARD, ERROR).permitAll() (2)
			.requestMatchers("/static/**", "/signup", "/about").permitAll()         (3)
			.requestMatchers("/admin/**").hasRole("ADMIN")                             (4)
			.requestMatchers("/db/**").access(allOf(hasAuthority('db'), hasRole('ADMIN')))   (5)
			.anyRequest().denyAll()                                                (6)
		);

	return http.build();
}
````





## Autorisation des demandes

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

  

### Configuration perso avec JWT

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

#### Configuration CORS globale
````java
@Override
public void addCorsMappings(CorsRegistry registry) {
registry.addMapping("/**");
}
````

#### @CrossOrigin sur la méthode du contrôleur

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

#### @CrossOrigin sur une méthode de gestionnaire annoté @RequestMapping

````java
@RestController
@RequiredArgsConstructor
++ @CrossOrigin
@RequestMapping("api/v1/admin/mushroom")
public class MushroomCrudController {
   ...
}
````

## Requête SQL

Exemple de Named Query (requêtes nommées) dans une entité avec Spring Boot et JPA :

1. **Définir l'entité `MushroomEntity` avec une Named Query**

````
// src/main/java/com/api/mushroom/entity/MushroomEntity.java

@Entity
@Data
@Table(name = "mushroom")
@NamedQuery(name = "MushroomEntity.findAllIsVisibility", query = "SELECT e FROM MushroomEntity e WHERE e.visibility = :visibility")
public class MushroomEntity {
    // instanciation de la classe slugify dans le constructeur (injection de dépendance).
    // private final Slugify slugify;
    //final Slugify slugify = Slugify.builder().build();

    // DECLARATION DES ATTRIBUTS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id auto-incrémente
    private Long id;

    ...

    @Column(name="visibility")
    private boolean visibility;
	...
	}
````

Nous  définissons une Named Query pour récupérer tous les enregistrements  dont  le champ visibility est true.

2. **Utiliser la Named Query dans le repository JPA**

   ````
   // src/main/java/com/api/mushroom/repository/MushroomJpaRepository.java
   
   @Repository
   public interface MushroomJpaRepository extends JpaRepository<MushroomEntity, Long> {
       List<MushroomEntity> findAllIsVisibility();
   }
   ````
   
OU

On peux utiliser ces requêtes nommées dans le code Java (service) en utilisant l'`EntityManager`

````
EntityManager entityManager; // Initialisez votre EntityManager ici

List<MushroomEntity> mushroomsByVisibility = entityManager.createNamedQuery("MushroomEntity.findAllIsVisibility")
                                                            .setParameter("visibility", true)
                                                            .getResultList();
````

**Méthode N°2**

3. Utilisation de la Named Query dans le service ou le contrôleur

```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceGreaterThan(double price);
}
```

Dans le repository JPA, nous avons défini une méthode `findByPriceGreaterThan`, qui correspond à la Named Query définie dans l'entité `Product`. Spring Data JPA utilisera automatiquement la Named Query pour exécuter la requête appropriée en fonction du nom de la méthode.

4. Utilisation de la Named Query dans le service ou le contrôleur :

   ````
   // src/main/java/com/api/mushroom/service/MushroomService.java
   
   @Data
   @RequiredArgsConstructor
   @Service
   public class MushroomService {
   
       //private final MushroomRepository mushroomRepository;
       private final MushroomJpaRepository mushroomJpaRepository;
   
   	...
   
       public List<MushroomEntity> findAllIsVisibility() {
           return mushroomJpaRepository.findAllIsVisibility();
       }
   }
   ````
### Autre exemple avec plusieurs filtres

Les :params doivent porter le meme nom que les variables dans la methode du repository
````java
@Entity
@Data
@Table(name = "mushroom")
@NamedQueries({
        @NamedQuery(name = "MushroomEntity.findAllByVisibility", query = "SELECT m FROM MushroomEntity m WHERE m.visibility = :visibility AND commonname = :nom " )
})
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