package com.api.mushroom.controller.user;

import com.api.mushroom.service.user.CurrentUserService;
import com.api.mushroom.service.user.UserServiceModel;
import com.api.mushroom.service.utils.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/user")
public class CurrentUserController {

    // Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final CurrentUserService currentUserService;

    private final FileUploadService fileUploadService;


    @GetMapping
    public ResponseEntity<UserGetDTO> getCurrentUser(Authentication authentication) {

        // Récupérer l'email de l'utilisateur courant
        String email = authentication.getName();

        // Récupère l'utilisateur courant
        UserServiceModel userServiceModel = currentUserService.getCurrentUser(email);

        if(userServiceModel == null){
            // Si userServiceModel est null, renvoyer une ResponseEntity avec un code 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // MAPPAGE AVEC MapStruct
//        UserGetDTO userGetDTO = UserGetDtoMapper.INSTANCE.userServiceModelToUserGetDTO(userServiceModel);
        UserGetDTO userGetDTO = UserDtoMapper.INSTANCE.userServiceModelToUserGetDTO(userServiceModel);

        // Créer et renvoyer une ResponseEntity avec le UserGetDTO en tant que corps
        return ResponseEntity.ok(userGetDTO);

    }

    @PutMapping
    public boolean updateCurrentUser(@RequestParam("pseudo") String pseudo,
                                     @RequestParam("lastname") String lastname,
                                     @RequestParam("firstname") String firstname,
                                     @RequestPart("filename") Optional<MultipartFile> filename
                                     ) throws IOException {

        String newFilename = "";

        if(filename.isPresent()) {
            // Télécharger le fichier de média et obtient le nouveau nom de fichier
            newFilename = fileUploadService.fileUpload(filename.get(), "user/");
        }

                UserDTO userDTO = new UserDTO(pseudo,lastname,firstname,newFilename);

            // MAPPAGE AVEC MapStruct
            UserServiceModel userServiceModel = UserDtoMapper.INSTANCE.UserDTOToUserServiceModel(userDTO);

            // Persistence DB
            return currentUserService.updateCurrentUser(userServiceModel);


    }

//    @PutMapping
//    public boolean updateCurrentUser(@RequestBody UserDTO userDTO){
//
//        // MAPPAGE AVEC MapStruct
//        UserServiceModel userServiceModel = UserDtoMapper.INSTANCE.UserDTOToUserServiceModel(userDTO);
//
//        // Récupère l'utilisateur courant
//        return currentUserService.updateCurrentUser(userServiceModel);
//
//    }

}
