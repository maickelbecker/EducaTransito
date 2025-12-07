package br.univali.educatransito.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CadastroViewModel : ViewModel() {

    val mensagemErro = MutableLiveData<String?>()
    val cadastroSucesso = MutableLiveData<Boolean>()

    fun cadastrarPai(nome: String, email: String, senha: String) {
        when {
            nome.isBlank() -> mensagemErro.value = "O nome não pode estar vazio."
            !email.contains("@") -> mensagemErro.value = "E-mail inválido."
            senha.length < 6 -> mensagemErro.value = "A senha deve ter pelo menos 6 caracteres."
            else -> {
                // Aqui você salvaria no banco ou repository
                cadastroSucesso.value = true
                mensagemErro.value = null
            }
        }
    }

    fun resetarEstado() {
        mensagemErro.value = null
        cadastroSucesso.value = false
    }
}
