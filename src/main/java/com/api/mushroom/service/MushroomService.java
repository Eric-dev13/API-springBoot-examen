package com.api.mushroom.service;

import com.api.mushroom.entity.LocalnameEntity;
import com.api.mushroom.entity.MediaEntity;
import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.repository.MediaJpaRepository;
import com.api.mushroom.repository.MushroomJpaRepository;
import com.api.mushroom.service.utils.FileUploadService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MushroomService {

    private final MushroomJpaRepository mushroomJpaRepository; // Créer une instance de mushroomJpaRepository via le constructeur.
    private final EntityManager entityManager; // Créer une instance de l'EntityManager via le constructeur.
    private final MediaService mediaService;  // Créer une instance de MediaService via le constructeur.
    private final FileUploadService fileUploadService;


    // GET - Récupère un tableau d'enregistrement trié par nom commun
    public Iterable<MushroomEntity> getAll() {
        return mushroomJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "commonname"));
    }

    // GET - Retourne un tableau d'objets - liste de tous les enregistrements validés par l'administrateur pour la publication.
    public List<MushroomEntity> findAllByVisibility(boolean isVisible) {
        return mushroomJpaRepository.findAllByVisibility(isVisible);
    }

    // GET - Récupère un enregistrement par l'ID
    public Optional<MushroomEntity> getById(Long id){
        return mushroomJpaRepository.findById(id);
    }

    // POST : Ajouter un enregistrement
    public MushroomEntity add(MushroomEntity mushroomEntity) {
//        // Les opérations de persistance vont créer automatiquement les enregistrements dans les tables, il faut ajouter la cle étrangère pour la liaison.
//        for (MediaEntity media : mushroomEntity.getMedias()) {
//            // Renommer le fichier
//            // Upload le fichier
//            // Associe chaque média nouvellement crée à l'entité MushroomEntity (RENSIGNE LA CLE ETRANGERE)
//            media.setMushroomEntity(mushroomEntity);
//            // PERSISTE EN BASE DE DONNEE /
//            //mediaJpaRepository.save(media);
//        }
        for (LocalnameEntity localname : mushroomEntity.getLocalnames()) {
            // Ici pas besoin de persister, car on a, définit, cascade = CascadeType.PERSIST dans MushroomEntity pour cette propriété.
            // Associe chaque enregistrement "nom local" nouvellement crée à l'entité MushroomEntity
            localname.setMushroomEntity(mushroomEntity);
        }
        return mushroomJpaRepository.save(mushroomEntity);
    }

    // UPDATE : Mettre à jour un enregistrement
    public MushroomEntity put(MushroomEntity mushroomEntity){
        return mushroomJpaRepository.save(mushroomEntity);
    }

    // PATCH : Mise à jour partiel d'un enregistrement
    public MushroomEntity patch(MushroomEntity mushroomEntity){
        return mushroomJpaRepository.save(mushroomEntity);
    }

    // delete : Supprimer un enregistrement
    public void delete(Long id) {
        mushroomJpaRepository.deleteById(id);
    }

    // Inverse la valeur booléen du champ visibility
    public void invertPublish(Long id){
        mushroomJpaRepository.findById(id)
            .map(mushroom -> {
                boolean isVisible = mushroom.isVisibility();
                mushroom.setVisibility(!isVisible);
                return mushroomJpaRepository.save(mushroom);
            });
    }

    //TEST
    public List<MushroomEntity> getSearch(String titre) {
        return mushroomJpaRepository.getSearch(titre);
    }

/* ----------------------------------------------------------------------------------------------------------------------- */

    // GET - Retourne une liste de tableau de 3 propriétés.
    public List findAllByVisibilityWithTitleImageEdibility () {
        return entityManager
                .createNamedQuery("MushroomEntity.findAllTitleImageEdibilityByVisibility")
                .setParameter("visibility", true)
                .getResultList();
    }

    public Optional<MushroomEntity> findBySlug(String slug){
        try {
            MushroomEntity result = entityManager
                    .createNamedQuery("MushroomEntity.findBySlug", MushroomEntity.class)
                    .setParameter("slug", slug)
                    .getSingleResult();
            return Optional.of(result);
        } catch (
                NoResultException ex) {
            return Optional.empty();
        }
    }

//    public MushroomEntity addNewMushroomWithFileUpolad(List<String> mediasNames, List<MultipartFile> mediasFiles) throws IOException {
//        // upload du fichier
//        // Traitez les noms et les fichiers ici
//        /*for (int i = 0; i < mediasNames.size(); i++) {
//            String mediaName = mediasNames.get(i);
//            MultipartFile mediaFile = mediasFiles.get(i);
//
//            String newFilename = fileUploadService.fileUpload(mediaFile, "mushrooms/");
//            MediaEntity mediaEntity = new MediaEntity();
//            mediaEntity.setName(mediaName);
//            mediaEntity.setFilename(newFilename);
//            mediaEntity.setMushroomEntity();
//            mediaService.add(mediaEntity);
//        }*/
//
//        return null;
//    }




    /*
    // Exemple avec mappage des données (forunit par le DAO: MushroomJpaRepository)  vers un objet DTO.
    public MushroomDTO getMushroomById(Long id) {
        MushroomEntity mushroomEntity = mushroomJpaRepository.findById(id).orElse(null);
        return convertToDTO(mushroomEntity);
    }

    // Autres méthodes pour effectuer des opérations métier avec les utilisateurs
    // ...

    private MushroomDTO convertToDTO(MushroomEntity mushroomEntity) {
        // instancie un nouvel mushroomDTO pour chaque objet renvoyé par le DAO.
        MushroomDTO mushroomDTO = new MushroomDTO();

        mushroomDTO.setComment(mushroomEntity.getComment());
        mushroomDTO.setId(mushroomEntity.getId());
        mushroomDTO.setCreatedAt(mushroomEntity.getCreatedAt());
        mushroomDTO.setUpdatedAt(mushroomEntity.getUpdatedAt());
        //mushroomDTO.setVisibility(mushroomEntity.getVisibility());
        mushroomDTO.setCommonname(mushroomEntity.getCommonname());
        mushroomDTO.setLatinname(mushroomEntity.getLatinname());
        mushroomDTO.setHat(mushroomEntity.getHat());
        mushroomDTO.setLamella(mushroomEntity.getLamella());
        mushroomDTO.setFoot(mushroomEntity.getFoot());
        mushroomDTO.setHabitat(mushroomEntity.getHabitat());
        mushroomDTO.setComment(mushroomEntity.getComment());
        mushroomDTO.setSlug(mushroomEntity.getSlug());

        return mushroomDTO;
    }
     */
}
