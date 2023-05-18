package com.example.Lab5.controller

import com.example.Lab5.dto.MovieRequestDto
import com.example.Lab5.dto.MovieResponseDto
import com.example.Lab5.svc.MovieService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/movies")
class MovieController(private val movieService: MovieService) {
    @GetMapping("/{id}")
    fun getMovieById(@PathVariable id: Long): ResponseEntity<MovieResponseDto> {
        val movie = movieService.getMovieById(id)
        return if (movie != null) {
            ResponseEntity.ok(movie)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createMovie(@RequestBody movieDto: MovieRequestDto): ResponseEntity<MovieResponseDto> {
        val createdMovie = movieService.createMovie(movieDto)
        return ResponseEntity.created(URI.create("/movies/${createdMovie.id}")).body(createdMovie)
    }
    @PutMapping("/{id}")
    fun updateMovie(@PathVariable id: Long, @RequestBody movieDto: MovieRequestDto): ResponseEntity<MovieResponseDto> {
        val updatedMovie = movieService.updateMovie(id, movieDto)
        return if (updatedMovie != null) {
            ResponseEntity.ok(updatedMovie)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMovie(@PathVariable id: Long): ResponseEntity<Boolean> {
        val isDeleted = movieService.deleteMovie(id)
        return if (isDeleted) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}