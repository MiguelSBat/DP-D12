
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Bid extends DomainEntity {

	private Double	amount;


	@NotNull
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(final Double amount) {
		this.amount = amount;
	}


	//Relationships

	private AuctionAdvertisement	auctionAdvertisement;
	private User					user;


	@ManyToOne(optional = false)
	public AuctionAdvertisement getAuctionAdvertisement() {
		return this.auctionAdvertisement;
	}

	public void setAuctionAdvertisement(final AuctionAdvertisement auctionAdvertisement) {
		this.auctionAdvertisement = auctionAdvertisement;
	}

	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
