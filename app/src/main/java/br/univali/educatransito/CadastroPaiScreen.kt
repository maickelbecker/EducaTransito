@Composable
fun CadastroPaiScreen(
    viewModel: CadastroViewModel = viewModel(),
    onCadastroSucesso: () -> Unit
) {
    val cadastroSucesso by viewModel.cadastroSucesso.observeAsState()

    // ... campos de entrada

    if (cadastroSucesso == true) {
        SuccessScreen("Cadastro do pai realizado com sucesso!")
        onCadastroSucesso()
    }
}
