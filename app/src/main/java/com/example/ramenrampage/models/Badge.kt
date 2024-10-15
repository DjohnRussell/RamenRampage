package com.example.ramenrampage.models

import androidx.compose.ui.util.trace

data class Badge(
    val imageUrl: String,  // URL to the badge image
    val name: String,
    val description: String,
    val achieved: Boolean,  // Whether the user has unlocked the badge
    val dateEarned: String? = null
)


val badgesList = listOf(
    Badge(
        imageUrl = "https://example.com/first-checkin-badge.png",
        name = "First Check-In",
        description = "Earned after your first noodle check-in",
        achieved = false,
        dateEarned = "October 10, 2024"
    ),
    Badge(
        imageUrl = "https://example.com/noodle-master-badge.png",
        name = "Noodle Master",
        description = "Awarded after 50 noodle reviews",
        achieved = false
    ),
    Badge(
        imageUrl = "https://example.com/ramen-connoisseur-badge.png",
        name = "Ramen Connoisseur",
        description = "Awarded after reviewing 100 noodles",
        achieved = false
    ),
    Badge(
        imageUrl = "https://example.com/spicy-lover-badge.png",
        name = "Spicy Lover",
        description = "Awarded after reviewing 10 spicy noodle varieties",
        achieved = false
        ),
    Badge(
        imageUrl = "https://example.com/international-taster-badge.png",
        name = "International Taster",
        description = "Awarded for reviewing noodles from 5 different countries",
        achieved = false
        ),
    Badge(
        imageUrl = "https://example.com/noodle-photographer-badge.png",
        name = "Noodle Photographer",
        description = "Earned for uploading 10 noodle photos",
        achieved = false
        ),
    Badge(
        imageUrl = "https://example.com/night-owl-badge.png",
        name = "Night Owl",
        description = "Awarded for checking in a noodle after midnight",
        achieved = true
        ),
    Badge(
        imageUrl = "https://example.com/noodle-adventurer-badge.png",
        name = "Noodle Adventurer",
        description = "Awarded for trying 20 unique noodle varieties",
        achieved = true
        ),
    Badge(
        imageUrl = "https://example.com/review-marathon-badge.png",
        name = "Review Marathon",
        description = "Awarded after leaving 5 reviews in a single day",
        achieved = true
        ),


)

