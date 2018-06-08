
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Business;

@Component
@Transactional
public class BusinessToStringConverter implements Converter<Business, String> {

	@Override
	public String convert(final Business business) {
		String result;

		if (business == null)
			result = null;
		else
			result = String.valueOf(business.getId());

		return result;
	}

}
