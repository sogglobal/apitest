package com.marvel.api.characters;

import com.marvel.api.characters.dto.ComicsCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComicsCharactersController {

    public static final String PUBLIC_CHARACTERS_API = "/v1/public/characters";

    private final ComicsCharacterService comicsCharacterService;

    @Autowired
    public ComicsCharactersController(ComicsCharacterService comicsCharacterService) {
        this.comicsCharacterService = comicsCharacterService;
    }

    @RequestMapping(value = PUBLIC_CHARACTERS_API, method = RequestMethod.GET)
    public ResponseEntity<Page<ComicsCharacter>> getComicsCharacters(@PageableDefault(size = 2) Pageable pageable) {

        Page<ComicsCharacter> characters = comicsCharacterService.getCharacters(pageable);

        return ResponseEntity.ok(characters);
    }

    @RequestMapping(value = PUBLIC_CHARACTERS_API + "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ComicsCharacter> getComicsCharacterById(@PathVariable Long id) {

        ComicsCharacter characters = comicsCharacterService.getCharacter(id);
        if (characters != null) {
            return ResponseEntity.ok(characters);
        }
        return ResponseEntity.notFound().build();
    }

}
