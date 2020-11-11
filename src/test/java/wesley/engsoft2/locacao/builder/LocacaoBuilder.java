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

		Imovel imovel = new Imovel();
		imovel.setId(new Long(1));
		imovel.setTipoDeImovel("Luxo");

		Cliente cliente = new Cliente();
		cliente.setId(new Long(1));
		cliente.setNome("Wesley");

		Locacao locacao = new Locacao();
		locacao.setId(new Long( 1));
		locacao.setImovel(imovel);
		locacao.setCliente(cliente);
		locacao.setValorAluguel(new BigDecimal(50000));
		
		LocacaoBuilder builder = new LocacaoBuilder();

		return builder;
	}


	public Locacao constroi(){
		return locacao;
	}
}