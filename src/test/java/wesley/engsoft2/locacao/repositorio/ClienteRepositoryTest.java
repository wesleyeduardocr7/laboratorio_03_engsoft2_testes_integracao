package wesley.engsoft2.locacao.repositorio;
import org.junit.jupiter.api.*;
import wesley.engsoft2.locacao.modelo.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClienteRepositoryTest {

	private EntityManager manager;
	private static EntityManagerFactory emf;
	private ClienteRepository clientes;

	@BeforeAll
	public static void inicio() {
		emf = Persistence.createEntityManagerFactory("locadoraPU_test");
	}

	@BeforeEach
	public void antes() {
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		clientes = new ClienteRepositoryImpl(manager);
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
	public void deveSalvarUmCliente() {

		Cliente cliente = new Cliente("Wesley");

		clientes.salva(cliente);
		manager.flush();
		manager.clear();

		Cliente novoCliente = clientes.buscaPorNome("Wesley");
		Assertions.assertNotNull(novoCliente);
	}

	@Test
	public void deveAtualizarUmCliente() {

		Cliente cliente = new Cliente("Wesley");

		clientes.salva(cliente);
		cliente.setNome(("Eduardo"));

		clientes.atualiza(cliente);

		manager.flush();
		manager.clear();

		Cliente novoCliente = clientes.buscaPorNome("Eduardo");
		Assertions.assertNotNull(novoCliente);

		Assertions.assertThrows(NoResultException.class,
				() -> clientes.buscaPorNome("Wesley"),
				"Deveria ter lançado a exceção NoResultException");
	}

	@Test
	public void deveExcluirUmCliente() {

		Cliente cliente = new Cliente("Wesley");

		clientes.salva(cliente);
		clientes.exclui(cliente);

		manager.flush();
		manager.clear();

		Assertions.assertThrows(NoResultException.class,
				() -> clientes.buscaPorNome("Wesley"),
				"Deveria ter lançado a exceção NoResultException");
	}


	@Test
	public void deveEncontrarClientePeloNome() {

		Assertions.assertThrows(NoResultException.class,
				() -> clientes.buscaPorNome("Wesley"),
				"Deveria lançar a exceção NoResultException");

		clientes.salva(new Cliente("Wesley") );
		manager.flush();
		manager.clear();

		Cliente clienteDoBanco = clientes.buscaPorNome("Wesley");

		assertThat("Wesley", is(equalTo(clienteDoBanco.getNome())));
	}

}
