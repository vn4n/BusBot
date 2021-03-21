import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
@AllArgsConstructor
@NoArgsConstructor
public class MyBusBot extends TelegramLongPollingBot {
    @Setter
    private String botName;
    @Setter
    private String botToken;

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        //String chatMessage = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        //PARSING
        TrafficParser trafficParser = new TrafficParser();
        String result = trafficParser.getTraffic("https://yandex.ru/maps/213/moscow/stops/stop__9641611");
        //END OF PARSING
        sendMessage.setText(result);
        //sendMessage.setText("Your message was received. Check it: "+chatMessage);
        try {
            execute(sendMessage);
        }
        catch (TelegramApiException telegramApiException){
            telegramApiException.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    public void botConnect(){
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this);
        }
        catch (TelegramApiRequestException telegramApiRequestException){
            telegramApiRequestException.printStackTrace();
        }
        catch (TelegramApiException telegramApiException){
            telegramApiException.printStackTrace();
        }


    }
}
