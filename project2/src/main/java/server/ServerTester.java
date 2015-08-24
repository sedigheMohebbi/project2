package server;

import exception.JSonParseException;

public class ServerTester {
    public static void main(String[] args) {
        JsonParser jsonParser = new JsonParser();
        Server server = null;
        try {
            server = jsonParser.parseJson();
            if (server != null) {

                server.connectionToTerminal();
            } else {
                System.err.println("json file is null");
            }
        } catch (JSonParseException e) {
            System.err.println(e.getMessage());
        }


    }
}

