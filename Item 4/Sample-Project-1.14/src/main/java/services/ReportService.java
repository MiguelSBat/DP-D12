
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import domain.Actor;
import domain.Business;
import domain.Config;
import domain.Report;
import domain.User;

@Service
@Transactional
public class ReportService {

	//Managed Repository ----
	@Autowired
	private ReportRepository	reportRepository;
	@Autowired
	private ConfigService		configService;
	@Autowired
	private ActorService		actorService;


	//Constructors
	public ReportService() {
		super();
	}

	public Report create() {
		Report result;

		result = new Report();
		result.setText("");
		result.setWeight(1);

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
	public boolean isExtraWeight() {
		boolean result;
		result = false;
		return result;
	}

	public Report save(final Report report) {
		Report result;
		Config config;
		Actor principal;

		Assert.isTrue(report.getActor() instanceof User || report.getActor() instanceof Business);

		config = this.configService.findConfiguration();
		principal = this.actorService.findByPrincipal();
		if (this.isExtraWeight())
			report.setWeight(config.getTransactionReportWeight());

		result = this.reportRepository.save(report);
		principal.getReports().add(result);
		this.actorService.save(principal);
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
