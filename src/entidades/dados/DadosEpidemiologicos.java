package entidades.dados;

import enums.doenca.*;
import enums.outros.*;
import enums.paciente.AtividadeUltimos15Dias;

import java.time.LocalDate;

public class DadosEpidemiologicos {

    private LocalDate dataInvestigação;
    private String Ocupação;

    private AtividadeUltimos15Dias atividade;
    private String dadosDoExame;
    private TipoDeLamina tipoLamina;
    private Sintomas sintomas;
    private LocalDate dataExame;
    private ResultadoExame resultadoExame;
    private float parasitasMetroCubico;

    private TipoEntrada tipoEntrada;
    private FormaTuberculose formaTuberculose;
    private LocalExtrapulmonar localExtrapulmonar;
    private BaciloscopiaEscarro baciloscopiaEscarro;
    private RadiografiaDoTorax radiografiaDoTorax;
    private HIV hiv;
    private Histopatologia histopatologia;
    private Cultura cultura;
    private TesteMolecularRápidoTB testeMolecularRápidoTB;
    private TestedeSensibilidade testeDeSensibilidade;

    private MododeEntrada modoDeEntrada;
    private ModoDetectacaoCasoNovo modoDetectacaoCasoNovo;
    private Baciloscopia baciloscopia;

    public DadosEpidemiologicos(){

    }

    public DadosEpidemiologicos(LocalDate dataInvestigação, String ocupação, AtividadeUltimos15Dias atividade, String dadosDoExame, TipoDeLamina tipoLamina, Sintomas sintomas, LocalDate dataExame, ResultadoExame resultadoExame, float parasitasMetroCubico, TipoEntrada tipoEntrada, FormaTuberculose formaTuberculose, LocalExtrapulmonar localExtrapulmonar, BaciloscopiaEscarro baciloscopiaEscarro, RadiografiaDoTorax radiografiaDoTorax, HIV hiv, Histopatologia histopatologia, Cultura cultura, TesteMolecularRápidoTB testeMolecularRápidoTB, TestedeSensibilidade testeDeSensibilidade, MododeEntrada modoDeEntrada, ModoDetectacaoCasoNovo modoDetectacaoCasoNovo, Baciloscopia baciloscopia) {
        this.dataInvestigação = dataInvestigação;
        Ocupação = ocupação;
        this.atividade = atividade;
        this.dadosDoExame = dadosDoExame;
        this.tipoLamina = tipoLamina;
        this.sintomas = sintomas;
        this.dataExame = dataExame;
        this.resultadoExame = resultadoExame;
        this.parasitasMetroCubico = parasitasMetroCubico;
        this.tipoEntrada = tipoEntrada;
        this.formaTuberculose = formaTuberculose;
        this.localExtrapulmonar = localExtrapulmonar;
        this.baciloscopiaEscarro = baciloscopiaEscarro;
        this.radiografiaDoTorax = radiografiaDoTorax;
        this.hiv = hiv;
        this.histopatologia = histopatologia;
        this.cultura = cultura;
        this.testeMolecularRápidoTB = testeMolecularRápidoTB;
        this.testeDeSensibilidade = testeDeSensibilidade;
        this.modoDeEntrada = modoDeEntrada;
        this.modoDetectacaoCasoNovo = modoDetectacaoCasoNovo;
        this.baciloscopia = baciloscopia;
    }

    public LocalDate getDataInvestigação() {
        return dataInvestigação;
    }

    public void setDataInvestigação(LocalDate dataInvestigação) {
        this.dataInvestigação = dataInvestigação;
    }

    public String getOcupação() {
        return Ocupação;
    }

    public void setOcupação(String ocupação) {
        Ocupação = ocupação;
    }

    public AtividadeUltimos15Dias getAtividade() {
        return atividade;
    }

    public void setAtividade(AtividadeUltimos15Dias atividade) {
        this.atividade = atividade;
    }

    public String getDadosDoExame() {
        return dadosDoExame;
    }

    public void setDadosDoExame(String dadosDoExame) {
        this.dadosDoExame = dadosDoExame;
    }

