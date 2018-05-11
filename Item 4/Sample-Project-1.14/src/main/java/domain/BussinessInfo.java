
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Access(AccessType.PROPERTY)
public class BussinessInfo extends DomainEntity {

	private String	address;
	private String	City;
	private String	additionalInfo;
	private String	country;


	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getCity() {
		return this.City;
	}

	public void setCity(final String city) {
		this.City = city;
	}

	public String getAdditionalInfo() {
		return this.additionalInfo;
	}

	public void setAdditionalInfo(final String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}


	//Relationships

	private Bussiness	bussiness;


	@ManyToOne(optional = false)
	public Bussiness getBussiness() {
		return this.bussiness;
	}

	public void setBussiness(final Bussiness bussiness) {
		this.bussiness = bussiness;
	}

}
