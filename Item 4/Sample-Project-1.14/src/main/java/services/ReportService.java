
package services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
	@Autowired
	private UserService			userService;
	@Autowired
	private BusinessService		businessService;


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
	private boolean isExtraWeight(final int userId) {
		boolean result;
		Set<User> users;
		Set<Business> businesses;
		Collection<Business> auxBus;
		Collection<User> auxUsers;
		Actor principal;
		Actor actor;
		User user;
		Business business;

		principal = this.actorService.findByPrincipal();
		actor = this.actorService.findOne(userId);
		result = false;
		if (actor instanceof User) {
			user = (User) actor;
			auxUsers = this.userService.findUsersISoldThingsToThey(principal.getId());
			users = new HashSet<User>(auxUsers);
			if (principal instanceof User) {
				auxUsers = this.userService.findUsersTheySoldThingsToMy(principal.getId());
				users.addAll(auxUsers);
			}
			if (users.contains(user))
				result = true;
		} else if (actor instanceof Business && principal instanceof User) {
			business = (Business) actor;
			auxBus = this.businessService.findBusinessIbuyThings(principal.getId());
			businesses = new HashSet<Business>(auxBus);
			if (businesses.contains(business))
				result = true;
		}
		return result;
	}

	public Report save(final Report report) {
		Report result;
		Config config;
		Actor principal;

		Assert.isTrue(report.getActor() instanceof User || report.getActor() instanceof Business);

		config = this.configService.findConfiguration();
		principal = this.actorService.findByPrincipal();
		if (this.isExtraWeight(report.getActor().getId()))
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

	public Collection<Report> getReportsByReportedActor(final int id) {
		Collection<Report> result;
		result = this.reportRepository.getReportsByReportedActor(id);
		return result;
	}

	public Double avgReportsPerUser() {
		Double result = 0.0;
		Collection<Report> auxR;
		Collection<User> aux;
		Collection<Actor> auxa;
		auxa = this.actorService.getActorsByReport();
		aux = this.userService.findAll();
		auxa.retainAll(aux);
		for (final Actor a : auxa) {
			auxR = this.getReportsByReportedActor(a.getId());
			result = result + auxR.size();
		}
		return result / aux.size();
	}

	public Double avgReportsPerBusiness() {
		Double result = 0.0;
		Collection<Report> auxR;
		Collection<Business> aux;
		Collection<Actor> auxa;
		auxa = this.actorService.getActorsByReport();
		aux = this.businessService.findAll();
		auxa.retainAll(aux);
		for (final Actor a : auxa) {
			auxR = this.getReportsByReportedActor(a.getId());
			result = result + auxR.size();
		}
		return result / aux.size();
	}

}
