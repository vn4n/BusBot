import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class TrafficParser {

    public String getTraffic(String url){
        Elements elements = null;
        String resultTraffic = "";
        try {
            Document document = Jsoup.connect(url).get();
           // elements = document.select("body > div.body > div.app > div.sidebar-container > div.sidebar-view._name_masstransit._shown._view_full > div.sidebar-view__panel > div.scroll._width_narrow > div.scroll__container > div.scroll__content > div.masstransit-stop-panel-view._type_urban");// > div > div.qhaw__dfdabbffefde > div.jccjm__bceccebafcae > div.masstransit-stop-panel-view__brief-schedule > div > ul > li:nth-child(1)");
            elements = document.select(new Evaluator.AttributeWithValue("class","masstransit-brief-schedule-view__vehicles"));
            Element nodeElement = elements.get(0);
            int size = nodeElement.childNodeSize();
            elements = document.select(new Evaluator.AttributeWithValue("class","card-title-view__wrapper"));
            String busStopName = elements.get(0).select("h1.card-title-view__title").text();
            resultTraffic = "[ "+ busStopName + " ]" + "\n";
            for (int i = 0; i < size; i++){
               // nodeElement.chi
                Elements childNodes = nodeElement.child(i).select(new Evaluator.AttributeWithValue("class","masstransit-vehicle-snippet-view__row"));
                if (childNodes != null){
                    String time = childNodes.select("div.masstransit-prognoses-view__title._live > span.masstransit-prognoses-view__title-text").text();
                    if (time.equals("")) continue;
                    String busInfo = childNodes.select("div.masstransit-vehicle-snippet-view__info > a.masstransit-vehicle-snippet-view__name").text();
                    //System.out.println(busInfo + ": " + time);
                    resultTraffic += busInfo + ": " + time + "\n";
                }
                //String str = childNodes.text();
                //System.out.printf(str);
            }



        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
        return resultTraffic;
    }



}
