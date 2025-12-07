package br.univali.educatransito

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.univali.educatransito.ui.screen.HomeScreen
import br.univali.educatransito.ui.screen.CadastroPaiScreen
import br.univali.educatransito.ui.screen.CadastroFilhoScreen
import br.univali.educatransito.ui.theme.EducaTransitoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EducaTransitoTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(onNavigateCadastroPai = { navController.navigate("cadastro_pai") })
                    }
                    composable("cadastro_pai") {
                        CadastroPaiScreen(onCadastroSucesso = { navController.navigate("cadastro_filho") })
                    }
                    composable("cadastro_filho") {
                        CadastroFilhoScreen(onCadastroSucesso = { navController.navigate("home") })
                    }
                }
            }
        }
    }
}
