
package forms;

import org.hibernate.validator.constraints.NotBlank;

public class SpamWordForm {

	private String	spamWord;


	@NotBlank
	public String getSpamWord() {
		return this.spamWord;
	}

	public void setSpamWord(final String spamWord) {
		this.spamWord = spamWord;
	}
}
