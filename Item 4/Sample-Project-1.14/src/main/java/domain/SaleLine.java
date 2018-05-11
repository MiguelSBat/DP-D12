
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class SaleLine extends DomainEntity {

	private Integer	amount;


	@NotNull
	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(final Integer amount) {
		this.amount = amount;
	}

}
