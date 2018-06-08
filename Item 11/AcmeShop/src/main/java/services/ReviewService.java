
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ReviewRepository;
import domain.Review;
import domain.User;

@Service
@Transactional
public class ReviewService {

	//Managed Repository ----
	@Autowired
	private ReviewRepository	reviewRepository;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private ConfigService		configService;


	//Constructors
	public ReviewService() {
		super();
	}

	public Review create() {
		Review result;

		result = new Review();
		final Date date = new Date();
		result.setDate(date);

		return result;
	}

	public Collection<Review> findAll() {
		Collection<Review> result;

		result = this.reviewRepository.findAll();

		return result;
	}

	public void delete(final Review review) {

		this.reviewRepository.delete(review);

	}

	public Review save(final Review review) {
		Review result;
		User user;
		user = (User) this.actorService.findByPrincipal();
		Assert.isTrue(user.getId() == review.getUser().getId());
		if (this.configService.isTaboo(review.getText()))
			user.setSuspicious(true);
		result = this.reviewRepository.save(review);
		return result;
	}

	public Review findOne(final int reviewId) {
		Review result;

		result = this.reviewRepository.findOne(reviewId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.reviewRepository.flush();
	}

	public Collection<Review> findByShopAdvertisement(final int id) {
		Collection<Review> result;
		result = this.reviewRepository.findByShopAdvertisement(id);
		return result;
	}

	public Double avgScoreOfShopAd(final int ID) {
		Double result;
		result = this.reviewRepository.avgScoreOfShopAd(ID);
		return result;
	}

}
