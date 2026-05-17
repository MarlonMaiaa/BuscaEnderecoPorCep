public record Endereco(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf,
        String ddd
) {
    @Override
    public String toString() {
        return  "\n==========================================\n" +
                "CEP:         " + cep + "\n" +
                "Logradouro:  " + logradouro + "\n" +
                "Complemento: " + (complemento == null || complemento.isEmpty() ? "nullo" : complemento) + "\n" +
                "Bairro:      " + bairro + "\n" +
                "Cidade:      " + localidade + "\n" +
                "UF:          " + uf + "\n" +
                "DDD:         " + ddd +
                "\n==========================================";
    }
}
