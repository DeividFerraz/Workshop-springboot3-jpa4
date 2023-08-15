package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity/*A anotação @Entity é utilizada para informar que 
uma classe também é uma entidade. A partir disso, a JPA 
estabelecerá a ligação entre a entidade e uma tabela de 
mesmo nome no banco de dados, onde os dados de objetos desse 
tipo poderão ser persistidos. Uma entidade representa, na 
Orientação a Objetos, uma tabela do banco de dados, e cada 
instância dessa entidade representa uma linha dessa tabela. 
Caso a tabela possua um nome diferente, podemos estabelecer 
esse mapeamento com a anotação @Table, a qual será explorada 
em outra documentação.*/
@Table(name = "tb_user")
public class User implements Serializable {//usaado para q o objeto trafegue na rede e possa ser gravada em arquivos
	private static final long serialVersionUID = 1L;

	@Id//Falei pro JPA q o campo q é a chave primaria é o ID
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Essa anotation diz ao JPA que essa chave é autoIncremento
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;

	@JsonIgnore/*Ele é usado para que não de um looping infinito no
	pontman, ai aqui foi colocado acima da lista de Order para n acontecer*/
	@OneToMany(mappedBy = "client")/*Quando Temos uma 
	associação para muitos, o JPA carrega automaticamente 
	pendurados as classes juntas, no casso aqui o User 
	vai pendurado em cada pedido que ele estiver*/
	/*Esse nome esta na tabela Order,
	estou dizendo para ele que Um user tem varios orders, usando
	o atributo que esta na classe "Order" ou seja esse 
	muitos para um la do outro lado esta mappeado por 'client"*/
	private List<Order> orders = new ArrayList<>();/*Estou dizewndo
	que dentro da classe "User" pode ter uma lista de pedidos
	OneToMany, estou dizendo q 1 User pode ter varios orders"pedidos"*/
	
	public User() {
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrders() {//somente o get, por que quando é coleção não é interessante criar o set
		return orders;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}