package non.modules;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;
import org.mozilla.javascript.Function;

import non.Non;
import non.Buffer;

public class NonNetwork extends Module {
    public class ScriptListener extends Listener {
        public void connected (Connection connection) {
            Non.script.call(connected, connection);
        }
        
        public void received (Connection connection, Object object) {
            if (!(object instanceof byte[])) return;
            Non.script.call(received, connection, (byte[])object);
        }

        public void disconnected (Connection connection) {
            Non.script.call(disconnected, connection);
        }
    }
    
    public Function connected, disconnected, received;
    
    public Client client() { 
        Client client = new Client();
        register(client);
        client.addListener(new ScriptListener());
        
        return client;
    }
    
    public Server server() { 
        Server server = new Server();
        register(server);
        server.addListener(new ScriptListener());

        return server;
    }
    
    public Buffer buffer() {
        return new Buffer();
    }
    
    public Buffer buffer(byte[] data) {
        return new Buffer(data);
    }
    
    private void register (EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(byte[].class);
    }
}