package com.example.scrumblegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scrumblegame.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: GameViewModel = viewModel()
            GameScreen(viewModel)
        }
    }
}

@Composable
fun GameScreen(viewModel: GameViewModel) {
    var userGuess by remember { mutableStateOf("") }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Score: ${viewModel.getScore()}", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Devinez le mot : ${viewModel.currentScrambledWord}", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = userGuess,
            onValueChange = { userGuess = it },
            label = { Text("Votre réponse") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            isCorrect = viewModel.checkGuess(userGuess)
            if (isCorrect == true) {
                viewModel.nextWord()
            }
            userGuess = ""
        }) {
            Text("Soumettre")
        }
        isCorrect?.let {
            Text(
                text = if (it) "Correct!" else "Incorrect! Essayez encore.",
                color = if (it) MaterialTheme.colorScheme
                    .primary else MaterialTheme.colorScheme.error
            )
        }
    }
}
