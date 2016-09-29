public class Matriz {
    
    private int[] mat = new int[9];   
    
    // Inicia a "Matriz" com -1
    public Matriz(){
        int i;
        for(i=0;i<9;i++) mat[i] = 3;
        System.out.println("Tabuleiro criado !");
    } 
    
    //Se o movimento for valido retorna 1, senao retorna 0
    public int podeMarcar(int i){ 
        
        if(i>=0 && i<=8){
            if(mat[i]== 3) return 1;
            else return 0; 
            
        }else return 0;
              
    }
    
    //Marca a "Matriz"
    public void marcar(int i, int jogador){       
        mat[i] = jogador;       
    } 
    
    //Gera uma String da Matriz
    public String gerarString(){
        String aux="";
        int i;
        for(i=0;i<9;i++){
            aux = aux + Integer.toString(mat[i]);
        }
        
        System.out.println("Gerou a String: "+aux);        
        return aux;       
    }
    
    public void updateMatriz(String aux){
        
        int i;
        for(i=0; i<9; i++){
            String s = "" + aux.charAt(i);
            mat[i] = Integer.parseInt(s);          
        }       
    }
    
    //Retorna 1 se acabou, 0 se ainda existe lugar para marcar !
    public int acabou(){
        int i;
        int cont=0;
        for(i=0;i<9;i++){
            if(mat[i]!=3){
                cont++;
            }
        }
        
        if(cont==9) return 1;
        else return 0;
    }
    
    private void printar(int aux){          
        if (aux==0) System.out.print(" X |");
        else if(aux==1) System.out.print(" O |");
        else System.out.print("   |");         
    }
    
    public void printarMatriz(){
        System.out.println();
        int i;
        for(i=0;i<3;i++) printar(mat[i]);
        System.out.print("\n");
        System.out.print(" -----------");
        System.out.print("\n");
        for(i=3;i<6;i++) printar(mat[i]);
        System.out.print("\n");
        System.out.print(" -----------");
        System.out.print("\n");
        for(i=6;i<9;i++) printar(mat[i]);
        System.out.print("\n");
        System.out.print(" -----------");
        System.out.print("\n");      
    }
    
    // Retorna 1 se o player 1 venceu, retorna 0 se o player 0 venceu, e retorna -1 se deu empate
    public int temVencedor(){
        
       if(mat[0]==1 && mat[1]==1 && mat[2]==1) return 1;
       else if(mat[0]==0 && mat[1]==0 && mat[2]==0) return 0;
       else if(mat[3]==1 && mat[4]==1 && mat[5]==1) return 1;
       else if(mat[3]==0 && mat[4]==0 && mat[5]==0) return 0;
       else if(mat[6]==1 && mat[7]==1 && mat[8]==1) return 1;
       else if(mat[6]==0 && mat[7]==0 && mat[8]==0) return 0;
       
       else if(mat[0]==1 && mat[3]==1 && mat[6]==1) return 1;
       else if(mat[0]==0 && mat[3]==0 && mat[6]==0) return 0;
       else if(mat[1]==1 && mat[4]==1 && mat[7]==1) return 1;
       else if(mat[1]==0 && mat[4]==0 && mat[7]==0) return 0;
       else if(mat[2]==1 && mat[5]==1 && mat[8]==1) return 1;
       else if(mat[2]==0 && mat[5]==0 && mat[8]==0) return 0;
       
       else if(mat[0]==1 && mat[4]==1 && mat[8]==1) return 1;
       else if(mat[0]==0 && mat[4]==0 && mat[8]==0) return 0;      
       else if(mat[2]==1 && mat[4]==1 && mat[6]==1) return 1;
       else if(mat[2]==0 && mat[4]==0 && mat[6]==0) return 0;
       else return -1;       
    }
    
    
    
    
    
    
    
}
