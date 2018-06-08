
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.ShippingInfoRepository;
import domain.ShippingInfo;

@Component
@Transactional
public class StringToShippingInfoConverter implements Converter<String, ShippingInfo> {

	@Autowired
	private ShippingInfoRepository	userRepository;


	@Override
	public ShippingInfo convert(final String text) {
		ShippingInfo result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.userRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
