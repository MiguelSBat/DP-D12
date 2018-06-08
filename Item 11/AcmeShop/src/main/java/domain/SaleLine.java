
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

	private Ticket			ticket;
	private ShoppingCart	shoppingCart;
	private Advertisement	advertisement;


	@ManyToOne(optional = true)
	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(final Ticket ticket) {
		this.ticket = ticket;
	}
	@ManyToOne(optional = false)
	public Advertisement getAdvertisement() {
		return this.advertisement;
	}

	public void setAdvertisement(final Advertisement advertisement) {
		this.advertisement = advertisement;
	}

	@ManyToOne(optional = true)
	public ShoppingCart getShoppingCart() {
		return this.shoppingCart;
	}

	public void setShoppingCart(final ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

}
