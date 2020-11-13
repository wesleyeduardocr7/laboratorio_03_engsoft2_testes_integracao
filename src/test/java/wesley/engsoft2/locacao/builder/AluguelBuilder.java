package wesley.engsoft2.locacao.builder;
import wesley.engsoft2.locacao.modelo.Aluguel;
import wesley.engsoft2.locacao.modelo.Locacao;

import java.time.LocalDate;

public class AluguelBuilder {

	private Aluguel aluguel;
	private static Long contadorID = new Long(1);

	private AluguelBuilder() {}
	
	public static AluguelBuilder umAluguel() {

		AluguelBuilder builder = new AluguelBuilder();

		builder.aluguel = new Aluguel();

		Locacao locacao = LocacaoBuilder.umaLocacao().constroi();

		builder.aluguel.setLocacao(locacao);

		builder.aluguel.setDataVencimento(LocalDate.now().plusDays(30));

		return builder;
	}


	public Aluguel constroi(){
		return aluguel;
	}
}