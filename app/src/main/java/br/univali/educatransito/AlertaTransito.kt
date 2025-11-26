package br.univali.educatransito.model

data class AlertaTransito(
    val id: String,
    val tipo: String,
    val mensagem: String,
    val emissao: Long,
    val escolaId: String
)