package br.univali.educatransito.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.univali.educatransito.viewmodel.CadastroFilhoViewModel

@Composable
fun CadastroFilhoScreen(
    viewModel: CadastroFilhoViewModel = viewModel(),
    onCadastroSucesso: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var escola by remember { mutableStateOf("") }

    val mensagemErro by viewModel.mensagemErro.observeAsState()
    val cadastroSucesso by viewModel.cadastroSucesso.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome do Filho") })
        OutlinedTextField(value = idade, onValueChange = { idade = it }, label = { Text("Idade") })
        OutlinedTextField(value = escola, onValueChange = { escola = it }, label = { Text("Escola") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val idadeInt = idade.toIntOrNull() ?: -1
            viewModel.cadastrarFilho(nome, idadeInt, escola)
        }) {
            Text("Cadastrar Filho")
        }

        Spacer(modifier = Modifier.height(16.dp))

        mensagemErro?.let { ErrorScreen(it) }
        if (cadastroSucesso == true) {
            SuccessScreen("Cadastro do filho realizado com sucesso!")
            onCadastroSucesso()
        }
    }
}