package com.marvel.api.characters;

import com.marvel.api.characters.dto.ComicsCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComicsCharacterService {

    private final ComicsCharactersRepository comicsCharactersRepository;

    @Autowired
    public ComicsCharacterService(ComicsCharactersRepository comicsCharactersRepository) {
        this.comicsCharactersRepository = comicsCharactersRepository;
    }

    public Page<ComicsCharacter> getCharacters(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int offset = Long.valueOf(pageable.getOffset()).intValue();

        List<ComicsCharacter> characters = new ArrayList<>();

        List<ComicsCharacter> comicsCharacters = comicsCharactersRepository.getComicsCharacters();
        int total = comicsCharacters.size();
        if (total > offset) {
            int toIndex = Math.min(offset + pageSize, total);
            characters = comicsCharacters.subList(offset, toIndex);
        }

        return new PageImpl<>(characters, pageable, total);
    }

    public ComicsCharacter getCharacter(Long id) {
        return comicsCharactersRepository.getCharacter(id);
    }
}
