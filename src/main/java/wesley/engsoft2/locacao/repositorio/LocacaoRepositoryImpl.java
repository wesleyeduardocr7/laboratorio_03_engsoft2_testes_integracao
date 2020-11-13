package wesley.engsoft2.locacao.repositorio;
import wesley.engsoft2.locacao.modelo.Locacao;
import javax.persistence.EntityManager;
import java.time.LocalDate;

public class LocacaoRepositoryImpl implements LocacaolRepository {

	private EntityManager manager;

	public LocacaoRepositoryImpl(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public Locacao buscaPorDataInicio(LocalDate dataInicio) {
		return manager.createQuery("SELECT l FROM Locacao l WHERE l.dataInicio = :pDataInicio", Locacao.class)
				.setParameter("pDataInicio", dataInicio)
				.getSingleResult();
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
