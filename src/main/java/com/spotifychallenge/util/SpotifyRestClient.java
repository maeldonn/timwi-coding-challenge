package com.spotifychallenge.util;

import com.spotifychallenge.dto.mapper.spotify.SpotifyMapper;
import com.spotifychallenge.dto.model.album.AlbumDto;
import com.spotifychallenge.dto.model.spotify.SpotifyAlbumDto;
import com.spotifychallenge.dto.model.spotify.SpotifySearchDto;
import com.spotifychallenge.dto.model.spotify.Token;
import com.spotifychallenge.exception.LoginException;
import com.spotifychallenge.exception.SpotifyChallengeRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

/**
 * Spotify REST Client
 */
// TODO: Externaliser les paramètres des appels api dans un fichier externe
@Service
public class SpotifyRestClient {

    @Value("${spotify.client-id}")
    public String CLIENT_ID;

    @Value("${spotify.client-secret}")
    public String CLIENT_SECRET;

    @Value("${spotify.base-url}")
    public String BASE_URL;

    private Token token;

    private final WebClient webClient;

    public SpotifyRestClient() {
        this.webClient = WebClient.create(BASE_URL);
    }

    /**
     * Callback
     *
     * @param code TODO
     */
    public void callback(String code) {

        // TODO: Renommer generateToken plutôt et gérer tous les cas dedans
        // Generate access token
        getAccessToken(code);
    }

    /**
     * Get access token
     */
    private String getToken() {

        // Check if token exist
        // TODO: Refactor this part
        if (token == null) {
            try {
                throw new LoginException(
                        "User is not logged in",
                        "https://accounts.spotify.com/authorize" +
                        "?client_id=" + CLIENT_ID +
                        "&response_type=" + "code" +
                        "&redirect_uri=" + URLEncoder.encode("http://localhost:8080/callback", StandardCharsets.UTF_8.toString())
                );
            } catch (UnsupportedEncodingException e) {
                throw new SpotifyChallengeRequestException(e.getMessage(), e);
            }
        }

        // Refresh token if expired
        if (token.isExpired()) {
            return getRefreshToken();
        }

        return token.getAccessToken();
    }

    /**
     * Get access token
     */
    private String getAccessToken(String code) {

        token = webClient.post()
                .uri("https://accounts.spotify.com/api/token")
                .body(BodyInserters
                        .fromFormData("grant_type", "authorization_code")
                        .with("code", code)
                        .with("redirect_uri", "http://localhost:8080/callback"))
                .headers(h -> h.setBasicAuth(Base64.getEncoder().encodeToString(String.format("%s:%s", CLIENT_ID, CLIENT_SECRET).getBytes(StandardCharsets.UTF_8))))
                .headers(h -> h.setContentType(MediaType.APPLICATION_FORM_URLENCODED))
                .retrieve()
                .bodyToMono(Token.class)
                .block();

        // TODO: Démarrer le timer

        return token.getAccessToken();
    }

    /**
     * Get refresh token
     */
    private String getRefreshToken() {
        token = webClient.post()
                .uri("https://accounts.spotify.com/api/token")
                .body(BodyInserters
                        .fromFormData("grant_type", "refresh_token")
                        .with("refresh_token", token.getRefreshToken()))
                .headers(h -> h.setBasicAuth(Base64.getEncoder().encodeToString(String.format("%s:%s", CLIENT_ID, CLIENT_SECRET).getBytes(StandardCharsets.UTF_8))))
                .headers(h -> h.setContentType(MediaType.APPLICATION_FORM_URLENCODED))
                .retrieve()
                .bodyToMono(Token.class)
                .block();

        // TODO: Démarrer le timer
        token.getTimer().schedule(new TimerTask() {
            @Override
            public void run() {
                getRefreshToken();
            }
        }, token.getExpiresIn() * 1000);
        
        return token.getAccessToken();
    }

    /**
     * Get albums
     *
     * @param searchQuery the filter
     * @return 20 albums
     */
    public List<AlbumDto> searchAlbums(String searchQuery) {

        // Get JSON response
        SpotifySearchDto spotifySearchDto = webClient
                .get()
                .uri("https://api.spotify.com/v1/search?type=album&market=FR&q=" + searchQuery)
//                .uri(uriBuilder -> uriBuilder
//                        .path("/search")
//                        .queryParam("type", "album")
//                        .queryParam("market", "FR")
//                        .queryParam("q", searchQuery).build())
                .headers(h -> h.setBearerAuth(getToken()))
                .retrieve().onStatus(HttpStatus::is4xxClientError, response -> null)
                .onStatus(HttpStatus::is5xxServerError, response -> null)
                .bodyToMono(SpotifySearchDto.class)
                .block();

        // TODO: Handle the case where the response is null
        // TODO : Handle it in onStatus
        if (spotifySearchDto == null) {
            return Collections.emptyList();
        }

        // Convert to a list of AlbumDto and return it
        return spotifySearchDto.getAlbums().getItems().stream()
                .map(SpotifyMapper::toAlbumDto)
                .collect(Collectors.toList());
    }

    /**
     * Get an album
     *
     * @param albumId the album id
     * @return The album
     */
    public AlbumDto getAlbum(String albumId) {

        // Get JSON response
        SpotifyAlbumDto spotifyAlbumDto = webClient.get()
                .uri("https://api.spotify.com/v1/albums/" + albumId)
//                .uri(uriBuilder -> uriBuilder
//                        .path("/albums")
//                        .query(albumId).build())
                .headers(h -> h.setBearerAuth(getToken()))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> null)
                .onStatus(HttpStatus::is5xxServerError, response -> null)
                .bodyToMono(SpotifyAlbumDto.class)
                .block();

        // TODO: Handle the case where the response is null && Handle it in onStatus
        if (spotifyAlbumDto == null || spotifyAlbumDto.getAlbumId() == null) {
            return null;
        }

        // Convert to an AlbumDto and return it
        return SpotifyMapper.toAlbumDto(spotifyAlbumDto);
    }
}
