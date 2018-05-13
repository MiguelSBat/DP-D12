
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ShippingInfo;

@Component
@Transactional
public class ShippingInfoToStringConverter implements Converter<ShippingInfo, String> {

	@Override
	public String convert(final ShippingInfo actor) {
		String result;

		if (actor == null)
			result = null;
		else
			result = String.valueOf(actor.getId());

		return result;
	}

}
