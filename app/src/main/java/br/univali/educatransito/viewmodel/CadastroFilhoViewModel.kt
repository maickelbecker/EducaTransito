package br.univali.educatransito.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CadastroFilhoViewModel : ViewModel() {

    val mensagemErro = MutableLiveData<String?>()
    val cadastroSucesso = MutableLiveData<Boolean>()

    fun cadastrarFilho(nome: String, idade: Int, escola: String) {
        when {
            nome.isBlank() -> mensagemErro.value = "O nome do filho não pode estar vazio."
            idade <= 0 -> mensagemErro.value = "A idade deve ser maior que zero."
            escola.isBlank() -> mensagemErro.value = "A escola deve ser informada."
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
