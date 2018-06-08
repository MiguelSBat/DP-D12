
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.FacturationData;

@Component
@Transactional
public class FacturationDataToStringConverter implements Converter<FacturationData, String> {

	@Override
	public String convert(final FacturationData actor) {
		String result;

		if (actor == null)
			result = null;
		else
			result = String.valueOf(actor.getId());

		return result;
	}

}
