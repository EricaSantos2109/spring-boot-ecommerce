package br.gov.sp.fatec.ecommerce.service;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.ecommerce.entity.Cliente;
import br.gov.sp.fatec.ecommerce.entity.Pedido;
import br.gov.sp.fatec.ecommerce.repository.PedidoRepository;
import br.gov.sp.fatec.ecommerce.repository.ClienteRepository;

//como o component => onde vai ter a regra de negócio
@Service("ClienteService")
public class ClienteServiceImpl implements ClienteService {

    // instancia o repositorio entao posso usar o rep direto
    @Autowired
    private PedidoRepository pedRepo;

    @Autowired
    private ClienteRepository cliRepo;

    //tudo o que ocorre é uma transação
    @Transactional
    public Cliente criarCliente(String nome, String email, Integer idade, String pedido, Integer valor) {
        
        Pedido ped = pedRepo.buscaPedidoPorNome(pedido);
        if(ped == null) {
            ped = new Pedido();
            ped.setNome(pedido);
            ped.setValor(valor);
            pedRepo.save(ped);
        }

        Cliente cli = new Cliente();
        cli.setNome(nome);
        cli.setEmail(email);
        cli.setIdade(idade);
        cli.setPedidos(new HashSet<Pedido>()); //pegando a lista de pedidos do cliente e atribuindo o pedido
        cli.getPedidos().add(ped);
        cliRepo.save(cli);
        return cli;
    }
    
}
