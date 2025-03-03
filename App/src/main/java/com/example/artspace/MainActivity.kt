package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.artspace.data.DataSource

class MainActivity : ComponentActivity() {

//https://youtu.be/DHVzyEZsyXQ

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            ArtSpaceTheme {

                NavHost(navController = navController, startDestination = Screen.Home.route + "/{id}") {
                        composable(

                            Screen.Home.route + "/{id}", arguments = listOf(navArgument( "id") {

                                type = NavType.IntType
                                defaultValue = 0
                            })
                        ) {
                          HomePage(navController = navController)
                }

                    composable(
                       Screen.Artist.route + "/{id}",
                        arguments = listOf(navArgument("id"){ type = NavType.IntType })


                    ){
                     ArtistPage(navController = navController)
                    }

                }

            }
        }
    }
}
@Composable
fun ArtistPage(navController: NavController) {
val id = navController.currentBackStackEntry?.arguments?.getInt("id") ?: 0

val art = DataSource.arts[id]







    Button(onClick = { navController.navigate(Screen.Home.route + "/$id")}) {
        Text(text = stringResource(id = R.string.back))
    }
    Text(text = "Artist Page")


}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController) {
    var current by remember {
        mutableIntStateOf(
            navController.currentBackStackEntry?.arguments?.getInt("id") ?: 0
        )
    }

    val art = DataSource.arts[current]

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer

                )
            )
        }) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {

                Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.spacer_extra_large)))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Artwall(current, art.artworkImageId, art.descriptionId, navController)
                }
            }
            ArtDescriptor(art.titleId, art.artistId, art.yearId)
            DisplayController(current) {
                current = if (it !in 0  ..<DataSource.arts.size) 0 else it
            }
        }


    }
}

@Composable
fun Artwall(artistId: Int, artImageId: Int, artDescriptionId: Int,navController: NavController) {

// HOME PAGE section A

    // TODO: 1. Add image of artwork
    val imageResource = painterResource(id = artImageId)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // Navigate to the artist's page
                navController.navigate("artist/$artistId")
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Add image of artwork
        Image(
            painter = painterResource(id = artImageId),
            contentDescription = stringResource(id = artDescriptionId),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // You can adjust the height as needed
        )

        Spacer(modifier = Modifier.height(16.dp))


    }
}


    //TODO: Add a click listener to navigate to the artist page





    //Note: use the following code on your click event
    // navController.navigate(Screen.Artist.route + "/$artistId")


// Safely REMOVE the following code and ADD your own code




@Composable
fun ArtDescriptor(artTitleId: Int, artistId: Int, artYearId: Int,) {

// HOME PAGE Section B

    //TODO: 1. Add Artwork

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // 1. Artwork Title
        Text(
            text = stringResource(id = artTitleId),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 2. Artist Name and Year
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Artist name
            Text(
                text = stringResource(id = artistId),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )

            // Year
            Text(
                text = stringResource(id = artYearId),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            color = MaterialTheme.colorScheme.surfaceVariant,
            tonalElevation = 2.dp
        ) {
            Text(
                text = stringResource(id = artYearId ),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
        }

    }

    //Todo: 2. Add artist name and year of artwork and bio of artist currently on screen



}


@Composable
fun DisplayController(current: Int, updateCurrent: (Int) -> Unit) {

// HOME PAGE section C

    //TODO: 1. Add a button to navigate to the previous artwork

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Previous Button
        Button(
            onClick = { updateCurrent(current - 1) },
            enabled = current != 0,
            modifier = Modifier.weight(1f)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Previous"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Previous")
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Next Button
        Button(
            onClick = { updateCurrent(current + 1) },
            enabled = current != DataSource.arts.size - 1,
            modifier = Modifier.weight(1f)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Next")
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next"
                )
            }
        }
    }





    //TODO 2. Add a button to navigate to the next artwork




}