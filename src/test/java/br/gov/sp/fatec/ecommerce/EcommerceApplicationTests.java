package br.gov.sp.fatec.ecommerce;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.ecommerce.entity.Pedido;
import br.gov.sp.fatec.ecommerce.entity.Cliente;
import br.gov.sp.fatec.ecommerce.repository.PedidoRepository;
import br.gov.sp.fatec.ecommerce.repository.ClienteRepository;
import br.gov.sp.fatec.ecommerce.service.ClienteService;

@SpringBootTest
@Transactional //cada metodo da classe abre uma transação nova e abre uma conexão
@Rollback //terminou o metodo ele não da commit
class EcommerceApplicationTests {

    @Autowired //sprint identiciar que precisa encontrar algo do tipo ClienteRepository
    private ClienteRepository cliRepo;

    @Autowired //sprint identiciar que precisa encontrar algo do tipo ClienteRepository
    private PedidoRepository pedRepo;

    @Autowired
    private ClienteService cliService;

	@Test
	void contextLoads() {
    }

@Test
    void testaInsercao() {
        Cliente cli = new Cliente();
        cli.setNome("Erica3");
        cli.setEmail("erica3@erica.com");
        cli.setIdade(20);
        cli.setPedidos(new HashSet<Pedido>());
        Pedido ped = new Pedido();
        ped.setNome("pedido03");
        ped.setValor(200);
        pedRepo.save(ped);
        cli.getPedidos().add(ped);
        cliRepo.save(cli);  
        assertNotNull(cli.getId());     

    }

    @Test
    void testaPedido() {
        Cliente cli = cliRepo.findById(1L).get();         
        assertEquals("pedido01", cli.getPedidos().iterator().next().getNome());     

    }

   

    @Test
    void testaCliente() {
        Pedido ped = pedRepo.findById(1L).get();         
        assertEquals("Erica", ped.getClientes().iterator().next().getNome());     

    }    

    @Test
    void testaBuscaClientePorNomeEmailQuery() {
        Cliente cli = cliRepo.buscaClientePorNomeEmail("Erica", "erica@email.com");
        assertNotNull(cli);    

    }

    @Test
    void testaBuscaClienteNomeQuery() {
        Cliente cli = cliRepo.buscaClientePorNome("Erica");
        assertNotNull(cli);    

    } 

    @Test
    void testaBuscaClienteNomePedidoQuery() {
        List<Cliente> clientes = cliRepo.buscaClientePorNomePedido("pedido01");
        assertFalse(clientes.isEmpty());    

    }

    @Test
    void testaServicoCriaCliente(){
        Cliente cli = cliService.criarCliente("Erica4", "erica4@email.com", 37, "pedido04", 400);
        assertNotNull(cli);
    }    

}