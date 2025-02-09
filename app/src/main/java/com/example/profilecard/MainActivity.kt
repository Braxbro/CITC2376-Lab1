package com.example.profilecard

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.profilecard.ui.theme.ProfileCardTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.jar.Attributes.Name

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileCardTheme {
                Scaffold(
                    containerColor = Color(255,75,75),
                    modifier = Modifier.fillMaxSize())
                { innerPadding ->
                    ProfileCard(
                        profileImage = painterResource(R.drawable.apple),
                        name = "Brennan Brook",
                        bio = "I like apples, so I used a picture of one.\n" +
                            "I would have rather used Godot.",
                        textColor = Color(255,255,255),
                        modifier = Modifier.padding(innerPadding).fillMaxSize()
                    )
                }
            }
        }
    }
}

// A composite Composable for the profile card thing.
// Accepts a painter resource (image resource) and two strings for its text.
@Composable
fun ProfileCard(profileImage: Painter, name: String, bio: String, textColor: Color,
                modifier: Modifier = Modifier) {
    val cardModifier = modifier.padding(Dp(16f))
    // Arrange the bits in a neat vertical column
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = cardModifier) {
        // all of this is pretty basic and self-explanatory.
        Image (
            painter = profileImage,
            contentDescription = null,
            // This feels EXTREMELY illegal.
            // I noticed that the order of operations is important here.
            // If I apply padding after I clip, I don't clip to a circle anymore
            // since the padding applies inside of the circle.
            modifier = Modifier.padding(Dp(4f)).clip(CircleShape).
            fillMaxWidth().aspectRatio(1f)
        )
        Text(
            text = name,
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            color = textColor,
            // All of the padding is 4dp because it applies on all sides.
            // Thus it combines with the other elements' padding to make 8dp spacing.
            modifier = Modifier.padding(Dp(4f)).fillMaxWidth()
        )
        Text(
            text = bio,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            lineHeight = 22.sp,
            color = textColor,
            modifier = Modifier.padding(Dp(4f)).fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    ProfileCardTheme {
        ProfileCardTheme {
            ProfileCard(
                profileImage = painterResource(R.drawable.apple),
                name = "Eve Employments",
                bio = "This is a joke profile for a lab.\nHopefully the joke is funny.",
                textColor = Color(255,255,255)
            )
        }
    }
}