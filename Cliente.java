
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException{
        
        Scanner scan = new Scanner(System.in);   
        
        int venc = -1;
        int i;
        int a =0;
        
        int portaServer = 12345;         
        InetAddress ipServer = InetAddress.getByName("localhost");    
        
       
        byte[] buffer = new byte[100];
              
        DatagramSocket ponto = new DatagramSocket();
        
        DatagramPacket env = new DatagramPacket(buffer,buffer.length,ipServer,portaServer);
        ponto.send(env);
        
        DatagramPacket rec = new DatagramPacket(buffer,buffer.length);
        ponto.receive(rec);
        
        String aux = new String(rec.getData());
        int player = (int) Integer.parseInt(aux.trim());
        System.out.println("Voce é o player: "+player);
        
        Matriz mat = new Matriz();
        
        // ESPERAR VEZ  
        
        while(venc == -1){
            
            //RECEBEU A STRING 
            
            if(mat.acabou()==1) break;
				if(mat.temVencedor() != -1) break;
            
            System.out.println("Aguarde o outro jogador...");
            buffer = new byte[0];
            buffer = new byte[100];
            rec = new DatagramPacket(buffer,buffer.length);
            ponto.receive(rec);        
            String s = new String(rec.getData());
            System.out.println("Recebeu: "+s);  
            
            // ATUALIZOU A MATRIZ E PRINTOU
            mat.updateMatriz(s);
            mat.printarMatriz();
            
            if(mat.temVencedor() != -1) break;
            if(mat.acabou() == 1) break;
            
           
        
            
            // MANDA MARCAR
            i = 0;
            a = 0;
            while(i==0){
                System.out.print("Marcar: ");
                a = scan.nextInt();               
                i = mat.podeMarcar(a);
            }
            
            // MARCOU E PRINTOU            
            mat.marcar(a, player);                
            mat.printarMatriz();      
        
            
            //ENVIA A NOVA MATRIZ PARA O SERVIDOR
            aux = mat.gerarString(); 
            buffer = new byte[0];
            buffer = new byte[100];
            buffer = aux.getBytes();
            env = new DatagramPacket(buffer,buffer.length,ipServer,portaServer);
            for(int ba = 0; ba<3;ba++)ponto.send(env); 
            
            venc = mat.temVencedor();         
            
        }
        
        venc = mat.temVencedor();
        if(venc==player) System.out.println("Voce ganhou !");             
        else if(venc==-1) System.out.println("Empate !");
        else System.out.println("Voce perdeu");
       
        
        
        
        
        
        
        
        
        
        
            
        
        
        
        
        
        
    }
        
    
    
    
}
