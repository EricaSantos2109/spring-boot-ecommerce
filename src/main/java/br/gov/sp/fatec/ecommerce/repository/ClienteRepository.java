package br.gov.sp.fatec.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.ecommerce.entity.Cliente;

//<Cliente, Long> indica a entidade e o tipo da chave primária
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    //inicio da query method
    //ContainsIgnoreCase ignora se é maiusculo ou minusculo
    public List<Cliente> findByNomeContainsIgnoreCase(String nome);

    //findBy encontra pelo paramentro passado
    public List<Cliente> findByNome(String nome);

    @Query("select c from Cliente c where c.nome =?1")
    public Cliente buscaClientePorNome(String nome);

    //autenticacao encontra pelo nome e pela senha
    public List<Cliente> findByNomeAndSenha(String nome, String senha);

    @Query("select c from Cliente c where c.nome =?1 and u.senha = ?2")
    public Cliente buscaUsuarioPorNomeESenha(String nome, String senha);

    //pega todos os clientes pelo nome que tenha autorizacao 
    public List<Cliente> findByAutorizacoesNome(String autorizacao);

    @Query("select c from Cliente c inner join c.autorizacoes a where a.nome = ?1")
    public List<Cliente> buscaPorNomeAutorizacao(String autorizacao);
}