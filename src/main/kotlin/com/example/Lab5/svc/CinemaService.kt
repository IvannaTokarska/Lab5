package com.example.Lab5.svc

import com.example.Lab5.dto.CinemaRequestDto
import com.example.Lab5.dto.CinemaResponseDto


interface CinemaService {

    fun getCinemaById(id: Long): CinemaResponseDto?
    fun createCinema(cinemaDto: CinemaRequestDto): CinemaResponseDto
    fun updateCinema(id: Long, cinemaDto: CinemaRequestDto): CinemaResponseDto?
    fun deleteCinema(id: Long): Boolean
}