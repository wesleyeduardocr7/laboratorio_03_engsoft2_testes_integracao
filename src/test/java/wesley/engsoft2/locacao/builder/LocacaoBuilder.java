package wesley.engsoft2.locacao.builder;
import wesley.engsoft2.locacao.modelo.Aluguel;
import wesley.engsoft2.locacao.modelo.Cliente;
import wesley.engsoft2.locacao.modelo.Imovel;
import wesley.engsoft2.locacao.modelo.Locacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LocacaoBuilder {

	private Locacao locacao;
	private static Long contadorID = new Long(1);

	private LocacaoBuilder() {}
	
	public static LocacaoBuilder umaLocacao() {

		LocacaoBuilder builder = new LocacaoBuilder();

		builder.locacao = new Locacao();

		builder.locacao.setImovel(new Imovel("Luxo"));

		builder.locacao.setCliente(new Cliente("Wesley"));

		builder.locacao.setDataInicio(LocalDate.now());

		builder.locacao.setValorAluguel(new BigDecimal(5000));

		return builder;
	}


	public Locacao constroi(){
		return locacao;
	}
}