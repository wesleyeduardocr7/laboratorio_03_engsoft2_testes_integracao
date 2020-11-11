package wesley.engsoft2.locacao.repositorio;
import wesley.engsoft2.locacao.modelo.Aluguel;
import wesley.engsoft2.locacao.modelo.Locacao;

import javax.persistence.EntityManager;

public class LocacaoRepositoryImpl implements LocacaolRepository {

	private EntityManager manager;

	public LocacaoRepositoryImpl(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void salva(Locacao locacao) {
		manager.persist(locacao);
	}

	@Override
	public void exclui(Locacao locacao) {
		manager.remove(locacao);
	}

	@Override
	public void atualiza(Locacao locacao) {
		manager.merge(locacao);
	}
}
