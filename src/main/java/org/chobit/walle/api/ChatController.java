package org.chobit.walle.api;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
public class ChatController {

	private final ChatModel chatModel;

	@Autowired
	public ChatController(ChatModel chatModel) {
		this.chatModel = chatModel;
	}

	@GetMapping("/ai/generate")
	public Map<String, String> generate(@RequestParam(value = "message", defaultValue = "给我讲个笑话") String message) {
		return Map.of("generation", this.chatModel.call(message));
	}

	@GetMapping("/ai/generateStream")
	public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
		Prompt prompt = new Prompt(new UserMessage(message));
		return this.chatModel.stream(prompt);
	}
}
