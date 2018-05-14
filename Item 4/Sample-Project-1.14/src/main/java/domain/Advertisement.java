
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Advertisement extends DomainEntity {

	private Date				publicationDate;
	private Date				endDate;
	private Double				price;
	private Collection<String>	tags;


	public Date getPublicationDate() {
		return this.publicationDate;
	}

	public void setPublicationDate(final Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(final Double price) {
		this.price = price;
	}

	public Collection<String> getTags() {
		return this.tags;
	}

	public void setTag(final Collection<String> tags) {
		this.tags = tags;
	}


	//Relationships

	private Item		item;
	private SaleLine	saleLine;
	private Business	business;


	@ManyToOne(optional = false)
	public Item getItem() {
		return this.item;
	}

	public void setItem(final Item item) {
		this.item = item;
	}

	@OneToMany
	public SaleLine getSaleLine() {
		return this.saleLine;
	}

	public void setSaleLine(final SaleLine saleLine) {
		this.saleLine = saleLine;
	}

	@ManyToOne(optional = true)
	public Business getBusiness() {
		return this.business;
	}

	public void setBusiness(final Business business) {
		this.business = business;
	}

}
