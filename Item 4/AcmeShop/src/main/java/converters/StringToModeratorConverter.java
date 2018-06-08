
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.ModeratorRepository;
import domain.Moderator;

@Component
@Transactional
public class StringToModeratorConverter implements Converter<String, Moderator> {

	@Autowired
	private ModeratorRepository	userRepository;


	@Override
	public Moderator convert(final String text) {
		Moderator result;
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
