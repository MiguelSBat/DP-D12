
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.FacturationDataRepository;
import domain.FacturationData;

@Component
@Transactional
public class StringToFacturationDataConverter implements Converter<String, FacturationData> {

	@Autowired
	private FacturationDataRepository	userRepository;


	@Override
	public FacturationData convert(final String text) {
		FacturationData result;
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
