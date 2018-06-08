
package services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ConfigRepository;
import domain.Config;

@Service
@Transactional
public class ConfigService {

	//Managed Repository ----
	@Autowired
	private ConfigRepository	configRepository;


	//Constructors
	public ConfigService() {
		super();
	}

	public Config create() {
		Config result;

		result = new Config();

		return result;
	}

	public Collection<Config> findAll() {
		Collection<Config> result;

		result = this.configRepository.findAll();

		return result;
	}

	public void delete(final Config config) {

		this.configRepository.delete(config);

	}

	public Config save(final Config config) {
		Config result;

		result = this.configRepository.save(config);
		return result;
	}

	public Config findOne(final int configId) {
		Config result;

		result = this.configRepository.findOne(configId);
		Assert.notNull(result);

		return result;
	}

	public Config addSpamWord(final String spamWord) {
		Config config;
		Config result;
		Set<String> spamWords;

		config = this.findConfiguration();
		spamWords = new HashSet<String>(config.getSpamWords());
		spamWords.add(spamWord);
		config.setSpamWords(spamWords);
		result = this.save(config);

		return result;
	}
	public Config removeSpamWord(final String spamWord) {
		Config config;
		Config result;
		Collection<String> spamWords;

		config = this.findConfiguration();
		spamWords = config.getSpamWords();
		spamWords.remove(spamWord);
		config.setSpamWords(spamWords);
		result = this.save(config);

		return result;
	}

	public Config findConfiguration() {
		Config result;
		result = this.configRepository.findAll().get(0);
		return result;
	}

	public void flush() {
		this.configRepository.flush();
	}
	public Boolean isTaboo(final String string) {
		Boolean result;
		Collection<String> tabooWords;

		tabooWords = this.findConfiguration().getSpamWords();
		result = false;
		for (final String tabooWord : tabooWords)
			if (string.contains(tabooWord))
				result = true;

		return result;
	}
}
