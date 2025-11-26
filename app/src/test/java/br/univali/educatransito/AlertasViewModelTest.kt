package br.univali.educatransito

import br.univali.educatransito.data.AlertasRepository
import br.univali.educatransito.model.AlertaTransito
import br.univali.educatransito.ui.AlertasViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.After
import org.junit.Test
import org.junit.Assert.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class AlertasViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var vm: AlertasViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        val repo = object : AlertasRepository {
            override suspend fun listar(escolaId: String): List<AlertaTransito> {
                return listOf(
                    AlertaTransito("1", "Buraco", "Buraco na rua", 123456789, escolaId)
                )
            }

            override suspend fun publicar(alerta: AlertaTransito): Result<Unit> {
                return Result.success(Unit)
            }
        }
        vm = AlertasViewModel(repo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `carregar deve popular itens quando sucesso`() = runTest {
        vm.carregar("escola123")
        testDispatcher.scheduler.advanceUntilIdle() // <- garante que a coroutine termine
        val estado = vm.state.value
        assertEquals(1, estado.itens.size)
        assertEquals("Buraco", estado.itens[0].tipo)
    }
}