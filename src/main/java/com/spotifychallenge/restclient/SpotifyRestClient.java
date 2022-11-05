package com.spotifychallenge.restclient;

import com.spotifychallenge.dto.AlbumDto;
import com.spotifychallenge.restclient.dto.SpotifyAlbum;
import com.spotifychallenge.restclient.dto.SpotifySearch;
import com.spotifychallenge.restclient.mapper.SpotifyAlbumMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.spotifychallenge.constants.SpotifyConstants.BASE_URL;
import static com.spotifychallenge.constants.SpotifyConstants.GET_ALBUM;
import static com.spotifychallenge.constants.SpotifyConstants.GET_ALBUMS;

@Component
public class SpotifyRestClient {

    @Value("${token.secret}")
    private String token;

    private final WebClient webClient;

    public SpotifyRestClient() {
        this.webClient = WebClient.create(BASE_URL);
    }

    public List<AlbumDto> searchAlbums(String searchFilter) {
        return webClient.get().uri(GET_ALBUMS + searchFilter)
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToMono(SpotifySearch.class)
                .block()
                .getAlbums()
                .stream()
                .map(SpotifyAlbumMapper::mapToModel)
                .toList();
    }

    public AlbumDto searchAlbum(String albumId) {
        SpotifyAlbum spotifyAlbum = webClient.get().uri(GET_ALBUM + albumId)
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToMono(SpotifyAlbum.class)
                .block();

        return SpotifyAlbumMapper.mapToModel(spotifyAlbum);
    }
}
