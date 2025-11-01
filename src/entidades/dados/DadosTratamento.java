package entidades.dados;

import enums.doenca.EsquemadeTratamentoUtilizado;
import enums.outros.EsquemaTerapeuticoInicial;

import java.time.LocalDate;

public class DadosTratamento {

    private EsquemadeTratamentoUtilizado esquemadeTratamentoUtilizado;
    private LocalDate dataInicioDoTratamento;
    private EsquemaTerapeuticoInicial esquemaTerapeuticoInicial;

    public DadosTratamento(){

    }

    public DadosTratamento(EsquemadeTratamentoUtilizado esquemadeTratamentoUtilizado, LocalDate dataInicioDoTratamento, EsquemaTerapeuticoInicial esquemaTerapeuticoInicial){
        this.esquemadeTratamentoUtilizado = esquemadeTratamentoUtilizado;
        this.dataInicioDoTratamento = dataInicioDoTratamento;
        this.esquemaTerapeuticoInicial = esquemaTerapeuticoInicial;
    }

    public EsquemadeTratamentoUtilizado getEsquemadeTratamentoUtilizado() {
        return esquemadeTratamentoUtilizado;
    }

    public void setEsquemadeTratamentoUtilizado(EsquemadeTratamentoUtilizado esquemadeTratamentoUtilizado) {
        this.esquemadeTratamentoUtilizado = esquemadeTratamentoUtilizado;
    }

    public LocalDate getDataInicioDoTratamento() {
        return dataInicioDoTratamento;
    }

    public void setDataInicioDoTratamento(LocalDate dataInicioDoTratamento) {
        this.dataInicioDoTratamento = dataInicioDoTratamento;
    }

    public EsquemaTerapeuticoInicial getEsquemaTerapeuticoInicial() {
        return esquemaTerapeuticoInicial;
    }

    public void setEsquemaTerapeuticoInicial(EsquemaTerapeuticoInicial esquemaTerapeuticoInicial) {
        this.esquemaTerapeuticoInicial = esquemaTerapeuticoInicial;
    }
}
