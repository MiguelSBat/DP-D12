
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class User extends DomainEntity {

	private String				name;
	private String				surname;
	private String				phone;
	private Boolean				premium;
	private Integer				reputation;
	private Boolean				suspicious;
	private Collection<String>	photosURL;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@Pattern(regexp = "^\\+?\\d+$")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public Boolean isPremium() {
		return this.premium;
	}

	public void setPremium(final Boolean premium) {
		this.premium = premium;
	}

	@NotNull
	@Range(min = 1, max = 5)
	public Integer getReputation() {
		return this.reputation;
	}

	public void setReputation(final Integer reputation) {
		this.reputation = reputation;
	}

	public Boolean isSuspicious() {
		return this.suspicious;
	}

	public void setSuspicious(final Boolean suspicious) {
		this.suspicious = suspicious;
	}

	public Collection<String> getPhotosURL() {
		return this.photosURL;
	}

	public void setPhotosURL(final Collection<String> photosURL) {
		this.photosURL = photosURL;
	}


	//Relationships

	private ShoppingCart	shoppingCart;


	@NotNull
	public ShoppingCart getShoppingCart() {
		return this.shoppingCart;
	}

	public void setShoppingCart(final ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

}
