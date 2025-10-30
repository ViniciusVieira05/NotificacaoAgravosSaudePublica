package entidades.dados;

import enums.paciente.Zona;

public class DadosResidencias {

    private String uf;
    private String municipio;
    private String distrito;
    private String bairro;
    private String logradouro;
    private String numero;
    private String cep;
    private String telefone;
    private Zona zona;

    public DadosResidencias(){

    }

    public DadosResidencias(String uf, String municipio, String distrito, String bairro, String logradouro, String numero, String cep, String telefone, Zona zona){
        this.uf = uf;
        this.municipio = municipio;
        this.distrito = distrito;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.telefone = telefone;
        this.zona = zona;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
}
