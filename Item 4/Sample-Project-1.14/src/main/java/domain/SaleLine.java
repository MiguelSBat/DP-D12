
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class SaleLine extends DomainEntity {

	private Integer	amount;


	@NotNull
	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(final Integer amount) {
		this.amount = amount;
	}


	//Relationships

	private Order			order;
	private ShoppingCart	shoppingCart;


	@ManyToOne(optional = false)
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(final Order order) {
		this.order = order;
	}

	@ManyToOne(optional = false)
	public ShoppingCart getShoppingCart() {
		return this.shoppingCart;
	}

	public void setShoppingCart(final ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

}
