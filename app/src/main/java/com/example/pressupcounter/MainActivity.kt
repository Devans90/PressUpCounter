package com.example.pressupcounter

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.temporal.ChronoUnit


class MainActivity : ComponentActivity() {

    // Companion object for messages
    companion object {
        val motivationalMessages = listOf(
            "Push like your life depends on it! Or at least like your dignity does.",
            "If pain is weakness leaving the body, you’re about to be invincible.",
            "You’ve got this! Or maybe you don’t, but hey, at least you're trying!",
            "Lifting yourself up is the first step, putting the donut down is the second.",
            "Go hard or go home! Oh wait, you’re already home. Well, go harder!",
            "You’re basically one push-up away from becoming a Greek god, probably.",
            "Squeeze those arms like they owe you rent!",
            "Just think, every push-up gets you closer to not looking like a potato.",
            "Is that sweat or your will to succeed evaporating? Keep going!",
            "Channel the strength of a thousand squirrels on caffeine!",
            "By the end of this, you’ll either be jacked or emotionally broken. Let’s go!",
            "Push-ups don’t count unless you dramatically grunt with each one.",
            "If you can lift your body, you can lift your soul. No refunds on this advice.",
            "Who needs wings when you can just have arms of steel? Aim for steel!",
            "You’re not a couch potato anymore; you’re an aggressively determined yam.",
            "Push harder or your arms might unionize and demand a day off.",
            "Remember, every rep is a step toward immortality. Or just nicer biceps.",
            "Feel the burn! That’s either progress or just your body screaming for mercy.",
            "Push through! You’ll be so strong you can lift existential dread next.",
            "The only thing standing between you and greatness is gravity. Fight it!",
            "Your future self is watching this... and shaking their head. Try harder!",
            "Think of it as lifting your dreams... and also the regret of yesterday’s pizza.",
            "By the time you're done, you'll either have arms or arms with a story.",
            "You’ll never regret the push-ups you do, only the nachos you ate first.",
            "Push-ups now, glory later. Or at least fewer complaints from your future self.",
            "Sweat is just your body’s way of saying ‘why are you doing this to me?’",
            "This is the easy part! Wait until you have to carry your grocery bags.",
            "You're practically Superman. Well, like... a tired, sweaty Superman.",
            "Keep going! Soon you'll have biceps that can bench press your problems!"
        )

        val successMessages = listOf(
            "Nice work, champion! You could probably punch a cloud now.",
            "Well done, Schwarzenegger 2.0! Time to wrestle a mountain!",
            "You nailed it! Maybe even hammered it into submission.",
            "You did it! Somewhere, Chuck Norris is mildly impressed.",
            "Congrats! You’ve got more power in those arms than a small bulldozer.",
            "You’re unstoppable! Or at least mildly inconvenient to gravity.",
            "Boom! You just won the arm lottery. Cash it in for high-fives.",
            "Great job! If only your discipline were as strong as your biceps.",
            "Nice! Your arms are now 2% closer to becoming a national treasure.",
            "Victory is yours! What’s next? Lifting small vehicles?",
            "You did it! Not even your reflection can handle how awesome you are.",
            "Well played, gladiator! Now go drink a protein shake or three.",
            "Your arms are a weapon now. Use them wisely. Or just flex a lot.",
            "That was impressive! Even the treadmill in the corner is jealous.",
            "Congrats! You’re officially stronger than the excuses you made yesterday.",
            "You crushed it! Somewhere, Zeus is considering a gym membership.",
            "Bravo! Now you can high-five your future self with pride.",
            "Mission accomplished! Now go forth and intimidate some gym equipment.",
            "You’re a legend in the making. Or at least a really buff myth.",
            "Impressive! Your arms have leveled up like they’re in a video game.",
            "Great job! Your muscles are now classified as semi-professional lifters.",
            "You nailed it! Time to flex on all those who doubted your greatness.",
            "Success! If this were a movie, you'd be in slow-motion right now.",
            "You crushed it! Somewhere, a dumbbell is writing an apology letter.",
            "Amazing! Now walk around like you own the place. Because you do.",
            "You conquered that! Pretty soon you’ll be bench pressing confidence.",
            "You’re killing it! Those arms could open jars with pure intimidation.",
            "Well done! Your biceps are now sponsored by determination.",
            "Success tastes sweet... but maybe lay off the cookies anyway.",
            "Crushed it! If there was an award for awesomeness, your arms would win."
        )

        val failMessages = listOf(
            "Oof, that was rough. Maybe try lifting your self-esteem next.",
            "Not your best work... but hey, at least the floor appreciates the visit.",
            "You fell like a hero. A really tired, sweaty hero.",
            "That was a valiant effort... for someone who naps professionally.",
            "Well, that didn’t go as planned. But did you really have a plan?",
            "It's okay! Michael Jordan missed a bunch of shots too. Probably.",
            "That was... an attempt. Next time, aim for ‘success.’",
            "Failure is just the universe’s way of saying ‘Not today, champ.’",
            "You gave it 110%! Too bad you needed 150% today.",
            "Well, you didn’t die. So that’s something, right?",
            "Don’t worry, failure builds character. Too bad you wanted muscles.",
            "Gravity 1, You 0. Try again tomorrow, champ.",
            "You didn’t fail, you just... innovated in a downward direction.",
            "It’s not a fail, it’s a ‘pre-success learning opportunity.’",
            "Not great, but hey, at least you have an interesting story now.",
            "If at first you don’t succeed... well, that just happened.",
            "That attempt was so close! To not being close at all.",
            "Congratulations! You successfully achieved... not success.",
            "It's fine, even Thor has bad days. Yours just happened to be today.",
            "You’re like Sisyphus, but instead of a boulder, it’s just a heavy nap.",
            "Failure is the first step to success! It’s just a really long, exhausting step.",
            "Remember: every time you fail, an angel gets its wings... broken.",
            "It’s okay to fail. It’s just... not great. Try harder next time.",
            "You’re one step closer to greatness! Too bad that step was backward.",
            "Well, at least you didn’t quit. Oh wait... never mind.",
            "Failing is just winning at learning. Or something inspirational like that.",
            "Chin up! Or at least try to get your chin off the floor next time.",
            "You tried! And isn’t that what really matters? (No, but let’s pretend.)",
            "Don't worry, Rome wasn’t built in a day. You, however, took a nap."
        )
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load SharedPreferences data
        val sharedPrefs = getSharedPreferences("pressup_data", MODE_PRIVATE)
        val today = LocalDate.now()
        val lastPressDateString = sharedPrefs.getString("lastPressDate", today.minusDays(1).toString())
        val lastFailDateString = sharedPrefs.getString("lastFailDate", today.minusDays(1).toString())
        val lastPressDate = LocalDate.parse(lastPressDateString)
        val lastFailDate = LocalDate.parse(lastFailDateString)
        val pressUpCount = sharedPrefs.getInt("pressUpCount", 16)
        val streakCount = sharedPrefs.getInt("streakCount", 0)
        val totalSuccesses = sharedPrefs.getInt("totalSuccesses", 0)
        val totalFailures = sharedPrefs.getInt("totalFailures", 0)

        setContent {
            val context = LocalContext.current

            // State variables
            var pressUpCountState by remember { mutableStateOf(pressUpCount) }
            var lastPressDateState by remember { mutableStateOf(lastPressDate) }
            var lastFailDateState by remember { mutableStateOf(lastFailDate) }
            var streakCountState by remember { mutableStateOf(streakCount) }
            var totalSuccessesState by remember { mutableStateOf(totalSuccesses) }
            var totalFailuresState by remember { mutableStateOf(totalFailures) }
            var message by remember { mutableStateOf("") }
            var buttonPressed by remember { mutableStateOf(false) }
            var showDialog by remember { mutableStateOf(false) }

            // Animation states for fail button
            var failButtonPressed by remember { mutableStateOf(false) }
            val rotationAngle by animateFloatAsState(
                targetValue = if (failButtonPressed) 360f else 0f,
                animationSpec = tween(durationMillis = 500)
            )

            // Function to save data
            fun saveData(
                pressUpCount: Int,
                lastPressDate: LocalDate,
                lastFailDate: LocalDate,
                streakCount: Int,
                totalSuccesses: Int,
                totalFailures: Int
            ) {
                val editor = sharedPrefs.edit()
                editor.putInt("pressUpCount", pressUpCount)
                editor.putString("lastPressDate", lastPressDate.toString())
                editor.putString("lastFailDate", lastFailDate.toString())
                editor.putInt("streakCount", streakCount)
                editor.putInt("totalSuccesses", totalSuccesses)
                editor.putInt("totalFailures", totalFailures)
                editor.apply()
            }

            // Initialize message if no action taken today
            if (today != lastPressDateState && today != lastFailDateState && message == "") {
                message = motivationalMessages.random()
            }

            // Check if we need to decrement the press-up count on opening
            val daysMissed = ChronoUnit.DAYS.between(lastPressDateState, today)
            if (pressUpCountState > 15 && daysMissed > 0) {
                pressUpCountState -= daysMissed.toInt()
            }

            // Animation effect
            val scale by animateFloatAsState(
                targetValue = if (buttonPressed) 0.9f else 1f,
                animationSpec = tween(durationMillis = 100)
            )

            // LaunchedEffect for the main button animation
            LaunchedEffect(buttonPressed) {
                if (buttonPressed) {
                    delay(100)
                    buttonPressed = false
                }
            }

            // LaunchedEffect for the fail button animation
            LaunchedEffect(failButtonPressed) {
                if (failButtonPressed) {
                    delay(500) // Wait for the rotation animation to complete
                    failButtonPressed = false
                }
            }

            // UI Components
            Scaffold {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color(0xFFBBDEFB), Color.White)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    // Main Circular Button
                    Button(
                        onClick = {
                            if (today == lastFailDateState) {
                                Toast.makeText(
                                    context,
                                    "Fail registered today. Wait until tomorrow.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (today == lastPressDateState) {
                                Toast.makeText(
                                    context,
                                    "Wait until tomorrow.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                pressUpCountState++
                                lastPressDateState = today
                                streakCountState++
                                totalSuccessesState++
                                message = successMessages.random()
                                saveData(
                                    pressUpCountState,
                                    lastPressDateState,
                                    lastFailDateState,
                                    streakCountState,
                                    totalSuccessesState,
                                    totalFailuresState
                                )
                            }
                            buttonPressed = true
                        },
                        modifier = Modifier
                            .size(200.dp)
                            .scale(scale)
                            .clip(CircleShape),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                    ) {
                        Text(
                            text = "${pressUpCountState}",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    // Fail Button with animation
                    Button(
                        onClick = {
                            if (today != lastFailDateState) {
                                lastFailDateState = today
                                streakCountState = 0
                                totalFailuresState++
                                message = failMessages.random()
                                saveData(
                                    pressUpCountState,
                                    lastPressDateState,
                                    lastFailDateState,
                                    streakCountState,
                                    totalSuccessesState,
                                    totalFailuresState
                                )
                                failButtonPressed = true
                            }
                        },
                        modifier = Modifier
                            .size(60.dp)
                            .rotate(rotationAngle) // Apply the rotation animation
                            .align(Alignment.BottomStart)
                            .offset(x = 20.dp, y = -20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(text = "Fail", color = Color.White, fontSize = 12.sp, textAlign = TextAlign.Center)
                        }
                    }

                    // Reset Button
                    Button(
                        onClick = {
                            showDialog = true
                        },
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.TopEnd)
                            .offset(x = -20.dp, y = 20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF455A64)),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(text = "Reset", color = Color.White, fontSize = 12.sp, textAlign = TextAlign.Center)
                        }
                    }

                    // Display the confirmation dialog when reset is clicked
                    if (showDialog) {
                        ConfirmResetDialog(
                            onConfirm = {
                                pressUpCountState = 15
                                lastPressDateState = today.minusDays(1)
                                lastFailDateState = today.minusDays(1)
                                streakCountState = 0
                                totalSuccessesState = 0
                                totalFailuresState = 0
                                message = motivationalMessages.random()
                                saveData(
                                    pressUpCountState,
                                    lastPressDateState,
                                    lastFailDateState,
                                    streakCountState,
                                    totalSuccessesState,
                                    totalFailuresState
                                )
                                showDialog = false // Close the dialog
                            },
                            onDismiss = {
                                showDialog = false // Close the dialog without resetting
                            }
                        )
                    }

                    // Display Message
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 100.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = message,
                            fontSize = 20.sp,
                            fontStyle = FontStyle.Italic,
                            color = Color(0xFF37474F),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }

                    // Display Stats
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopCenter)
                            .padding(top = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = "Streak: ${streakCountState} days", fontSize = 18.sp)
                        Text(text = "Total Successes: ${totalSuccessesState}", fontSize = 16.sp)
                        Text(text = "Total Failures: ${totalFailuresState}", fontSize = 16.sp)
                        val totalAttempts = totalSuccessesState + totalFailuresState
                        val successRate = if (totalAttempts > 0) {
                            (totalSuccessesState * 100) / totalAttempts
                        } else {
                            0
                        }
                        Text(text = "Success Rate: $successRate%", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun ConfirmResetDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Reset Counter")
        },
        text = {
            Text("Are you sure you want to reset everything? This action is irreversible.")
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        }
    )
}