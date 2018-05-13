
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import domain.Report;

@Service
@Transactional
public class ReportService {

	//Managed Repository ----
	@Autowired
	private ReportRepository	reportRepository;


	//Constructors
	public ReportService() {
		super();
	}

	public Report create() {
		Report result;

		result = new Report();

		return result;
	}

	public Collection<Report> findAll() {
		Collection<Report> result;

		result = this.reportRepository.findAll();

		return result;
	}

	public void delete(final Report report) {

		this.reportRepository.delete(report);

	}

	public Report save(final Report report) {
		Report result;

		result = this.reportRepository.save(report);
		return result;
	}

	public Report findOne(final int reportId) {
		Report result;

		result = this.reportRepository.findOne(reportId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.reportRepository.flush();
	}

}
