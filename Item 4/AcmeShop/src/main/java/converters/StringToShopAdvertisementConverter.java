
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.ShopAdvertisementRepository;
import domain.ShopAdvertisement;

@Component
@Transactional
public class StringToShopAdvertisementConverter implements Converter<String, ShopAdvertisement> {

	@Autowired
	private ShopAdvertisementRepository	userRepository;


	@Override
	public ShopAdvertisement convert(final String text) {
		ShopAdvertisement result;
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
