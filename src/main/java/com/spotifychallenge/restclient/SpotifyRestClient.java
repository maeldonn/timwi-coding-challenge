package com.spotifychallenge.restclient;

import com.spotifychallenge.exception.specific.ApiSearchException;
import com.spotifychallenge.model.Album;
import com.spotifychallenge.restclient.dto.SpotifyAlbum;
import com.spotifychallenge.restclient.dto.SpotifySearch;
import com.spotifychallenge.restclient.mapper.SpotifyAlbumMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

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

    public List<Album> searchAlbums(String searchFilter) {
        SpotifySearch search = webClient.get().uri(GET_ALBUMS + searchFilter)
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> Mono.error(new ApiSearchException()))
                .bodyToMono(SpotifySearch.class)
                .block();

        return Optional.ofNullable(search)
                .map(SpotifySearch::getAlbums)
                .orElseThrow(ApiSearchException::new)
                .stream()
                .map(SpotifyAlbumMapper::mapToModel)
                .toList();
    }

    public Album searchAlbum(String albumId) {
        SpotifyAlbum spotifyAlbum = webClient.get().uri(GET_ALBUM + albumId)
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .onStatus(HttpStatus::isError, response -> Mono.error(new ApiSearchException()))
                .bodyToMono(SpotifyAlbum.class)
               .block();

        return Optional.ofNullable(spotifyAlbum)
                .map(SpotifyAlbumMapper::mapToModel)
                .orElseThrow(ApiSearchException::new);
    }
}
