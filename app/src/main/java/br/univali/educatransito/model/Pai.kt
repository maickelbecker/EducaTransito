package br.univali.educatransito.model

data class Pai(
    val id: String,
    val nome: String,
    val email: String,
    val filhos: List<Filho> = emptyList()
)
