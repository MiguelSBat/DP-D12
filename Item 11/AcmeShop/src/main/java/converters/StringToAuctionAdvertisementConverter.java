
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.AuctionAdvertisementRepository;
import domain.AuctionAdvertisement;

@Component
@Transactional
public class StringToAuctionAdvertisementConverter implements Converter<String, AuctionAdvertisement> {

	@Autowired
	private AuctionAdvertisementRepository	userRepository;


	@Override
	public AuctionAdvertisement convert(final String text) {
		AuctionAdvertisement result;
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
