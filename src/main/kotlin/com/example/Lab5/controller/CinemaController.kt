package com.example.Lab5.controller

import com.example.Lab5.dto.CinemaRequestDto
import com.example.Lab5.dto.CinemaResponseDto
import com.example.Lab5.svc.CinemaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/cinemas")
class CinemaController(private val cinemaService: CinemaService) {

    @GetMapping("/{id}")
    fun getCinemaById(@PathVariable id: Long): ResponseEntity<CinemaResponseDto> {
        val cinema = cinemaService.getCinemaById(id)
        return if (cinema != null) {
            ResponseEntity.ok(cinema)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    @PostMapping
    fun createCinema(@RequestBody cinemaRequest: CinemaRequestDto): ResponseEntity<CinemaResponseDto> {
        val createdCinema = cinemaService.createCinema(cinemaRequest)
        return ResponseEntity.created(URI.create("/cinemas/${createdCinema.id}")).body(createdCinema)
    }

    @PutMapping("/{id}")
    fun updateCinema(
        @PathVariable id: Long,
        @RequestBody cinemaRequest: CinemaRequestDto
    ): ResponseEntity<CinemaResponseDto> {
        val updatedCinema = cinemaService.updateCinema(id, cinemaRequest)
        return if (updatedCinema != null) {
            ResponseEntity.ok(updatedCinema)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    @DeleteMapping("/{id}")
    fun deleteCinema(@PathVariable id: Long): ResponseEntity<Boolean> {
        val deleted = cinemaService.deleteCinema(id)
        return if (deleted) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

}