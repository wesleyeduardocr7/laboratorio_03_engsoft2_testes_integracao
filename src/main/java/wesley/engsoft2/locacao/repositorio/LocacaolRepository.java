package wesley.engsoft2.locacao.repositorio;
import wesley.engsoft2.locacao.modelo.Locacao;

public interface LocacaolRepository {

	void salva(Locacao locacao);

	void exclui(Locacao locacao);

	void atualiza(Locacao locacao);
}
