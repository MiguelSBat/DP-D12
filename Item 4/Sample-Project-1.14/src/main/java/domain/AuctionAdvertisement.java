
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class AuctionAdvertisement extends DomainEntity {

	private Double	startingPrice;
	private Double	instaBuyPrice;
	private Boolean	secret;


	@NotNull
	public Double getStartingPrice() {
		return this.startingPrice;
	}

	public void setStartingPrice(final Double startingPrice) {
		this.startingPrice = startingPrice;
	}

	public Double getInstaBuyPrice() {
		return this.instaBuyPrice;
	}

	public void setInstaBuyPrice(final Double instaBuyPrice) {
		this.instaBuyPrice = instaBuyPrice;
	}

	@NotNull
	public Boolean isSecret() {
		return this.secret;
	}

	public void setSecret(final Boolean secret) {
		this.secret = secret;
	}


	//Relationships

	private Collection<User>	users;


	@ManyToOne(optional = false)
	public Collection<User> getUsers() {
		return this.users;
	}

	public void setUsers(final Collection<User> users) {
		this.users = users;
	}

}
