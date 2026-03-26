package com.example.praktam_2457051009.model

import com.example.praktam_2457051009.R

data class Sprint(
    val jarak: String,
    val deskripsi: String,
    val target: String,
    val imageRes: Int
)

object SprintSource {
    val dummySprint = listOf(
        Sprint("100m", "Interval 6x", "Target 12 detik", R.drawable.sprint1),
        Sprint("200m", "Interval 5x", "Target 25 detik", R.drawable.sprint2),
        Sprint("Strength", "Lower Body Training", "Squat & Deadlift", R.drawable.strength3)
    )
}