    public TipoDeLamina getTipoLamina() {
        return tipoLamina;
    }

    public void setTipoLamina(TipoDeLamina tipoLamina) {
        this.tipoLamina = tipoLamina;
    }

    public Sintomas getSintomas() {
        return sintomas;
    }

    public void setSintomas(Sintomas sintomas) {
        this.sintomas = sintomas;
    }

    public LocalDate getDataExame() {
        return dataExame;
    }

    public void setDataExame(LocalDate dataExame) {
        this.dataExame = dataExame;
    }

    public ResultadoExame getResultadoExame() {
        return resultadoExame;
    }

    public void setResultadoExame(ResultadoExame resultadoExame) {
        this.resultadoExame = resultadoExame;
    }

    public float getParasitasMetroCubico() {
        return parasitasMetroCubico;
    }

    public void setParasitasMetroCubico(float parasitasMetroCubico) {
        this.parasitasMetroCubico = parasitasMetroCubico;
    }

    public TipoEntrada getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(TipoEntrada tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public FormaTuberculose getFormaTuberculose() {
        return formaTuberculose;
    }

    public void setFormaTuberculose(FormaTuberculose formaTuberculose) {
        this.formaTuberculose = formaTuberculose;
    }

    public LocalExtrapulmonar getLocalExtrapulmonar() {
        return localExtrapulmonar;
    }

    public void setLocalExtrapulmonar(LocalExtrapulmonar localExtrapulmonar) {
        this.localExtrapulmonar = localExtrapulmonar;
    }

    public BaciloscopiaEscarro getBaciloscopiaEscarro() {
        return baciloscopiaEscarro;
    }

    public void setBaciloscopiaEscarro(BaciloscopiaEscarro baciloscopiaEscarro) {
        this.baciloscopiaEscarro = baciloscopiaEscarro;
    }

    public RadiografiaDoTorax getRadiografiaDoTorax() {
        return radiografiaDoTorax;
    }

    public void setRadiografiaDoTorax(RadiografiaDoTorax radiografiaDoTorax) {
        this.radiografiaDoTorax = radiografiaDoTorax;
    }

    public HIV getHiv() {
        return hiv;
    }

    public void setHiv(HIV hiv) {
        this.hiv = hiv;
    }

    public Histopatologia getHistopatologia() {
        return histopatologia;
    }

    public void setHistopatologia(Histopatologia histopatologia) {
        this.histopatologia = histopatologia;
    }

    public Cultura getCultura() {
        return cultura;
    }

    public void setCultura(Cultura cultura) {
        this.cultura = cultura;
    }

    public TesteMolecularRápidoTB getTesteMolecularRápidoTB() {
        return testeMolecularRápidoTB;
    }

    public void setTesteMolecularRápidoTB(TesteMolecularRápidoTB testeMolecularRápidoTB) {
        this.testeMolecularRápidoTB = testeMolecularRápidoTB;
    }

    public TestedeSensibilidade getTesteDeSensibilidade() {
        return testeDeSensibilidade;
    }

    public void setTesteDeSensibilidade(TestedeSensibilidade testeDeSensibilidade) {
        this.testeDeSensibilidade = testeDeSensibilidade;
    }

    public MododeEntrada getModoDeEntrada() {
        return modoDeEntrada;
    }

    public void setModoDeEntrada(MododeEntrada modoDeEntrada) {
        this.modoDeEntrada = modoDeEntrada;
    }

    public ModoDetectacaoCasoNovo getModoDetectacaoCasoNovo() {
        return modoDetectacaoCasoNovo;
    }

    public void setModoDetectacaoCasoNovo(ModoDetectacaoCasoNovo modoDetectacaoCasoNovo) {
        this.modoDetectacaoCasoNovo = modoDetectacaoCasoNovo;
    }

    public Baciloscopia getBaciloscopia() {
        return baciloscopia;
    }

    public void setBaciloscopia(Baciloscopia baciloscopia) {
        this.baciloscopia = baciloscopia;
    }

}
