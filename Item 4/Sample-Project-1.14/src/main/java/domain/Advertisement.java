
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "endDate")
})
public class Advertisement extends DomainEntity {

	private Date				publicationDate;
	private Date				endDate;
	private Collection<String>	tags;


	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getPublicationDate() {
		return this.publicationDate;
	}

	public void setPublicationDate(final Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	@ElementCollection(targetClass = String.class)
	public Collection<String> getTags() {
		return this.tags;
	}

	public void setTags(final Collection<String> tags) {
		this.tags = tags;
	}


	//Relationships

	private Item					item;
	private Collection<SaleLine>	saleLines;
	private Business				business;


	@NotNull
	@ManyToOne(optional = false)
	public Item getItem() {
		return this.item;
	}

	public void setItem(final Item item) {
		this.item = item;
	}

	@OneToMany
	public Collection<SaleLine> getSaleLines() {
		return this.saleLines;
	}

	public void setSaleLines(final Collection<SaleLine> saleLines) {
		this.saleLines = saleLines;
	}

	@ManyToOne(optional = true)
	public Business getBusiness() {
		return this.business;
	}

	public void setBusiness(final Business business) {
		this.business = business;
	}

}
