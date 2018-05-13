
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ShoppingCart;

@Component
@Transactional
public class ShoppingCartToStringConverter implements Converter<ShoppingCart, String> {

	@Override
	public String convert(final ShoppingCart actor) {
		String result;

		if (actor == null)
			result = null;
		else
			result = String.valueOf(actor.getId());

		return result;
	}

}
