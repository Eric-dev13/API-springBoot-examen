package com.api.mushroom.controller.user;

import com.api.mushroom.service.user.UserService;
import com.api.mushroom.service.user.UserServiceMapper;
import com.api.mushroom.service.user.UserServiceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/user")
public class UserCrudController {

    // Lombok va génèrer un constructeur avec un paramètre pour chaque constante (final)
    private final UserService userService;


    @GetMapping("/current")
    public ResponseEntity<UserGetDTO> getCurrentUser() {
        // Récupérer l'utilisateur courant
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Vérifier si l'utilisateur est authentifié
        if (authentication.isAuthenticated()) {
            String email = authentication.getName();
            UserServiceModel userServiceModel = userService.getCurrentUser(email);

            if(userServiceModel == null){
                // Si userServiceModel est null, renvoyer une ResponseEntity avec un code 404 Not Found
                return ResponseEntity.notFound().build();
            }

            // MAPPAGE AVEC MapStruct
            UserGetDTO userGetDTO = UserGetDtoMapper.INSTANCE.userServiceModelToUserGetDTO(userServiceModel);

            // Créer et renvoyer une ResponseEntity avec le UserGetDTO en tant que corps
            return ResponseEntity.ok(userGetDTO);

        } else {
            // L'utilisateur n'est pas authentifié, renvoyez une réponse appropriée (par exemple, un code d'erreur 401)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserGetDTO>update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO){

        // MAPPAGE AVEC MapStruct
        UserServiceModel userServiceModel = UserDtoMapper.INSTANCE.UserDTOToUserServiceModel(userDTO);

        userService.update(id, userServiceModel);
        return null;
    }

}
