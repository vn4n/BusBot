import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.util.HashMap;

@Component
public class MyBusBot extends TelegramLongPollingBot {
    @Autowired
    private TrafficParser trafficParser;
    @Value("${myBusBot.name}")
    private String botName;
    @Value("${myBusBot.token}")
    private String botToken;
    @Value("#{${myBusBot.stopsMap}}")
    private HashMap<String,String> stopsMap;


    @Override
    public void onUpdateReceived(Update update) {
        String result = "";
        Long chatId = update.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        String stopName = update.getMessage().getText();
        String urlForStop = this.getStopsMap().get(stopName);
        //String result = trafficParser.getTraffic("https://yandex.ru/maps/213/moscow/stops/stop__9641611");
        if (urlForStop != null) {
            result = trafficParser.getTraffic(urlForStop);
        }
        else {
            result = "[~Остановки~]:\n";
            for (String key : this.getStopsMap().keySet()){
                result += key + "\n";
            }
        }
        sendMessage.setText(result);
        try {
            execute(sendMessage);
        }
        catch (TelegramApiException telegramApiException){
            telegramApiException.printStackTrace();
        }

    }

    public HashMap<String, String> getStopsMap() {
        return stopsMap;
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
