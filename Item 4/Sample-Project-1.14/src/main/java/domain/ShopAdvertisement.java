
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class ShopAdvertisement extends Advertisement {

	private Integer	stock;


	@NotNull
	public Integer getStock() {
		return this.stock;
	}

	public void setStock(final Integer stock) {
		this.stock = stock;
	}


	//Relationships

	private Collection<Question>	questions;


	@OneToMany
	public Collection<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(final Collection<Question> questions) {
		this.questions = questions;
	}

}
