package wesley.engsoft2.locacao.repositorio;
import wesley.engsoft2.locacao.modelo.Aluguel;

import javax.persistence.EntityManager;

public class AluguelRepositoryImpl implements AluguelRepository {

	private EntityManager manager;

	public AluguelRepositoryImpl(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public Aluguel buscaPorIdLocacao(Long id) {
		return manager.createQuery("SELECT a FROM Aluguel a WHERE a.id = :pId", Aluguel.class)
				.setParameter("pId", id)
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
