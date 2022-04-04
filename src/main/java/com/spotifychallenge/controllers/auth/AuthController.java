package com.spotifychallenge.controllers.auth;

import com.spotifychallenge.service.auth.SpotifyAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * REST endpoints for the spotify login
 */
@CrossOrigin
@RestController
@RequestMapping("/")
public class AuthController {

    // SERVICES
    private final SpotifyAuthService spotifyAuthService;

    public AuthController(SpotifyAuthService spotifyAuthService) {
        this.spotifyAuthService = spotifyAuthService;
    }

    /**
     * Callback
     *
     * @return the link to log in
     */
    @GetMapping("/callback")
    public ResponseEntity<Void> callback(@RequestParam String code) {

        // Generate access token
        spotifyAuthService.callback(code);

        // Redirect user to the frontend
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create("http://localhost:3000"))
                .build();
    }
}
