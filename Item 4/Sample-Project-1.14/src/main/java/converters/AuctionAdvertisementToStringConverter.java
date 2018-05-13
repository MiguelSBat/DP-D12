
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.AuctionAdvertisement;

@Component
@Transactional
public class AuctionAdvertisementToStringConverter implements Converter<AuctionAdvertisement, String> {

	@Override
	public String convert(final AuctionAdvertisement auctionAdvertisement) {
		String result;

		if (auctionAdvertisement == null)
			result = null;
		else
			result = String.valueOf(auctionAdvertisement.getId());

		return result;
	}

}
