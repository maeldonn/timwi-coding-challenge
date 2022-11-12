package com.spotifychallenge.controllers.v1.api;

import com.spotifychallenge.model.Album;
import com.spotifychallenge.restclient.SpotifyRestClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AlbumControllerIT {

    @MockBean
    private SpotifyRestClient restClient;

    private final MockMvc mockMvc;

    public AlbumControllerIT(WebApplicationContext context) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("it should respond a list of albums")
    void getAlbumsTest() throws Exception {
        // GIVEN
        String searchFilter = "Nirvana";
        Album albumFromSpotify = Album.builder()
                .id("1To7kv722A8SpZF789MZy7")
                .title("MTV Unplugged In New York")
                .artists("Nirvana")
                .image("https://i.scdn.co/image/ab67616d0000b27336c5417732e53e23cb219246")
                .releaseDate(LocalDate.of(1994, 11, 1))
                .duration(14)
                .build();

        // MOCK
        when(restClient.searchAlbums(searchFilter)).thenReturn(List.of(albumFromSpotify));

        // WHEN
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/albums")
                        .queryParam("searchFilter", searchFilter)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // THEN
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    @DisplayName("it should respond a liste of personal albums")
    void getPersonalAlbumsTest() throws Exception {
        // WHEN
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/albums/personal")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // THEN
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    @DisplayName("it should add an album to personal list")
    void addAlbumToPersonalListTest() throws Exception {
        // GIVEN
        Album albumFromSpotify = Album.builder()
                .id("5JY3b9cELQsoG7D5TJMOgw")
                .title("Multitude")
                .artists("Stromae")
                .image("https://i.scdn.co/image/ab67616d0000b27331d6b27ffaa0b2f89234698a")
                .releaseDate(LocalDate.of(2022, 3, 3))
                .duration(12)
                .build();

        // MOCK
        when(restClient.searchAlbum(any())).thenReturn(albumFromSpotify);

        // WHEN
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/albums/" + "5JY3b9cELQsoG7D5TJMOgw")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // THEN
        result.andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("5JY3b9cELQsoG7D5TJMOgw"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Multitude"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artists").value("Stromae"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.image").value("https://i.scdn.co/image/ab67616d0000b27331d6b27ffaa0b2f89234698a"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseDate").value("2022-03-03"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.duration").value("12"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.personal").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.favorite").value("false"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tags").isEmpty());
    }

    @Test
    @DisplayName("it should remove an album from personal list")
    void removeAlbumFromPersonalListTest() throws Exception {
        // WHEN
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/albums/" + "1To7kv722A8SpZF789MZy7")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // THEN
        result.andExpect(status().isOk());
    }

    @Test
    @DisplayName("it should toggle favorite on an album")
    void toggleFavoriteAlbumTest() throws Exception {
        // GIVEN
        String album = "{\"id\": \"6olE6TJLqED3rqDCT0FyPh\", \"title\": \"Nevermind (Remastered)\", \"artists\": \"Nirvana\", \"image\": \"https://i.scdn.co/image/ab67616d0000b273fbc71c99f9c1296c56dd51b6\", \"releaseDate\": \"1991-09-26\", \"duration\": \"13\", \"personal\": \"true\", \"favorite\": \"false\"}";

        // WHEN
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/albums")
                        .content(album)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

        // THEN
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("6olE6TJLqED3rqDCT0FyPh"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.personal").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.favorite").value("true"));
    }
}
