package com.example.praktam_2457051009

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.*
import com.example.praktam_2457051009.model.Sprint
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.praktam_2457051009.model.SprintSource
import com.example.praktam_2457051009.ui.theme.PrakTAM_2457051009Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PrakTAM_2457051009Theme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            Greeting(navController)
        }

        composable("detail/{jarak}") { backStackEntry ->
            val jarak = backStackEntry.arguments?.getString("jarak")

            val sprint = SprintSource.dummySprint.find {
                it.jarak == jarak
            }

            if (sprint != null) {
                DetailScreen(sprint, navController)
            }
        }
    }
}

@Composable
fun Greeting(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val sprintList = SprintSource.dummySprint
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.track4),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Sprint Monitoring System",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }

            item {

                Text(
                    text = "Latihan Populer",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(sprintList) { sprint ->
                        Card(
                            modifier = Modifier
                                .width(160.dp)
                                .clickable {
                                    navController.navigate("detail/${sprint.jarak}")
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White.copy(alpha = 0.9f)
                            )
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {

                                Image(
                                    painter = painterResource(id = sprint.imageRes),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(90.dp),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = sprint.jarak,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Semua Latihan",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }

            items(sprintList) { sprint ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("detail/${sprint.jarak}")
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {

                    Column(modifier = Modifier.padding(16.dp)) {

                        Image(
                            painter = painterResource(id = sprint.imageRes),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Jenis: ${sprint.jarak}",
                            fontWeight = FontWeight.Bold
                        )

                        Text(text = "Latihan: ${sprint.deskripsi}")

                        Text(
                            text = "Detail: ${sprint.target}",
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                Toast.makeText(
                                    context,
                                    "Mulai latihan ${sprint.jarak}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Mulai Latihan")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailScreen(
    sprint: Sprint,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Image(
            painter = painterResource(id = sprint.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Jenis: ${sprint.jarak}",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Text("Latihan: ${sprint.deskripsi}")
        Text("Detail: ${sprint.target}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kembali")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrakTAM_2457051009Theme {
        val navController = rememberNavController()
        Greeting(navController)
    }
}