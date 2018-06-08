
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.ExpressAdvertisementRepository;
import domain.ExpressAdvertisement;

@Component
@Transactional
public class StringToExpressAdvertisementConverter implements Converter<String, ExpressAdvertisement> {

	@Autowired
	private ExpressAdvertisementRepository	userRepository;


	@Override
	public ExpressAdvertisement convert(final String text) {
		ExpressAdvertisement result;
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
