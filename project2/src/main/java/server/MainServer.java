package server;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) throws IOException, ParseException {
        //        Server server = new Server();
        //        server.ConnectionToTerminal();

        JsonParser jsonParser = new JsonParser();
        Server server = jsonParser.parsJson();
        server.ConnectionToTerminal();
    }
}
