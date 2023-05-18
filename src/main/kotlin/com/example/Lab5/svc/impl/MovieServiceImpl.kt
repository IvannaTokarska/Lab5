package com.example.Lab5.svc.impl

import com.example.Lab5.dto.MovieRequestDto
import com.example.Lab5.dto.MovieResponseDto
import com.example.Lab5.entity.Movie
import com.example.Lab5.repository.MovieRepository
import com.example.Lab5.svc.MovieService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service


@Service
class MovieServiceImpl(
    private val movieRepository: MovieRepository
) : MovieService {

    @PreAuthorize("hasRole('EDITOR')")
    override fun createMovie(movieRequest: MovieRequestDto): MovieResponseDto {
        val movies = Movie (title = movieRequest.title, country = movieRequest.country, filmCompany = movieRequest.filmCompany, duration = movieRequest.duration, budget = movieRequest.budget, premiereDate = movieRequest.premiereDate, isRestricted = movieRequest.isRestricted)
        val savedMovies = movieRepository.save(movies)
        return MovieResponseDto.fromEntity(savedMovies)
    }
    @PreAuthorize("hasAnyRole('EDITOR', 'VIEWER')")
    override fun getMovieById(id: Long): MovieResponseDto {
        val movies = movieRepository.findById(id).orElseThrow()
        return MovieResponseDto.fromEntity(movies)
    }
    @PreAuthorize("hasRole('EDITOR')")
    override fun updateMovie(id: Long, movieRequest: MovieRequestDto): MovieResponseDto {
        val movies = movieRepository.findById(id).orElseThrow()
        movies.title = movieRequest.title
        movies.country = movieRequest.country
        val updateMovies = movieRepository.save(movies)
        return MovieResponseDto.fromEntity(updateMovies)
    }
    @PreAuthorize("hasRole('EDITOR')")
    override fun deleteMovie(id: Long): Boolean {
        return if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}