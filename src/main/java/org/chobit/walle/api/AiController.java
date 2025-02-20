package org.chobit.walle.api;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AiController {

	private final ChatClient chatClient;

	public AiController(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}


	@GetMapping("/ai")
	String generation(String userInput) {
		return this.chatClient.prompt()
				.user(userInput)
				.call()
				.content();
	}
}
