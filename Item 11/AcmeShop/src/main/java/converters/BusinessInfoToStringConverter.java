
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.BusinessInfo;

@Component
@Transactional
public class BusinessInfoToStringConverter implements Converter<BusinessInfo, String> {

	@Override
	public String convert(final BusinessInfo businessInfo) {
		String result;

		if (businessInfo == null)
			result = null;
		else
			result = String.valueOf(businessInfo.getId());

		return result;
	}

}
