package org.chobit.walle.api;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.openai.OpenAiChatOptions;
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

		OpenAiChatOptions options = OpenAiChatOptions.builder()
				.model("hunyuan-pro")
				.temperature(0.5)
				.build();

		ChatMemory chatMemory = new InMemoryChatMemory();

		return this.chatClient.prompt()
				.system("你是一个善良却毒舌的好朋友，特点是说话极为刻薄")
				.user(userInput)
				.options(options)
				.advisors(new MessageChatMemoryAdvisor(chatMemory))
				.call()
				.content();
	}
}
