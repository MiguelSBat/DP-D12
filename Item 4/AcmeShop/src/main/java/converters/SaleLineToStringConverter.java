
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.SaleLine;

@Component
@Transactional
public class SaleLineToStringConverter implements Converter<SaleLine, String> {

	@Override
	public String convert(final SaleLine actor) {
		String result;

		if (actor == null)
			result = null;
		else
			result = String.valueOf(actor.getId());

		return result;
	}

}
