package wesley.engsoft2.locacao.repositorio;
import wesley.engsoft2.locacao.modelo.Aluguel;
import javax.persistence.EntityManager;
import java.time.LocalDate;

public class AluguelRepositoryImpl implements AluguelRepository {

	private EntityManager manager;

	public AluguelRepositoryImpl(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public Aluguel buscaPorDataVencimento(LocalDate dataVencimento) {
		return manager.createQuery("SELECT a FROM Aluguel a WHERE a.dataVencimento = :pDataVencimento", Aluguel.class)
				.setParameter("pDataVencimento", dataVencimento)
				.getSingleResult();
	}

	@Override
	public void salva(Aluguel aluguel) {
		manager.persist(aluguel);
	}

	@Override
	public void exclui(Aluguel aluguel) {
		manager.remove(aluguel);
	}

	@Override
	public void atualiza(Aluguel aluguel) {
		manager.merge(aluguel);
	}
}
