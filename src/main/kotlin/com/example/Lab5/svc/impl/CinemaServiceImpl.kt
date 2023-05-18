package com.example.Lab5.svc.impl

import com.example.Lab5.dto.CinemaRequestDto
import com.example.Lab5.dto.CinemaResponseDto
import com.example.Lab5.entity.Cinema
import com.example.Lab5.repository.CinemaRepository
import com.example.Lab5.svc.CinemaService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class CinemaServiceImpl(
    private val cinemaRepository: CinemaRepository
) : CinemaService {

    @PreAuthorize("hasRole('EDITOR')")
    override fun createCinema(cinemaRequest: CinemaRequestDto): CinemaResponseDto {
        val cinemas = Cinema (name = cinemaRequest.name, address = cinemaRequest.address, openingDate = LocalDate.EPOCH, seatCount = cinemaRequest.seatCount, screenCount = cinemaRequest.screenCount, soundTechnology = cinemaRequest.soundTechnology, is3D = cinemaRequest.is3D)
        val savedCinemas = cinemaRepository.save(cinemas)
        return CinemaResponseDto.fromEntity(savedCinemas)
    }
    @PreAuthorize("hasAnyRole('EDITOR', 'VIEWER')")
    override fun getCinemaById(id: Long): CinemaResponseDto {
        val cinemas = cinemaRepository.findById(id).orElseThrow()
        return CinemaResponseDto.fromEntity(cinemas)
    }
    @PreAuthorize("hasRole('EDITOR')")
    override fun updateCinema(id: Long, cinemaRequest: CinemaRequestDto): CinemaResponseDto {
        val cinemas = cinemaRepository.findById(id).orElseThrow()
        cinemas.name = cinemaRequest.name
        cinemas.address = cinemaRequest.address
        val updateCinemas = cinemaRepository.save(cinemas)
        return CinemaResponseDto.fromEntity(updateCinemas)
    }
    @PreAuthorize("hasRole('EDITOR')")
    override fun deleteCinema(id: Long): Boolean {
        return if (cinemaRepository.existsById(id)) {
            cinemaRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}