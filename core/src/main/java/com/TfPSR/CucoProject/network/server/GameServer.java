package com.TfPSR.CucoProject.network.server;

import com.TfPSR.CucoProject.network.protocol.InputPacket;
import com.TfPSR.CucoProject.network.protocol.NetworkConfig;
import com.TfPSR.CucoProject.network.protocol.PacketType;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class GameServer {
    DatagramSocket socket;
    boolean serverRunning = false;

    public GameServer() {
    }

    public void start() throws IOException {
        this.socket = new DatagramSocket(NetworkConfig.SERVER_PORT);
        System.out.println("The server started to run");
        serverRunning = true;
        while (serverRunning){
            byte[] buf = new byte[InputPacket.SIZE]; // 1 del tipo, 1 del id, el resto de booleanos para las teclas
            DatagramPacket dataPacket = new DatagramPacket(buf, buf.length);
            socket.receive(dataPacket);
            handlePacket(dataPacket, buf[0]);
        }

    }


    public void handlePacket(DatagramPacket data, byte packetType){
        if(packetType == PacketType.CONNECT){
            System.out.println("El cliente quiere conectarse");
        }else if(packetType == PacketType.INPUT){
            System.out.println("El cliente quiere enviar un paquete");
        }

    }
}
