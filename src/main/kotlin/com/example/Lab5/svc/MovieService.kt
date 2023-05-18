package com.example.Lab5.svc

import com.example.Lab5.dto.MovieRequestDto
import com.example.Lab5.dto.MovieResponseDto


interface MovieService {

    fun getMovieById(id: Long): MovieResponseDto?
    fun createMovie(movieDto: MovieRequestDto): MovieResponseDto
    fun updateMovie(id: Long, movieDto: MovieRequestDto): MovieResponseDto?
    fun deleteMovie(id: Long): Boolean
}