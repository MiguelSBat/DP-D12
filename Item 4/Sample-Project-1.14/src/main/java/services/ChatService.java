
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ChatRepository;
import domain.Chat;

@Service
@Transactional
public class ChatService {

	//Managed Repository ----
	@Autowired
	private ChatRepository	chatRepository;


	//Constructors
	public ChatService() {
		super();
	}

	public Chat create() {
		Chat result;

		result = new Chat();

		return result;
	}

	public Collection<Chat> findAll() {
		Collection<Chat> result;

		result = this.chatRepository.findAll();

		return result;
	}

	public void delete(final Chat chat) {

		this.chatRepository.delete(chat);

	}

	public Chat save(final Chat chat) {
		Chat result;

		chat.setDate(new Date());
		result = this.chatRepository.save(chat);
		return result;
	}

	public Chat findOne(final int chatId) {
		Chat result;

		result = this.chatRepository.findOne(chatId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.chatRepository.flush();
	}

	public Collection<Chat> findByActorsId(final int user1Id, final int user2Id) {
		Collection<Chat> result;

		result = this.chatRepository.findByActorsId(user1Id, user2Id);

		return result;
	}

}
