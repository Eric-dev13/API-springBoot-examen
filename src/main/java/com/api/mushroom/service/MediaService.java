package com.api.mushroom.service;

import com.api.mushroom.Mapper.MapStructMapper;
import com.api.mushroom.entity.MediaEntity;
import com.api.mushroom.entity.MushroomEntity;
import com.api.mushroom.repository.MediaJpaRepository;
import com.api.mushroom.repository.MushroomJpaRepository;
import com.api.mushroom.service.model.MediaServiceModel;
import com.api.mushroom.service.utils.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.management.relation.RoleNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MediaService {

    private final MediaJpaRepository mediaJpaRepository;
    private final FileUploadService fileUploadService;
    private final MushroomJpaRepository mushroomJpaRepository;
    private final MapStructMapper mapStructMapper;


    public List<MediaServiceModel> getAll() {
        List<MediaEntity> mediaEntity = mediaJpaRepository.findAll();
        List<MediaServiceModel> mediaServiceModels =  mediaEntity.stream().map(mapStructMapper::mediaEntityToMediaService).collect(Collectors.toList());
        return mediaServiceModels;
    }

    public MediaServiceModel getById(Long id) throws RoleNotFoundException {
        MediaEntity mediaEntity = mediaJpaRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("La référence avec l'ID spécifié n'a pas été trouvé."));
        return mapStructMapper.mediaEntityToMediaService(mediaEntity);
    }

    public MediaEntity add(MediaEntity mediaEntity) {
        return mediaJpaRepository.save(mediaEntity);
    }

    public List<MediaServiceModel> addMediasWithFileUpoladAndName(Long id, List<String> mediasNames, List<MultipartFile> mediasFiles) throws IOException {

        // Obtenir l'enregistrement champignon correspondant à l'ID fourni
        MushroomEntity mushroomEntity  = mushroomJpaRepository.findById(id).orElseThrow();

        // Initialise une liste pour stocker les nouvelles entités MediaEntity créées
        List <MediaEntity> mediaEntities = new ArrayList<>();

        // Parcourir chaque média à ajouter
        for (int i = 0; i < mediasNames.size(); i++) {
            // Récupérer le nom du média et le fichier correspondant
            String mediaName = mediasNames.get(i);
            MultipartFile mediaFile = mediasFiles.get(i);

            // Télécharger le fichier de média et obtient le nouveau nom de fichier
            String newFilename = fileUploadService.fileUpload(mediaFile, "mushrooms/");

            // Créer une nouvelle entité MediaEntity
            MediaEntity mediaEntity = new MediaEntity();
            mediaEntity.setName(mediaName);
            mediaEntity.setFilename(newFilename);
            mediaEntity.setMushroom(mushroomEntity);

            // Enregistrer la nouvelle entité MediaEntity dans la base de données
            MediaEntity savedMediaEntity = mediaJpaRepository.save(mediaEntity);

            // Ajouter la nouvelle entité MediaEntity à la liste des entités créées
            mediaEntities.add(savedMediaEntity);
        }
        return mediaEntities.stream().map(mapStructMapper::mediaEntityToMediaService).collect(Collectors.toList());
    }

    // POST : Ajouter une collection de médias nom + fileUpload
    public List<MediaEntity> addMediasWithFileUpolad(Long id, List<MultipartFile> mediasFiles) throws IOException {
        // Obtenir l'enregistrement champignon correspondant à l'ID fourni
        MushroomEntity mushroomEntity  = mushroomJpaRepository.findById(id).orElseThrow();

        // Initialise une liste pour stocker les nouvelles entités MediaEntity créées
        List <MediaEntity> mediaEntities = new ArrayList<>();

        // Parcourir chaque média à ajouter
        for (MultipartFile mediaFile : mediasFiles) {
                // Télécharger le fichier de média et obtient le nouveau nom de fichier
                String newFilename = fileUploadService.fileUpload(mediaFile, "mushrooms/");

                // Créer une nouvelle entité MediaEntity
                MediaEntity mediaEntity = new MediaEntity();
                mediaEntity.setFilename(newFilename);
                mediaEntity.setMushroom(mushroomEntity);

                // Enregistrer la nouvelle entité MediaEntity dans la base de données
                mediaJpaRepository.save(mediaEntity);

                // Ajouter la nouvelle entité MediaEntity à la liste des entités créées
                mediaEntities.add(mediaEntity);
        }
        // Retourner la liste des entités MediaEntity ajoutées dans la table des médias
        return mediaEntities;
    }


    // UPDATE : Mettre à jour un enregistrement
    public MediaEntity edit(MediaEntity mediaEntity){
        return mediaJpaRepository.save(mediaEntity);
    }


    // delete : Supprimer un enregistrement
    public boolean delete(Long id) {
        if(mediaJpaRepository.existsById(id)){
            mediaJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
