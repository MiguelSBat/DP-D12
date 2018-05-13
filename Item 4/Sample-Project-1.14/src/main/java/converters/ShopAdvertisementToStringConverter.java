
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ShopAdvertisement;

@Component
@Transactional
public class ShopAdvertisementToStringConverter implements Converter<ShopAdvertisement, String> {

	@Override
	public String convert(final ShopAdvertisement actor) {
		String result;

		if (actor == null)
			result = null;
		else
			result = String.valueOf(actor.getId());

		return result;
	}

}
