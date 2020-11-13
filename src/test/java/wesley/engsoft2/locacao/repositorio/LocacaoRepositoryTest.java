package wesley.engsoft2.locacao.repositorio;
import org.junit.jupiter.api.*;
import wesley.engsoft2.locacao.builder.LocacaoBuilder;
import wesley.engsoft2.locacao.modelo.Locacao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class LocacaoRepositoryTest {

	private EntityManager manager;
	private static EntityManagerFactory emf;
	private LocacaolRepository locacoes;

	@BeforeAll
	public static void inicio() {
		emf = Persistence.createEntityManagerFactory("locadoraPU_test");
	}

	@BeforeEach
	public void antes() {
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		locacoes = new LocacaoRepositoryImpl(manager);
	}

	@AfterEach
	public void depois() {
		manager.getTransaction().rollback();
	}

	@AfterAll
	public static void fim() {
		emf.close();
	}

	@Test
	public void deveSalvarUmaLocacao() {

		Locacao locacao = LocacaoBuilder.umaLocacao().constroi();

		locacoes.salva(locacao);
		manager.flush();
		manager.clear();

		Locacao novaLocacao = locacoes.buscaPorDataInicio(LocalDate.now());

		Assertions.assertNotNull(novaLocacao);

	}

	@Test
	public void deveAtualizarUmaLocacao() {

		Locacao locacao = LocacaoBuilder.umaLocacao().constroi();

		locacoes.salva(locacao);
		locacao.setDataInicio(LocalDate.now().plusDays(5));

		locacoes.atualiza(locacao);

		manager.flush();
		manager.clear();

		Locacao novaLocacao = locacoes.buscaPorDataInicio(LocalDate.now().plusDays(5));
		Assertions.assertNotNull(novaLocacao);

		Assertions.assertThrows(NoResultException.class,
				() -> locacoes.buscaPorDataInicio(LocalDate.now()),
				"Deveria ter lançado a exceção NoResultException");
	}

	@Test
	public void deveExcluirUmaLocacao() {

		Locacao locacao = LocacaoBuilder.umaLocacao().constroi();

		locacoes.salva(locacao);
		locacoes.exclui(locacao);

		manager.flush();
		manager.clear();

		Assertions.assertThrows(NoResultException.class,
				() -> locacoes.buscaPorDataInicio(LocalDate.now()),
				"Deveria ter lançado a exceção NoResultException");
	}

}
