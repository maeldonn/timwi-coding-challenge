package com.spotifychallenge.restclient;

import com.spotifychallenge.exception.specific.ApiSearchException;
import com.spotifychallenge.model.Album;
import com.spotifychallenge.restclient.dto.SpotifyAlbum;
import com.spotifychallenge.restclient.dto.SpotifySearch;
import com.spotifychallenge.restclient.mapper.SpotifyAlbumMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Component
public class SpotifyRestClient {

    private final WebClient webClient;

    public SpotifyRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Album> searchAlbums(String searchFilter) {
        SpotifySearch search = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("type", "album")
                        .queryParam("q", searchFilter)
                        .build()
                )
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

    public Album searchAlbum(String id) {
        SpotifyAlbum spotifyAlbum = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/albums/{id}")
                        .build(id)
                )
                .retrieve()
                .onStatus(HttpStatus::isError, response -> Mono.error(new ApiSearchException()))
                .bodyToMono(SpotifyAlbum.class)
               .block();

        return Optional.ofNullable(spotifyAlbum)
                .map(SpotifyAlbumMapper::mapToModel)
                .orElseThrow(ApiSearchException::new);
    }
}
