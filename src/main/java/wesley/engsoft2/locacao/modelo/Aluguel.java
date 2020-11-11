package wesley.engsoft2.locacao.modelo;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "aluguel")
public class Aluguel {

	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.ALL})
	private Locacao locacao;

	@Id
	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;

	@Column(name = "valor_pago")
	private BigDecimal valorPago;

	@Column(name = "data_pagamento")
	private LocalDate dataPagamento;

	@Column(name = "obs")
	private String obs;

	public Aluguel(){}

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Aluguel aluguel = (Aluguel) o;
		return locacao.equals(aluguel.locacao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(locacao);
	}

	@Override
	public String toString() {
		return "Aluguel{" +
				", locacao=" + locacao +
				", dataVencimento=" + dataVencimento +
				", valorPago=" + valorPago +
				", dataPagamento=" + dataPagamento +
				", obs='" + obs + '\'' +
				'}';
	}
}