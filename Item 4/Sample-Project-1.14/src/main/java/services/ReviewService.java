
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ReviewRepository;
import domain.Review;

@Service
@Transactional
public class ReviewService {

	//Managed Repository ----
	@Autowired
	private ReviewRepository	reviewRepository;


	//Constructors
	public ReviewService() {
		super();
	}

	public Review create() {
		Review result;

		result = new Review();

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

}