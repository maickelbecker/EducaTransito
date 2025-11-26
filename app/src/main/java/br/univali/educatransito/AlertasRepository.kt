package br.univali.educatransito.data

import br.univali.educatransito.model.AlertaTransito

interface AlertasRepository {
    suspend fun listar(escolaId: String): List<AlertaTransito>
    suspend fun publicar(alerta: AlertaTransito): Result<Unit>
}