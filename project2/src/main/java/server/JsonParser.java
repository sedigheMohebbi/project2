package server;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonParser {
    public Server parseJson() throws IOException, ParseException {
        FileReader reader = new FileReader("./core.json");
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

        int port = ((Long) jsonObject.get("port")).intValue();
        JSONArray deposit = (JSONArray) jsonObject.get("deposit");
        Iterator iterator = deposit.iterator();
        List<Deposit> depositList = new ArrayList<Deposit>();
        while (iterator.hasNext()) {
            JSONObject depositObject = (JSONObject) iterator.next();
            String customer = (String) depositObject.get("customer");
            int id = Integer.parseInt((String) depositObject.get("id"));
            BigDecimal initialBalance = new BigDecimal(depositObject.get("initialBalance").toString().replace(",", ""));
            BigDecimal upperBound = new BigDecimal(depositObject.get("upperBound").toString().replace(",", ""));
            Deposit deposit1 = new Deposit(customer, id, initialBalance, upperBound);
            depositList.add(deposit1);
        }
        String outLog = (String) jsonObject.get("outLog");
        Server server = new Server(port, outLog, depositList);
        return server;


    }
}

