package wesley.engsoft2.locacao.builder;
import wesley.engsoft2.locacao.modelo.Aluguel;
import wesley.engsoft2.locacao.modelo.Cliente;
import wesley.engsoft2.locacao.modelo.Imovel;
import wesley.engsoft2.locacao.modelo.Locacao;
import java.time.LocalDate;

public class AluguelBuilder {

	private Aluguel aluguel;
	private static Long contadorID = new Long(1);

	private AluguelBuilder() {}
	
	public static AluguelBuilder umAluguel() {

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
		
		AluguelBuilder builder = new AluguelBuilder();

		builder.aluguel = new Aluguel();
		builder.aluguel.setLocacao(locacao);
		builder.aluguel.setDataVencimento(LocalDate.now());

		return builder;
	}


	public Aluguel constroi(){
		return aluguel;
	}
}