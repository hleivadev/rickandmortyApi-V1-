package com.example.rickandmortyApi.controller;

import com.example.rickandmortyApi.model.CharacterCartoon;
import com.example.rickandmortyApi.service.CharacterService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Optional;

@CrossOrigin()
@RestController
@RequestMapping(value = "rickandmortyApi/V1/character", method = {RequestMethod.GET, RequestMethod.POST})
public class characterController {

    /**
     * Inyeccion de dependencias
     */
    private final CharacterService characterService;

    /**
     * Constructor
     *
     * @param characterService
     */
    public characterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/{characterId}")
    public ResponseEntity characterById(@PathVariable String characterId) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-Type", "application/json; charset=utf-8");
            Optional<CharacterCartoon> personage = Optional.ofNullable(characterService.getCharacterCartoon(characterId));
            if (personage.isEmpty()) {
                new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(personage, headers, HttpStatus.OK);

        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
            } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
            }
            throw e;
        }

    }


}
