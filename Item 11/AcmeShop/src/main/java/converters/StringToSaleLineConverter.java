
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.SaleLineRepository;
import domain.SaleLine;

@Component
@Transactional
public class StringToSaleLineConverter implements Converter<String, SaleLine> {

	@Autowired
	private SaleLineRepository	userRepository;


	@Override
	public SaleLine convert(final String text) {
		SaleLine result;
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
