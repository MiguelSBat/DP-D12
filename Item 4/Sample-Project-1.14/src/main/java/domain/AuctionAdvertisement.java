
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class AuctionAdvertisement extends Advertisement {

	private Double	startingPrice;
	private Double	instantBuyPrice;
	private Boolean	secret;


	@NotNull
	public Double getStartingPrice() {
		return this.startingPrice;
	}

	public void setStartingPrice(final Double startingPrice) {
		this.startingPrice = startingPrice;
	}

	@NotNull
	public Double getInstantBuyPrice() {
		return this.instantBuyPrice;
	}

	public void setInstantBuyPrice(final Double instantBuyPrice) {
		this.instantBuyPrice = instantBuyPrice;
	}

	@NotNull
	public Boolean isSecret() {
		return this.secret;
	}

	public void setSecret(final Boolean secret) {
		this.secret = secret;
	}


	//Relationships

	private User	user;


	@ManyToOne(optional = true)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
