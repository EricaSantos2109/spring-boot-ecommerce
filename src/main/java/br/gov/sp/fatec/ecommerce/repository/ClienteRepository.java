package br.gov.sp.fatec.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.ecommerce.entity.Cliente;

//<Cliente, Long> indica a entidade e o tipo da chave primária
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    //inicio da query method

    @Query("select c from Cliente c where c.nome =?1")
    public Cliente buscaClientePorNome(String nome);

    @Query("select c from Cliente c where c.nome =?1 and c.email = ?2")
    public Cliente buscaClientePorNomeEmail(String nome, String email);

    @Query("select c from Cliente c inner join c.pedidos p where p.nome = ?1")
    public List<Cliente> buscaClientePorNomePedido(String pedido);
}