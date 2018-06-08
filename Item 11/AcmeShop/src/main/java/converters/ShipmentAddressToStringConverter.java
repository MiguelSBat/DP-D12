
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ShipmentAddress;

@Component
@Transactional
public class ShipmentAddressToStringConverter implements Converter<ShipmentAddress, String> {

	@Override
	public String convert(final ShipmentAddress actor) {
		String result;

		if (actor == null)
			result = null;
		else
			result = String.valueOf(actor.getId());

		return result;
	}

}
