
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Review extends DomainEntity {

	private Date	date;
	private String	text;
	private Integer	score;


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

	@NotNull
	@Range(min = 0, max = 5)
	public Integer getScore() {
		return this.score;
	}

	public void setScore(final Integer score) {
		this.score = score;
	}


	//Relationships

	private ShopAdvertisement	shopAdvertisement;


	@ManyToOne(optional = false)
	public ShopAdvertisement getShopAdvertisement() {
		return this.shopAdvertisement;
	}

	public void setShopAdvertisement(final ShopAdvertisement shopAdvertisement) {
		this.shopAdvertisement = shopAdvertisement;
	}

}
