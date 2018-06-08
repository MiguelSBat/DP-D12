
package controllers.user;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ChatService;
import controllers.AbstractController;
import domain.Actor;
import domain.Chat;

@Controller
@RequestMapping("/user/chat")
public class ChatUserController extends AbstractController {

	//Service -----------------------------------------------------------------
	@Autowired
	private ChatService		chatService;

	@Autowired
	private ActorService	actorService;


	// Constructors -----------------------------------------------------------

	public ChatUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final int user2Id) {
		ModelAndView result;
		final Collection<Chat> chats;
		Chat chat;
		Actor principal;
		Actor receiver;

		receiver = this.actorService.findOne(user2Id);
		principal = this.actorService.findByPrincipal();
		chat = this.chatService.create();
		chat.setReceiver(receiver);
		chat.setSender(principal);
		chat.setDate(new Date());
		result = this.createEditModelAndView(chat);
		result.addObject("principalId", principal.getId());
		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Chat chat, final BindingResult binding) {
		ModelAndView result;
		int principalId;

		principalId = this.actorService.findByPrincipal().getId();
		if (binding.hasErrors())
			result = this.createEditModelAndView(chat);
		else
			try {

				this.chatService.save(chat);

				result = new ModelAndView("redirect:/user/chat/list.do?user2Id=" + chat.getReceiver().getId());

			} catch (final Throwable oops) {
				final String errorMessage = "chat.commit.error";

				result = this.createEditModelAndView(chat, errorMessage);
			}
		result.addObject("principalId", principalId);
		return result;
	}
	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Chat chat) {
		ModelAndView result;

		result = this.createEditModelAndView(chat, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Chat chat, final String message) {
		ModelAndView result;
		Collection<Chat> chats;

		Actor principal;
		if (message == null)
			result = new ModelAndView("chat/list");
		else
			result = new ModelAndView("redirect:/user/chat/list.do?user2Id=" + chat.getReceiver().getId());

		result = new ModelAndView("chat/list");
		result.addObject("chat", chat);
		result.addObject("message", message);
		principal = this.actorService.findByPrincipal();
		chats = this.chatService.findByActorsId(principal.getId(), chat.getReceiver().getId());
		result.addObject("chats", chats);

		result.addObject("requestURI", "user/chat/list.do?user2Id=" + chat.getReceiver().getId());

		return result;
	}

}
