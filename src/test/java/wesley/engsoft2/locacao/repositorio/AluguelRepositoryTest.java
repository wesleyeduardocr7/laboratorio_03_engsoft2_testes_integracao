package wesley.engsoft2.locacao.repositorio;
import org.junit.jupiter.api.*;
import wesley.engsoft2.locacao.builder.AluguelBuilder;
import wesley.engsoft2.locacao.builder.ImovelBuilder;
import wesley.engsoft2.locacao.builder.LocacaoBuilder;
import wesley.engsoft2.locacao.modelo.Aluguel;
import wesley.engsoft2.locacao.modelo.Cliente;
import wesley.engsoft2.locacao.modelo.Imovel;
import wesley.engsoft2.locacao.modelo.Locacao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class AluguelRepositoryTest {

	private EntityManager manager;
	private static EntityManagerFactory emf;
	private AluguelRepository alugueis;

	@BeforeAll
	public static void inicio() {
		emf = Persistence.createEntityManagerFactory("locadoraPU_test");
	}

	@BeforeEach
	public void antes() {
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		alugueis = new AluguelRepositoryImpl(manager);
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
	public void deveSalvarUmAluguel(){

		Aluguel aluguel = AluguelBuilder.umAluguel().constroi();

		alugueis.salva(aluguel);
		manager.flush();
		manager.clear();

		Aluguel novoAluguel = alugueis.buscaPorDataVencimento(LocalDate.now().plusDays(30));

		Assertions.assertNotNull(novoAluguel);

	}

	@Test
	public void deveAtualizarUmAluguel() {

		Aluguel aluguel = AluguelBuilder.umAluguel().constroi();

		alugueis.salva(aluguel);
		aluguel.setDataVencimento(LocalDate.of(2020,5,20));

		alugueis.atualiza(aluguel);

		manager.flush();
		manager.clear();

		Aluguel novoAluguel = alugueis.buscaPorDataVencimento(LocalDate.of(2020,5,20));
		Assertions.assertNotNull(novoAluguel);

		Assertions.assertThrows(NoResultException.class,
			() -> alugueis.buscaPorDataVencimento(LocalDate.now().plusDays(30)),
			"Deveria ter lançado a exceção NoResultException");
	}

	@Test
	public void deveExcluirUmAluguel() {


		Aluguel aluguel = AluguelBuilder.umAluguel().constroi();

		alugueis.salva(aluguel);
		alugueis.exclui(aluguel);

		manager.flush();
		manager.clear();

		Assertions.assertThrows(NoResultException.class,
				() -> alugueis.buscaPorDataVencimento(LocalDate.now().plusDays(30)),
				"Deveria ter lançado a exceção NoResultException");
	}

}
