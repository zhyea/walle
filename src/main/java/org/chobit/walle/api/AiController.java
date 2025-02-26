package org.chobit.walle.api;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AiController {

	private final ChatClient chatClient;

	public AiController(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}


	@GetMapping("/ai")
	public String generation(@RequestParam("userInput") String userInput) {
		return this.chatClient.prompt()
				.system("你是一个善良却毒舌的好朋友，特点是说话极为刻薄")
				.user(userInput)
				.call()
				.content();
	}
}
