import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");

    public List<Map<String,String>> parse (String dataJson){
        Matcher matcher = REGEX_ITEMS.matcher(dataJson);
        if(!matcher.find()){
            throw new IllegalArgumentException("Não encontramos itens.");
        }

        String[] items = matcher.group(1).split("\\},\\{");
        List<Map<String,String>> data = new ArrayList<>();

        for(String item : items){
            Map<String,String> itemAttribute = new HashMap<>();
            Matcher matcherAttributesJson = REGEX_ATRIBUTOS_JSON.matcher(item);
            while(matcherAttributesJson.find()){
                String key = matcherAttributesJson.group(1);
                String value = matcherAttributesJson.group(2);
                itemAttribute.put(key, value);
            }
            data.add(itemAttribute);
        }
        return data;
    }
}
