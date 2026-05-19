package in.digeshwar.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final ChatClient chatClient;
    public AiController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String question) {

        PromptTemplate template = new PromptTemplate("Explain {topic} for beginners");
        String response1 = chatClient.prompt(template.create(Map.of("topic", "Java Streams")))
                .call()
                .content();

        String response2 = chatClient.prompt(String.valueOf(template))
                .param("topic", "Spring Boot")
                .call()
                .content();

        return chatClient.prompt()
                .system("You are a Java teacher")
                .user(question)
                .call()
                .content();
    }
}