
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Order extends DomainEntity {

	private Date	date;
	private String	status;


	@NotNull
	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}
	@NotNull
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}


	//Relationships

	private Collection<SaleLine>	saleLines;
	private ShipmentAddress		shippmentAddress;
	private ShippingInfo			shippingInfo;
	private FacturationData			facturationData;
	private User					user;
	private Business				bussiness;


	@OneToMany
	public Collection<SaleLine> getSaleLines() {
		return this.saleLines;
	}

	public void setSaleLines(final Collection<SaleLine> saleLines) {
		this.saleLines = saleLines;
	}
	@NotNull
	public ShipmentAddress getShippmentAddress() {
		return this.shippmentAddress;
	}

	public void setShippmentAddress(final ShipmentAddress shippmentAddress) {
		this.shippmentAddress = shippmentAddress;
	}

	@OneToOne(optional = true)
	public ShippingInfo getShippingInfo() {
		return this.shippingInfo;
	}

	public void setShippingInfo(final ShippingInfo shippingInfo) {
		this.shippingInfo = shippingInfo;
	}

	@NotNull
	public FacturationData getFacturationData() {
		return this.facturationData;
	}

	public void setFacturationData(final FacturationData facturationData) {
		this.facturationData = facturationData;
	}

	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	@ManyToOne(optional = false)
	public Business getBussiness() {
		return this.bussiness;
	}

	public void setBussiness(final Business bussiness) {
		this.bussiness = bussiness;
	}

}
