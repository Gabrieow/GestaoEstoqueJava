package model;

public class Fornecedor extends Pessoa implements IRegistravel { // remoer implementaçao da interface pessoa e criar FornecedorDAO para salvar e carregar fornecedores

    private int id;
    private String cnpj;
    private String razaoSocial;

    public Fornecedor(String nome, String telefone, String endereco, int id, String cnpj, String razaoSocial) {
        super(nome, telefone, endereco);
        this.id = id;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    @Override
    public String exibirDados() {
        return String.format(
            "Fornecedor #%d\nCNPJ: %s\nRazão Social: %s\nNome: %s\nTelefone: %s\nEndereço: %s\n",
            id,
            cnpj,
            razaoSocial,
            getNome(),
            getTelefone(),
            getEndereco()
        );
    }

    @Override
    public String registrar() 
    {
        return "Fornecedor registrado com sucesso!";
    }

    @Override
    public String atualizar() {
        return "Fornecedor atualizado com sucesso!";
    }

    @Override
    public String excluir() {
        return "Fornecedor excluído com sucesso!";
    }

}
