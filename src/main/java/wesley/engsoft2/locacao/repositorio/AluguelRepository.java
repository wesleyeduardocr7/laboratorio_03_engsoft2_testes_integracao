package wesley.engsoft2.locacao.repositorio;
import wesley.engsoft2.locacao.modelo.Aluguel;

public interface AluguelRepository {

	Aluguel buscaPorIdLocacao(Long id);

	void salva(Aluguel aluguel);

	void exclui(Aluguel aluguel);

	void atualiza(Aluguel aluguel);
}
