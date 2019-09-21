package com.marvel.api.characters;

import com.marvel.api.characters.dto.ComicsCharacter;
import com.marvel.api.characters.dto.ComicsCharacterComic;
import com.marvel.api.characters.dto.ComicsCharacterUrl;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.marvel.api.characters.ComicsCharactersController.PUBLIC_CHARACTERS_API;

@Component
public class ComicsCharactersRepository {

    public static final int TOTAL_ITEMS = 5;

    private List<ComicsCharacter> comicsCharacters;

    public ComicsCharactersRepository() {
        this.comicsCharacters = initComicsCharacters();
    }

    public List<ComicsCharacter> getComicsCharacters() {
        return comicsCharacters;
    }

    public ComicsCharacter getCharacter(Long id) {
        return this.comicsCharacters.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private List<ComicsCharacter> initComicsCharacters() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS zzz");

        List<ComicsCharacter> characters = new ArrayList<>();
        for (int i = 0; i < TOTAL_ITEMS; i++) {
            Long id = Long.valueOf(i + 1);
            characters.add(createComicsCharacter(dateFormat, i, id));
        }
        return characters;
    }

    private ComicsCharacter createComicsCharacter(DateFormat dateFormat, int i, Long id) {
        return new ComicsCharacter(
                id,
                "Character name " + id,
                "Character description " + id,
                dateFormat.format(new Date(1560000000000L + 100000000L * i)),
                PUBLIC_CHARACTERS_API + "/" + id,
                "/some/thumbnail/" + id,
                new ComicsCharacterComic(10),
                "Character stories " + id,
                "Character events " + id,
                Arrays.asList(new ComicsCharacterUrl("detail", "/some/urls/" + id))
        );
    }
}
