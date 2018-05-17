
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ItemRepository;
import domain.Actor;
import domain.Business;
import domain.Item;
import domain.User;

@Service
@Transactional
public class ItemService {

	//Managed Repository ----
	@Autowired
	private ItemRepository	itemRepository;

	@Autowired
	private ActorService	actorService;


	//Constructors
	public ItemService() {
		super();
	}

	public Item create() {
		Item result;

		result = new Item();

		return result;
	}

	public Collection<Item> findAll() {
		Collection<Item> result;

		result = this.itemRepository.findAll();

		return result;
	}

	public void delete(final Item item) {

		this.itemRepository.delete(item);

	}

	public Item save(final Item item) {
		Item result;
		Actor actor;

		Assert.isTrue(this.actorService.isLogged());
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof User || actor instanceof Business);
		if (actor instanceof User) {
			item.setUser((User) actor);
			result = this.itemRepository.save(item);
		} else {
			item.setBusiness((Business) actor);
			result = this.itemRepository.save(item);
		}

		return result;
	}

	public Collection<Item> findByUser(final int userId) {
		Collection<Item> result;

		result = this.itemRepository.getItemsByUser(userId);

		return result;
	}

	public Item findOne(final int itemId) {
		Item result;

		result = this.itemRepository.findOne(itemId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.itemRepository.flush();
	}

	public Collection<Item> findByPrincipal() {
		Collection<Item> result;
		Actor actor;

		actor = this.actorService.findByPrincipal();
		result = this.findByUser(actor.getId());

		return result;
	}

}
