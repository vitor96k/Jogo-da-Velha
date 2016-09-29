
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor {
    
    public static void main(String[] args) throws SocketException, IOException{
        
        int porta = 12345;   
        InetAddress ipP0,ipP1; 
		  InetAddress auxIp=InetAddress.getByName("localhost");
		  int auxPorta=1;
        int portaP1,portaP0;
        byte[] buffer = new byte[100];
        String x;
        
        System.out.println("Servidor Criado ! Esperando players...");
        DatagramSocket ponto = new DatagramSocket(porta);
        DatagramPacket rec = new DatagramPacket(buffer,buffer.length);
        
        //ESCUTA PLAYER 0;
        ponto.receive(rec);
        ipP0 = rec.getAddress();
        portaP0 = rec.getPort();
        System.out.println("Player 0 conectado. Ip: +" + ipP0 + " na porta: " + portaP0);
        
        byte[] p = "0".getBytes();
        DatagramPacket env = new DatagramPacket(p,p.length,ipP0,portaP0);
        ponto.send(env);
        
        //ESCUTA PLAYER 1;
        ponto.receive(rec);
        ipP1 = rec.getAddress();
        portaP1 = rec.getPort();
        System.out.println("Player 1 conectado. Ip: +" + ipP1 + " na porta: " + portaP1);
        
        p = "1".getBytes();
        env = new DatagramPacket(p,p.length,ipP1,portaP1);
        ponto.send(env);
        
        
        int contador = 0;
        int ganho = 0;
        
        Matriz mat = new Matriz();
        
        while(ganho==0 && contador<9){            
            
            // GERA A STRING PARA MANDAR PARA O PLAYER 0
            
            byte[] aux = mat.gerarString().getBytes();
            env = new DatagramPacket(aux,aux.length,ipP0,portaP0);
            ponto.send(env);
            
            if(mat.temVencedor() != -1) break;
            if(mat.acabou() == 1) break;
            
            // ESPERA O PLAYER 0 MARCAR E MANDAR A NOVA MATRIZ:
				
				do{

					aux = new byte[0];
            	aux = new byte[100];
            	rec = new DatagramPacket(buffer,buffer.length);
            	ponto.receive(rec);
            	x = new String(rec.getData());
            	System.out.println("Recebeu a string: "+ x); 
					auxIp = rec.getAddress();
        			auxPorta= rec.getPort();
					

				}while(auxIp!=ipP0 && auxPorta!= portaP0);

				aux = x.getBytes();
	
            
            
            //ATUALIZOU A MATRIZ E ENVIOU PARA O PLAYER 1;
            mat.updateMatriz(x);
            env = new DatagramPacket(aux,aux.length,ipP1,portaP1);
            ponto.send(env);
            
            if(mat.temVencedor() != -1) break;
            if(mat.acabou() == 1) break;
            
            
            // ESPERA O PLAYER 1 MARCAR E MANDAR A NOVA MATRIZ:
				
				do{

	         	aux = new byte[0];
   	      	aux = new byte[100];
  	         	rec = new DatagramPacket(buffer,buffer.length);
  	         	ponto.receive(rec);
  	         	x = new String(rec.getData());
    		   	System.out.println("Recebeu a string: "+ x); 
					auxIp = rec.getAddress();
        			auxPorta= rec.getPort();
 
				}while(auxIp!=ipP1 && auxPorta!= portaP1); 		     
				
            
				aux = x.getBytes();
            mat.updateMatriz(x);
            
            
            
            
            
            contador++;
        }
        
        
        
       
        
        
        
        
        
        
        
    }
    
}
