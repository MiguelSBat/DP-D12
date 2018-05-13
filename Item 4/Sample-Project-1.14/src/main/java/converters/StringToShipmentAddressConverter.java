
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.ShipmentAddressRepository;
import domain.ShipmentAddress;

@Component
@Transactional
public class StringToShipmentAddressConverter implements Converter<String, ShipmentAddress> {

	@Autowired
	private ShipmentAddressRepository	userRepository;


	@Override
	public ShipmentAddress convert(final String text) {
		ShipmentAddress result;
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
