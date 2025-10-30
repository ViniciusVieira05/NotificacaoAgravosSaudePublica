package entidades.dados;

import enums.doenca.Doencas;

import java.time.LocalDate;

public class DadosGerais {

    private Doencas doenca;
    private LocalDate dataNotificacao;
    private String uf;
    private String municipioDeNotificacao;
    private String ubs;
    private LocalDate dataDiagnostico;

    public DadosGerais(Doencas doenca, LocalDate dataNotificacao, String uf, String municipioDeNotificacao, String ubs, LocalDate dataDiagnostico){
        this.doenca = doenca;
        this.dataNotificacao = dataNotificacao;
        this.uf = uf;
        this.municipioDeNotificacao = municipioDeNotificacao;
        this.ubs = ubs;
        this.dataDiagnostico = dataDiagnostico;
    }

    public Doencas getDoenca() {
        return doenca;
    }

    public void setDoenca(Doencas doenca) {
        this.doenca = doenca;
    }

    public LocalDate getDataNotificacao() {
        return dataNotificacao;
    }

    public void setDataNotificacao(LocalDate dataNotificacao) {
        this.dataNotificacao = dataNotificacao;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getMunicipioDeNotificacao() {
        return municipioDeNotificacao;
    }

    public void setMunicipioDeNotificacao(String municipioDeNotificacao) {
        this.municipioDeNotificacao = municipioDeNotificacao;
    }

    public String getUbs() {
        return ubs;
    }

    public void setUbs(String ubs) {
        this.ubs = ubs;
    }

    public LocalDate getDataDiagnostico() {
        return dataDiagnostico;
    }

    public void setDataDiagnostico(LocalDate dataDiagnostico) {
        this.dataDiagnostico = dataDiagnostico;
    }
}
