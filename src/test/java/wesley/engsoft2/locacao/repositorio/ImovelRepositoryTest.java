package wesley.engsoft2.locacao.repositorio;
import org.junit.jupiter.api.*;
import wesley.engsoft2.locacao.modelo.Imovel;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ImovelRepositoryTest {

	private EntityManager manager;
	private static EntityManagerFactory emf;
	private ImovelRepository imoveis;

	@BeforeAll
	public static void inicio() {
		emf = Persistence.createEntityManagerFactory("locadoraPU_test");
	}

	@BeforeEach
	public void antes() {
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		imoveis = new ImovelRepositoryImpl(manager);
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
	public void deveEncontrarImovelPeloTipo() {

		Assertions.assertThrows(NoResultException.class,
				() -> imoveis.buscaPorTipo("Luxo"),
				"Deveria lançar a exceção NoResultException");

		imoveis.salva(new Imovel("Luxo") );
		manager.flush();
		manager.clear();

		Imovel imovelDoBanco = imoveis.buscaPorTipo("Luxo");

		assertThat("Luxo", is(equalTo(imovelDoBanco.getTipoDeImovel())));
	}

}
