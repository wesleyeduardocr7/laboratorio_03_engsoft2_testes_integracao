package wesley.engsoft2.locacao.repositorio;
import wesley.engsoft2.locacao.modelo.Aluguel;

import java.time.LocalDate;

public interface AluguelRepository {

	Aluguel buscaPorDataVencimento(LocalDate dataVencimento);

	void salva(Aluguel aluguel);

	void exclui(Aluguel aluguel);

	void atualiza(Aluguel aluguel);
}
