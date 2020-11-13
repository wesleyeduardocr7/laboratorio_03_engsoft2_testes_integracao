package wesley.engsoft2.locacao.repositorio;
import org.junit.jupiter.api.*;
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

public class AluguelRepositoryTest {

	private EntityManager manager;
	private static EntityManagerFactory emf;
	private AluguelRepository alugueis;
	private ImovelRepository imoveis;
	private ClienteRepository clientes;
	private LocacaolRepository locacoes;

	@BeforeAll
	public static void inicio() {
		emf = Persistence.createEntityManagerFactory("locadoraPU_test");
	}

	@BeforeEach
	public void antes() {
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		alugueis = new AluguelRepositoryImpl(manager);
		imoveis = new ImovelRepositoryImpl(manager);
		clientes = new ClienteRepositoryImpl(manager);
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
	public void deveSalvarUmAluguel() {

		Assertions.assertThrows(NoResultException.class,
				() -> imoveis.buscaPorTipo("Luxo"),
				"Deveria lançar a exceção NoResultException");

		imoveis.salva(new Imovel("Luxo"));
		manager.flush();
		manager.clear();

		Imovel imovelDoBanco = imoveis.buscaPorTipo("Luxo");
		Assertions.assertNotNull(imovelDoBanco);


	}
}
