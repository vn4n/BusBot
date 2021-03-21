public class BusBot {

    public static void main(String[] args) {

            MyBusBot myBusBot = new MyBusBot("when_traffic_bot","1621573472:AAEv3KQKTS8ie2N_NwSlsYXlTpa6C4iSDnY");
            myBusBot.botConnect();
        /*
        TrafficParser trafficParser = new TrafficParser();
        String res = trafficParser.getTraffic("https://yandex.ru/maps/213/moscow/stops/stop__9641611");
        System.out.println(res);
         */

    }
}
