package br.gov.sp.fatec.ecommerce.service;

import br.gov.sp.fatec.ecommerce.entity.Cliente;

public interface ClienteService {
    
    public Cliente criarCliente(String nome, String email, Integer idade, String pedido, Integer valor);

}
