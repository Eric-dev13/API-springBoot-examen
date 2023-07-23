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


## Requête SQL

Exemple de Named Query dans une entité avec Spring Boot et JPA :

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

3. Utilisation de la Named Query dans le service ou le contrôleur :

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


## Sécurité

érer les autorisations sur des routes (endpoints) en utilisant Spring Security. Spring Security est un module de sécurité puissant qui permet de définir des règles d'accès et de contrôler l'accès aux ressources de votre application.

Dans le contexte de Spring Security (la gestion de la sécurité dans les applications Spring), lorsque vous vous connectez avec succès en tant qu'utilisateur authentifié, vous obtenez un "jeton" (ou "token") d'accès qui vous permet d'accéder à certaines ressources protégées ou d'exécuter certaines actions spécifiques. Dans ce cas, vous pouvez dire que l'accès à ces ressources ou actions a été "granted" (accordé) à l'utilisateur.

