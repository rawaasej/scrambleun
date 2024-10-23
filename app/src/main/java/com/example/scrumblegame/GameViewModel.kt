package com.example.scrumblegame.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel : ViewModel() {
    private val words = listOf(
        "android", "kotlin", "compose", "scramble", "game"
    )

    private var currentWordIndex = Random.nextInt(words.size)
    private var score = 0

    val currentWord: String get() = words[currentWordIndex]
    val currentScrambledWord: String get() = currentWord.toList().shuffled().joinToString("")

    fun checkGuess(guess: String): Boolean {
        return if (guess.equals(currentWord, ignoreCase = true)) {
            score++
            true
        } else {
            false
        }
    }

    fun nextWord() {
        currentWordIndex = Random.nextInt(words.size)
    }

    fun getScore(): Int {
        return score
    }
}
