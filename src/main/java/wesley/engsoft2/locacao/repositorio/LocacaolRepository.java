package wesley.engsoft2.locacao.repositorio;
import wesley.engsoft2.locacao.modelo.Cliente;
import wesley.engsoft2.locacao.modelo.Locacao;

import java.time.LocalDate;

public interface LocacaolRepository {

	Locacao buscaPorDataInicio(LocalDate dataInicio);

	void salva(Locacao locacao);

	void exclui(Locacao locacao);

	void atualiza(Locacao locacao);
}
