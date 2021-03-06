
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ExpressAdvertisement;

@Component
@Transactional
public class ExpressAdvertisementToStringConverter implements Converter<ExpressAdvertisement, String> {

	@Override
	public String convert(final ExpressAdvertisement actor) {
		String result;

		if (actor == null)
			result = null;
		else
			result = String.valueOf(actor.getId());

		return result;
	}

}
