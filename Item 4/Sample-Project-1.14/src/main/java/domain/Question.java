
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Question extends DomainEntity {

	private Date	date;
	private String	text;


	@NotNull
	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	@NotBlank
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}


	//Relationships

	private User				user;
	private ShopAdvertisement	shopAdvertisement;


	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	@ManyToOne(optional = false)
	public ShopAdvertisement getShopAdvertisement() {
		return this.shopAdvertisement;
	}

	public void setShopAdvertisement(final ShopAdvertisement shopAdvertisement) {
		this.shopAdvertisement = shopAdvertisement;
	}

}
