package com.marvel.api.characters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marvel.api.characters.dto.ComicsCharacter;
import com.marvel.api.characters.dto.ComicsCharacterComic;
import com.marvel.api.characters.dto.ComicsCharacterUrl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.marvel.api.characters.ComicsCharactersController.PUBLIC_CHARACTERS_API;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ComicsCharactersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComicsCharacterService comicsCharacterService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getFirstPage_defaultPageSize() throws Exception {
        List<ComicsCharacter> content = Arrays.asList(getComicsCharacter(1), getComicsCharacter(2));
        PageRequest pageable = PageRequest.of(0, 2);
        PageImpl<ComicsCharacter> page = new PageImpl<>(content, pageable, 3);

        given(comicsCharacterService.getCharacters(pageable)).willReturn(page);

        String jsonContent = mapper.writeValueAsString(page);
        ResultActions perform = this.mockMvc.perform(get(PUBLIC_CHARACTERS_API));
        perform
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));

        for (int i = 0; i < 2; i++) {
            perform.andExpect(jsonPath("$.content[" + i + "]", hasKey("id")));
            perform.andExpect(jsonPath("$.content[" + i + "].id", equalTo(i + 1)));
            perform.andExpect(jsonPath("$.content[" + i + "]", hasKey("name")));
            perform.andExpect(jsonPath("$.content[" + i + "]", hasKey("description")));
            perform.andExpect(jsonPath("$.content[" + i + "]", hasKey("modified")));
            perform.andExpect(jsonPath("$.content[" + i + "]", hasKey("resourceURI")));
            perform.andExpect(jsonPath("$.content[" + i + "]", hasKey("thumbnail")));
            perform.andExpect(jsonPath("$.content[" + i + "]", hasKey("comics")));
            perform.andExpect(jsonPath("$.content[" + i + "]", hasKey("stories")));
            perform.andExpect(jsonPath("$.content[" + i + "]", hasKey("events")));
            perform.andExpect(jsonPath("$.content[" + i + "]", hasKey("urls")));
        }
    }

    @Test
    public void getLastPage_defaultPageSize() throws Exception {
        PageRequest pageable = PageRequest.of(1, 2);
        List<ComicsCharacter> content = Arrays.asList(getComicsCharacter(3));
        PageImpl<ComicsCharacter> page = new PageImpl<>(content, pageable, 3);
        given(comicsCharacterService.getCharacters(pageable)).willReturn(page);

        String jsonContent = mapper.writeValueAsString(page);
        this.mockMvc.perform(get(PUBLIC_CHARACTERS_API)
                .param("page", "1")
        )
                .andExpect(content().json(jsonContent));
    }

    @Test
    public void getAll_pageSize_10() throws Exception {
        PageRequest pageable = PageRequest.of(0, 10);
        List<ComicsCharacter> content = Arrays.asList(getComicsCharacter(1), getComicsCharacter(2), getComicsCharacter(3));
        PageImpl<ComicsCharacter> page = new PageImpl<>(content, pageable, 3);
        given(comicsCharacterService.getCharacters(pageable)).willReturn(page);

        String jsonContent = mapper.writeValueAsString(page);
        this.mockMvc.perform(get(PUBLIC_CHARACTERS_API)
                .param("size", "10")
        )
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));
    }

    @Test
    public void getPage_100_empty_content() throws Exception {
        PageRequest pageable = PageRequest.of(100, 2);
        List<ComicsCharacter> content = new ArrayList<>();
        PageImpl<ComicsCharacter> page = new PageImpl<>(content, pageable, 3);
        given(comicsCharacterService.getCharacters(pageable)).willReturn(page);

        String jsonContent = mapper.writeValueAsString(page);
        this.mockMvc.perform(get(PUBLIC_CHARACTERS_API)
                .param("page", "100")
        )
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));
    }

    @Test
    public void getDetailsById_1010699() throws Exception {
//        “id”: 1010699,
//        “name”: "Aaron Stack",
//        “urls”: [
//                {
//                    "type": "detail",
//                    "url": "http://marvel.com/comics/characters/1010699/aaron_stack"
//                },
//        ...
//        ],
//                "comics": {
//                    "available": 14,
//        ...
//                }

        long id = 1010699L;

        ComicsCharacter comicsCharacter = new ComicsCharacter();
        comicsCharacter.setId(id);
        comicsCharacter.setName("Aaron Stack");
        comicsCharacter.setUrls(Arrays.asList(new ComicsCharacterUrl("detail", "http://marvel.com/comics/characters/1010699/aaron_stack")));
        comicsCharacter.setComics(new ComicsCharacterComic(14));

        given(comicsCharacterService.getCharacter(id)).willReturn(comicsCharacter);

        String jsonContent = mapper.writeValueAsString(comicsCharacter);
        ResultActions perform = this.mockMvc.perform(get(PUBLIC_CHARACTERS_API + "/" + id));
        perform
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));

        perform.andExpect(jsonPath("$.id", equalTo(1010699)));
        perform.andExpect(jsonPath("$.name", equalTo("Aaron Stack")));
        perform.andExpect(jsonPath("$.urls[0].type", equalTo("detail")));
        perform.andExpect(jsonPath("$.urls[0].url", equalTo("http://marvel.com/comics/characters/1010699/aaron_stack")));
        perform.andExpect(jsonPath("$.comics.available", equalTo(14)));
    }

    @Test
    public void detailsDoesNotExist_404() throws Exception {
        long id = 999;

        given(comicsCharacterService.getCharacter(id)).willReturn(null);

        ResultActions perform = this.mockMvc.perform(get(PUBLIC_CHARACTERS_API + "/" + id));
        perform
                .andExpect(status().isNotFound());
    }


    private ComicsCharacter getComicsCharacter(long id) {
        ComicsCharacter comicsCharacter = new ComicsCharacter();
        comicsCharacter.setId(id);
        return comicsCharacter;
    }

}
