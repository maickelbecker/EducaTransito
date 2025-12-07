package br.univali.educatransito.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.univali.educatransito.viewmodel.CadastroViewModel

@Composable
fun CadastroPaiScreen(
    viewModel: CadastroViewModel = viewModel(),
    onCadastroSucesso: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    val mensagemErro by viewModel.mensagemErro.observeAsState()
    val cadastroSucesso by viewModel.cadastroSucesso.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") })
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(value = senha, onValueChange = { senha = it }, label = { Text("Senha") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.cadastrarPai(nome, email, senha) }) {
            Text("Cadastrar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        mensagemErro?.let { ErrorScreen(it) }
        if (cadastroSucesso == true) {
            SuccessScreen("Cadastro do pai realizado com sucesso!")
            onCadastroSucesso()
        }
    }
}