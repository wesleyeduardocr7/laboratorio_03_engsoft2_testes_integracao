package wesley.engsoft2.locacao.repositorio;
import wesley.engsoft2.locacao.modelo.Locacao;

import java.time.LocalDate;
import java.util.List;

public interface LocacaolRepository {

	Locacao buscaPorDataInicio(LocalDate dataInicio);

	List<Locacao> recuperarLocacoesDoTipoApartamentoEAtivas(String bairro,String tipoImovel, boolean ativo);

	void salva(Locacao locacao);

	void exclui(Locacao locacao);

	void atualiza(Locacao locacao);
}
