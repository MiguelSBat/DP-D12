
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class ShoppingCart extends DomainEntity {

	//Relationships

	private Collection<SaleLine>	saleLines;


	@OneToMany
	public Collection<SaleLine> getSaleLines() {
		return this.saleLines;
	}

	public void setSaleLines(final Collection<SaleLine> saleLines) {
		this.saleLines = saleLines;
	}

}
