package com.mycompany.movieapi.runner;

import com.mycompany.movieapi.model.AuthProvider;
import com.mycompany.movieapi.model.Movie;
import com.mycompany.movieapi.model.User;
import com.mycompany.movieapi.security.WebSecurityConfig;
import com.mycompany.movieapi.service.MovieService;
import com.mycompany.movieapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UserService userService;
    private final MovieService movieService;

    public DatabaseInitializer(UserService userService, MovieService movieService) {
        this.userService = userService;
        this.movieService = movieService;
    }

    @Override
    public void run(String... args) {
        if (!userService.getUsers().isEmpty()) {
            return;
        }
        users.forEach(userService::saveUser);
        movies.forEach(movieService::saveMovie);
        log.info("Database initialized");
    }

    private final List<User> users = Arrays.asList(
            new User("admin", "admin", "Admin", "admin@mycompany.com", WebSecurityConfig.ADMIN, null, AuthProvider.local, "1"),
            new User("user", "user", "User", "user@mycompany.com", WebSecurityConfig.USER, null, AuthProvider.local, "1")
    );

    private final List<Movie> movies = Collections.singletonList(
            new Movie("abc", "I Tonia")
    );

}
