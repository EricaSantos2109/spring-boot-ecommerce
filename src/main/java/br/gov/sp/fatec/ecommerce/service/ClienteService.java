package br.gov.sp.fatec.ecommerce.service;

import br.gov.sp.fatec.ecommerce.entity.Cliente;
import java.util.List;

public interface ClienteService {
    
    public Cliente criarCliente(String nome, String email, Integer idade, String pedido, Integer valor);

    public List<Cliente> buscarClientes();

    public Cliente buscarClientePorId(Long id);

    public Cliente buscarClientePorNome(String nome);

    public Cliente atualizarCliente(String nome, String email, Integer idade, Long id);

    public Cliente novoCliente(String nome, String email, Integer idade);
}
