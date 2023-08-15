package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.educandoweb.course.entities.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant moment;
	
	private Integer orderStatus;

	@ManyToOne/*Serve para fazer a relação de chaves estrangeiras, 
	estou dizendo que tenho uma relação de tabelas de muitos para um 
	com a classe User*/
	@JoinColumn(name = "client_id")/*Estou dizendo tmb que nessa chave estrangeira
	vai a chave primaria da classe "User" ou seja o ID POR ISSO o que fica amostra la na
	tabela no campo "client_id" é um id.
	Aqui estou dizendo o nome 
	da chave estrangeira que vai ter no banco de dados*/
	private User client;/*criei a variavel client, e estou flnd
	q 1 cliente pode ter varios pedidos "orders" por isso o ManyToOne
	Muitos pedidos para um cliente, e salvo o ID do pedido na coluna cliente Id*/

	@OneToMany(mappedBy = "id.order")//quero dizer que meu pedido tem uma lista de itens
	/*o id.order, quer dizer que eu estou acessando o atributo Id ou seja a Variavel ID
	 * e o .order estou acessando os pedidos que esta relacionado a esse Id e 
	 * vai vir grudados nesse campo Id no json*/
	private Set<OrderItem> items = new HashSet<>();//coleção de itemns relacionado a orderitem
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)//Associação 1 para um com a classe paymente e usando o mappedBy para dizer que aquela classe é dependente dessa 	//Mapeando com o mesmo Id é obrigatoria por esse Cascade .all
	//mapeando relação 1 para 1 para ter o mesmo id.
	private Payment payment;
	//estou dizendo q um pedido tem um pagamento
	
	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}
	
	
	public Set<OrderItem> getItems() {
		return items;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Double getTotal() {
		double sum = 0.0;
		for (OrderItem x : items) {
			sum += x.getSubTotal();
		}
		return sum;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}