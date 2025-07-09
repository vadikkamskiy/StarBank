package com.skypro.StarBank.listener;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class TGListener {
    private final TelegramBot telegramBot;

    public TGListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }
    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                if (update.message() != null && update.message().text() != null) {
                    String chatId = update.message().chat().id().toString();
                    String text = update.message().text();
                    if (text.startsWith("/recomendations")) {
                        String[] parts = text.split(" ", 2);
                        if (parts.length == 2) {
                            String userName = parts[1];
                            //TODO: Здесь должна быть логика получения рекомендаций для пользователя
                            String response = "Рекомендации для пользователя " + userName + " : " + userName;
                            telegramBot.execute(new SendMessage(chatId, response));
                        } else {
                            telegramBot.execute(new SendMessage(chatId, "Пожалуйста, укажите имя пользователя после команды /recomendations"));
                        }
                    } else {
                        switch (text) {
                            case "/start" -> telegramBot.execute(new SendMessage(chatId, "Добро пожаловать в StarBank!"));
                            case "/help" -> telegramBot.execute(new SendMessage(chatId,
                                    """
                                    Доступные команды:
                                    /start - Начать
                                    /help - Помощь
                                    /recomendations <userName> - Получить рекомендации для пользователя
                                    """));
                            default -> telegramBot.execute(new SendMessage(chatId, "Неизвестная команда. Введите /help для получения списка команд."));
                        }
                    }
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
