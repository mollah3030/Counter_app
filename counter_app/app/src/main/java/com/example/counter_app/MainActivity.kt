import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Simple Material theme wrapper
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TripleCounterScreen()
                }
            }
        }
    }
}

/* ---------- UI COMPOSABLES ---------- */

// Whole screen: three counters in a column
@Composable
fun TripleCounterScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Triple Counter",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        CounterCard(label = "Counter 1")
        CounterCard(label = "Counter 2")
        CounterCard(label = "Counter 3")
    }
}

// One counter: text field + + / - buttons + result
@Composable
fun CounterCard(label: String) {
    var count by remember { mutableStateOf(0) }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = label, fontWeight = FontWeight.SemiBold)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Text field where you can set the starting value
                TextField(
                    value = count.toString(),
                    onValueChange = { newText ->
                        count = newText.toIntOrNull() ?: 0
                    },
                    label = { Text("Start value") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )

                // + and - buttons
                Button(onClick = { count++ }) {
                    Text("+")
                }
                Button(onClick = { count-- }) {
                    Text("-")
                }
            }

            Text(text = "Current: $count")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TripleCounterPreview() {
    MaterialTheme {
        TripleCounterScreen()
    }
}
