
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Valoration;

@Component
@Transactional
public class ValorationToStringConverter implements Converter<Valoration, String> {

	@Override
	public String convert(final Valoration actor) {
		String result;

		if (actor == null)
			result = null;
		else
			result = String.valueOf(actor.getId());

		return result;
	}

}
