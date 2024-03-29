package br.com.kennobi.cursorest;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.kennobi.cursorest.domain.Categoria;
import br.com.kennobi.cursorest.domain.Cidade;
import br.com.kennobi.cursorest.domain.Cliente;
import br.com.kennobi.cursorest.domain.Endereco;
import br.com.kennobi.cursorest.domain.Estado;
import br.com.kennobi.cursorest.domain.ItemPedido;
import br.com.kennobi.cursorest.domain.Pagamento;
import br.com.kennobi.cursorest.domain.PagamentoComBoleto;
import br.com.kennobi.cursorest.domain.PagamentoComCartao;
import br.com.kennobi.cursorest.domain.Pedido;
import br.com.kennobi.cursorest.domain.Produto;
import br.com.kennobi.cursorest.domain.enums.EstadoPagamento;
import br.com.kennobi.cursorest.domain.enums.TipoCliente;
import br.com.kennobi.cursorest.repositories.CategoriaRepository;
import br.com.kennobi.cursorest.repositories.CidadeRepository;
import br.com.kennobi.cursorest.repositories.ClienteRepository;
import br.com.kennobi.cursorest.repositories.EnderecoRepository;
import br.com.kennobi.cursorest.repositories.EstadoRepository;
import br.com.kennobi.cursorest.repositories.ItemPedidoRepository;
import br.com.kennobi.cursorest.repositories.PagamentoRepository;
import br.com.kennobi.cursorest.repositories.PedidoRepository;
import br.com.kennobi.cursorest.repositories.ProdutoRepository;

@SpringBootApplication
public class CursorestApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursorestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, mesa e Banho");
		Categoria cat4 = new Categoria(null, "Vestuário");
		Categoria cat5 = new Categoria(null, "Eletrodoméstico");
		Categoria cat6 = new Categoria(null, "Perfumaria");		

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Toalha de Banho", 40.00);
		Produto p5 = new Produto(null, "Blusa de Moleton", 30.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		cat3.getProdutos().addAll(Arrays.asList(p4));
		cat4.getProdutos().addAll(Arrays.asList(p5));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat3));
		p5.getCategorias().addAll(Arrays.asList(cat4));

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3,p4,p5));

		Estado est1 = new Estado(null, "Minas Gerais", "MG");
		Estado est2 = new Estado(null, "São Paulo", "SP");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Erbenio Mota", "erbeniomota@gmail.com", "67518338300", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("8599732-0153","8599613-4656"));
		
		Endereco e1 = new Endereco(null, "Rua Francisco Darley", "36","Estrada Nova II", "Serpa", "61700-000", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua 84", "60","Setor H", "Jereissati II", "62800-000", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
		
		Pedido ped1 = new Pedido(null,sdf.parse("08/02/2023 14:00"),cli1, e1);
		Pedido ped2 = new Pedido(null,sdf.parse("09/02/2023 15:10"),cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("08/02/2023 14:00"),null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1,0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3,0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2,100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip2));
		p3.getItens().addAll(Arrays.asList(ip3));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
