
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.BusinessInfoRepository;
import domain.BusinessInfo;

@Component
@Transactional
public class StringToBusinessInfoConverter implements Converter<String, BusinessInfo> {

	@Autowired
	private BusinessInfoRepository	userRepository;


	@Override
	public BusinessInfo convert(final String text) {
		BusinessInfo result;
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
