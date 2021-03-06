package com.spotifychallenge.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spotifychallenge.dto.model.AlbumDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.spotifychallenge.constants.SpotifyConstants.*;

/**
 * Spotify REST Client
 */
@Service
public class SpotifyRestClient {

    @Value( "${token.secret}" )
    private String token;

    private final WebClient webClient;

    public SpotifyRestClient() {
        this.webClient = WebClient.create(BASE_URL);
    }

    /**
     * Get albums
     *
     * @param searchFilter the filter
     * @return 20 albums
     * @throws IOException
     */
    public List<AlbumDto> searchAlbums(String searchFilter) throws IOException {

        // Get JSON response
        String json = webClient.get().uri(GET_ALBUMS + searchFilter)
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Get the array of album in JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonNode = objectMapper.readTree(json).get("albums").get("items").toString();

        // Convert to an array of Album
        return Arrays.asList(objectMapper.readValue(jsonNode, AlbumDto[].class));
    }

    /**
     * Get an album
     *
     * @param albumId the album id
     * @return The album
     * @throws IOException if the id is invalid
     */
    public AlbumDto searchAlbum(String albumId) throws IOException {

        // Get JSON response
        String json = webClient.get().uri(GET_ALBUM + albumId)
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Convert to an array of Album
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, AlbumDto.class);
    }
}
