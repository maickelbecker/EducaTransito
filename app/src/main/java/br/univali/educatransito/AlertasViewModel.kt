package br.univali.educatransito.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.univali.educatransito.data.AlertasRepository
import br.univali.educatransito.model.AlertaTransito
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AlertasState(
    val itens: List<AlertaTransito> = emptyList(),
    val erro: String? = null
)

class AlertasViewModel(
    private val repo: AlertasRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AlertasState())
    val state: StateFlow<AlertasState> = _state

    fun carregar(escolaId: String) {
        viewModelScope.launch {
            try {
                val lista = repo.listar(escolaId)
                _state.value = AlertasState(itens = lista)
            } catch (e: Exception) {
                _state.value = AlertasState(erro = e.message)
            }
        }
    }

    fun publicar(alerta: AlertaTransito) {
        viewModelScope.launch {
            val resultado = repo.publicar(alerta)
            if (resultado.isSuccess) {
                carregar(alerta.escolaId)
            } else {
                _state.value = AlertasState(erro = "Erro ao publicar")
            }
        }
    }
}