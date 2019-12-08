package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Clilent {
    public static void main(String[] args) throws IOException {
        Scanner in=new Scanner(System.in);
        byte[] serverIP = new byte[4];
        serverIP[0] = (byte) 192;
        serverIP[1] = (byte) 168;
        serverIP[2] = (byte) 1;
        serverIP[3] = (byte) 107;
        while(true) {
            DatagramSocket UDPclilent = new DatagramSocket();
            String message = in.nextLine();
            byte[] sendbuffer = message.getBytes("utf-8");

            InetAddress severAddress = InetAddress.getByAddress(serverIP);
            DatagramPacket sendpacket = new DatagramPacket(sendbuffer, sendbuffer.length, severAddress, 1234);
            UDPclilent.send(sendpacket);
            //接收回复的消息
            byte[]receive=new byte[1024];
            DatagramPacket  receiverParket=new DatagramPacket(receive,receive.length);
            UDPclilent.receive(receiverParket);
            String responseMessage=new String(receiverParket.getData(),0,receiverParket.getLength(),"utf-8");
            System.out.println(responseMessage);





            UDPclilent.close();
        }
    }
}
