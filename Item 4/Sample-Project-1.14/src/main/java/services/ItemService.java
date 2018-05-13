
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ItemRepository;
import domain.Item;

@Service
@Transactional
public class ItemService {

	//Managed Repository ----
	@Autowired
	private ItemRepository	itemRepository;


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

		result = this.itemRepository.save(item);
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

}
