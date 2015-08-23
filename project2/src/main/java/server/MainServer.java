package server;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) throws IOException, ParseException {
        JsonParser jsonParser = new JsonParser();
        Server server = jsonParser.parseJson();
        server.ConnectionToTerminal();
    }
}
