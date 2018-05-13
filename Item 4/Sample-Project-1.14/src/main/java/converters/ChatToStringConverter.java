
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Chat;

@Component
@Transactional
public class ChatToStringConverter implements Converter<Chat, String> {

	@Override
	public String convert(final Chat actor) {
		String result;

		if (actor == null)
			result = null;
		else
			result = String.valueOf(actor.getId());

		return result;
	}

}
