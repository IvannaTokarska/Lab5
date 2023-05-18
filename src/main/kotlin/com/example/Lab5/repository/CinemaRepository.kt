package com.example.Lab5.repository

import com.example.Lab5.entity.Cinema
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CinemaRepository : JpaRepository<Cinema, Long>
