package com.api.mushroom.controller.admin.media;


import com.api.mushroom.Mapper.MapStructMapper;
import com.api.mushroom.controller.dto.MediaDto;
import com.api.mushroom.entity.MediaEntity;
import com.api.mushroom.service.MediaService;
import com.api.mushroom.service.model.MediaServiceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.management.relation.RoleNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin/media")
public class MediaCrudController {

    private final MediaService mediaService;
    private final MapStructMapper mapStructMapper;


    @GetMapping
    public List<MediaDto> getAll() {
        List<MediaServiceModel> mediaServiceModels = mediaService.getAll();
        List<MediaDto> mediaDtos = mediaServiceModels.stream().map(mapStructMapper::mediaServiceMediaDto).collect(Collectors.toList());
        return mediaDtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaDto> getById(@PathVariable("id") Long id) throws RoleNotFoundException {
        try {
            MediaServiceModel mediaServiceModel = mediaService.getById(id);
            return ResponseEntity.ok(mapStructMapper.mediaServiceMediaDto(mediaServiceModel));
        }  catch (RoleNotFoundException e) {
            // Gérer l'erreur lorsque le rôle n'est pas trouvé
            return ResponseEntity.notFound().build();
        }
    }

    // Enregistrer un lot d'images en une seule fois.
    @PostMapping("/{id}")
    public List<MediaDto> addMediasWithFileUpolad(@PathVariable("id") Long id, @RequestParam("mediasNames") List<String> mediasNames, @RequestPart("mediasFiles") List<MultipartFile> mediasFiles) throws IOException {
        List<MediaServiceModel> mediaServiceModel = mediaService.addMediasWithFileUpoladAndName(id, mediasNames, mediasFiles);
        return mediaServiceModel.stream().map(mapStructMapper::mediaServiceMediaDto).collect(Collectors.toList());
    }

    // Enregistrer un lot d'images en une seule fois.
    @PostMapping("/upload/{id}")
    public ResponseEntity<?> addMediasWithFileUpolad2(@PathVariable("id") Long id,
                                                      Optional<List<MultipartFile>> mediasFiles ) throws IOException {
        if(mediasFiles.isPresent()) {
            List<MediaEntity> mediaEntities = mediaService.addMediasWithFileUpolad(id, mediasFiles.get());
            if (mediaEntities != null) {
                return new ResponseEntity(mediaEntities, HttpStatus.CREATED);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La requête est vide, aucune donnée fournie.");
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    public MediaEntity edit(@PathVariable("id") Long id, @RequestBody final MediaEntity mediaEntity) {
        return mediaService.edit(mediaEntity);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Long id){
        return mediaService.delete(id);
    }

    @PostMapping("/new")
    public boolean fileUpload(MultipartFile mediaFile) throws IOException {
        /* ***************************************************** */
        /*                   TELEVERSE LE FICHIER                */
        /* ***************************************************** */
        // Obtient le nom de fichier original à partir de l'objet MultipartFile
        String originalFilename = mediaFile.getOriginalFilename();

        // Récupérer le répertoire public absolu
        Path publicDirectory = Paths.get(".", "public/upload").toAbsolutePath();

        // Créer le chemin complet du fichier à enregistrer dans le dossier upload du répertoire public
        Path filepath = Paths.get(publicDirectory.toString(), originalFilename);

        // Lire le contenu du fichier en bytes
        byte[] imageContent = mediaFile.getBytes();

        try (OutputStream os = Files.newOutputStream(filepath)) {
            // Écrire le contenu du fichier dans le chemin spécifié
            os.write(imageContent);
            return true;
        } catch (IOException e) {
            // En cas d'erreur lors de l'écriture du fichier, afficher la trace d'erreur
            e.printStackTrace();
            return false;
        }
    }

}
