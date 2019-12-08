package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        Scanner in=new Scanner(System.in);

        //DatagramSocket(int port)构造数据报套接字并将其绑定在本地主机的指定端口
        //TCP和UDP端口分为3种
        /*
            知名端口  1^1023
            注册端口   1024^49151
            动态端口   49151^65535
         */
        //1.建立一个DatagramSocket
        DatagramSocket UDPServerSocket=new DatagramSocket(1234);

        //在这里必须创建一个byte的数组 因为datagramPacket用的是字节接收

        byte[]receice=new byte[1024];//接受文件的大小
        DatagramPacket receivePacket=new DatagramPacket(receice,receice.length);

        //2.等待客户端链接
        while(true) {
            UDPServerSocket.receive(receivePacket);
            InetAddress clientAddress = receivePacket.getAddress();
            System.out.println(clientAddress.getHostAddress() + receivePacket.getPort());
            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println(message);


            String sendMessage=in.nextLine();
            byte[]sendbuffer=sendMessage.getBytes();
            DatagramPacket sendpacket=new DatagramPacket(sendbuffer,sendbuffer.length,clientAddress,receivePacket.getPort());
            UDPServerSocket.send(sendpacket);
        }

    }
}
