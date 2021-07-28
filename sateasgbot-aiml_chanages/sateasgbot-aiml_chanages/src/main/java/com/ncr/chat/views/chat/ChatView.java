package com.ncr.chat.views.chat;

import com.ncr.chat.views.main.MainView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.scopes.VaadinUIScope;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.artur.Avataaar;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Route(value = "chat", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Chat")
@CssImport("styles/views/chat/chat-view.css")
@Component
@Scope(VaadinUIScope.VAADIN_UI_SCOPE_NAME)
public class ChatView extends VerticalLayout {

    private final UI ui;
    private final MessageList messageList = new MessageList();
    private final TextField message = new TextField();
    private final Chat chatSession;
    private final ScheduledExecutorService executorService;

    public ChatView(Bot alice, ScheduledExecutorService executorService) {
        this.executorService = executorService;
        ui = UI.getCurrent();
        chatSession = new Chat(alice);

        message.setPlaceholder("Enter a message...");
        message.setSizeFull();

        Button send = new Button(VaadinIcon.ENTER.create(), event -> sendMessage());
        send.addClickShortcut(Key.ENTER);

        HorizontalLayout inputLayout = new HorizontalLayout(message, send);
        inputLayout.addClassName("inputLayout");

        add(messageList, inputLayout);
        expand(messageList);
        setSizeFull();
    }

    private void sendMessage() {
        String text = message.getValue();
        messageList.addMessage("You", new Avataaar("Name"), text, true);
        message.clear();

        executorService.schedule(() -> {
            String answer = null;
            if (text.equalsIgnoreCase("Hi Sate"))
                answer = "Hi, How can I help you?";
            else if (text.equalsIgnoreCase("I would like to activate ASG module in Sate"))
                answer = "Great! Will be more than Happy to assist you. \r\n " +
                        "Please answer the following questions so I can process your request.\r\n" +
                        "Please let me know where are you transaction logs saved.";
            else if(text.equalsIgnoreCase("C:\\user\\logs\\asg"))
                answer = "Great! you will have your scenarios ready in SATE in few seconds.";
            else
                answer = chatSession.multisentenceRespond(text);

            //if it is a file location then API call and update the answer value=status of the response

            String finalAnswer = answer;
            ui.access(() -> messageList.addMessage("SATE", new Avataaar("Alice2"), finalAnswer, false));
        }, new Random().ints(1000, 3000).findFirst().getAsInt(), TimeUnit.MILLISECONDS);
    }

}
