package com.example.Lab5.dto


import com.example.Lab5.entity.Cinema
import com.example.Lab5.entity.Movie
import java.time.LocalDate

data class ExampleRequest(
    var name: String
)

data class ExampleResponse(
    var id: Long,
    var name: String
)

data class CinemaRequestDto(
    val name: String,
    val address: String,
    val openingDate: String,
    val seatCount: Int,
    val screenCount: Int,
    val soundTechnology: String,
    val is3D: Boolean,
    val movies: List<Long>

)

data class CinemaResponseDto(
    val id: Long,
    val name: String,
    val address: String,
    val openingDate: LocalDate,
    val seatCount: Int,
    val screenCount: Int,
    val soundTechnology: String,
    val is3D: Boolean,
    val movies: List<MovieResponseDto>
)  {
    companion object {
        fun fromEntity(cinema: Cinema): CinemaResponseDto {

            val movieDtos = cinema.movies.map { MovieResponseDto.fromEntity(it) }

            return CinemaResponseDto(
                id = cinema.id,
                name = cinema.name,
                address = cinema.address,
                openingDate = cinema.openingDate,
                seatCount = cinema.seatCount,
                screenCount = cinema.screenCount,
                soundTechnology = cinema.soundTechnology,
                is3D = cinema.is3D,
                movies = movieDtos
            )
        }
    }
}

data class MovieRequestDto(
    val id: Long,
    val title: String,
    val country: String,
    val filmCompany: String,
    val duration: Int,
    val budget: Double,
    val premiereDate: LocalDate,
    val isRestricted: Boolean
)

data class MovieResponseDto(
    val id: Long,
    val title: String,
    val country: String,
    val filmCompany: String,
    val duration: Int,
    val budget: Double,
    val premiereDate: LocalDate,
    val isRestricted: Boolean
) {
    companion object {
        fun fromEntity(movie: Movie): MovieResponseDto {
            return MovieResponseDto(
                id = movie.id,
                title = movie.title,
                country = movie.country,
                filmCompany = movie.filmCompany,
                duration = movie.duration,
                budget = movie.budget,
                premiereDate = movie.premiereDate,
                isRestricted = movie.isRestricted
            )
        }
    }
}