
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
	private ShippmentAddress		shippmentAddress;
	private ShippingInfo			shippingInfo;
	private FacturarionData			facturationData;
	private User					user;
	private Bussiness				bussiness;


	@OneToMany
	public Collection<SaleLine> getSaleLines() {
		return this.saleLines;
	}

	public void setSaleLines(final Collection<SaleLine> saleLines) {
		this.saleLines = saleLines;
	}
	@NotNull
	public ShippmentAddress getShippmentAddress() {
		return this.shippmentAddress;
	}

	public void setShippmentAddress(final ShippmentAddress shippmentAddress) {
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
	public FacturarionData getFacturationData() {
		return this.facturationData;
	}

	public void setFacturationData(final FacturarionData facturationData) {
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
	public Bussiness getBussiness() {
		return this.bussiness;
	}

	public void setBussiness(final Bussiness bussiness) {
		this.bussiness = bussiness;
	}

}
