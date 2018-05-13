
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class ShippingInfo extends DomainEntity {

	private Integer	trackingNumber;
	private String	company;
	private String	additionalInfo;


	public Integer getTrackingNumber() {
		return this.trackingNumber;
	}

	public void setTrackingNumber(final Integer trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(final String company) {
		this.company = company;
	}

	public String getAdditionalInfo() {
		return this.additionalInfo;
	}

	public void setAdditionalInfo(final String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

}