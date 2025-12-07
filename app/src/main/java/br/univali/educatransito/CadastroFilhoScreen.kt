@Composable
fun CadastroFilhoScreen(
    viewModel: CadastroFilhoViewModel = viewModel(),
    onCadastroSucesso: () -> Unit
) {
    val cadastroSucesso by viewModel.cadastroSucesso.observeAsState()

    // ... campos de entrada

    if (cadastroSucesso == true) {
        SuccessScreen("Cadastro do filho realizado com sucesso!")
        onCadastroSucesso()
    }
}
