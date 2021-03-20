package br.gov.sp.fatec.ecommerce.service;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.ecommerce.entity.Cliente;
import br.gov.sp.fatec.ecommerce.entity.Autorizacao;
import br.gov.sp.fatec.ecommerce.repository.AutorizacaoRepository;
import br.gov.sp.fatec.ecommerce.repository.ClienteRepository;

//como o component => onde vai ter a regra de negócio
@Service("segurancaService")
public class SegurancaServiceImpl implements SegurancaService {

    // instancia o repositorio entao posso usar o rep direto
    @Autowired
    private AutorizacaoRepository autRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    //tudo o que ocorre é uma transação
    @Transactional
    public Cliente criarCliente(String nome, String email, String senha, String autorizacao) {
        
        Autorizacao aut = autRepo.findByNome(autorizacao);
        if(aut == null){
            aut = new Autorizacao();
            aut.setNome(autorizacao);
            autRepo.save(aut);
        }

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setSenha(senha);
        cliente.setAutorizacoes(new HashSet<Autorizacao>());
        cliente.getAutorizacoes().add(aut);
        clienteRepo.save(cliente);
        return cliente;
    }
    
}
