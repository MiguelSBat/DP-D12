
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class ShipmentAddress extends DomainEntity {

	private String	country;
	private String	city;
	private String	postalCode;
	private String	address;


	@NotBlank
	public String getCountry() {
		return this.country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	@NotBlank
	public String getCity() {
		return this.city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	@NotBlank
	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(final String postalCode) {
		this.postalCode = postalCode;
	}

	@NotBlank
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}


	//Relationships

	private Ticket	ticket;
	private User	user;


	@OneToOne
	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(final Ticket ticket) {
		this.ticket = ticket;
	}

	@ManyToOne
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
