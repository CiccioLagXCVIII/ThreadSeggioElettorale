public class Main{
    public static void main(String[] args){
        int numeroElettori = 20;
        int numeroCabine = 4;
        int tipoElettore;

        SeggioElettorale seggioElettorale = new SeggioElettorale();
        Cabina cabine[] = new Cabina[numeroCabine];
        Elettore elettori[] = new Elettore[numeroElettori];

        for(int i=0; i<numeroCabine; i++){
            cabine[i] = new Cabina(seggioElettorale, i);
            cabine[i].start();
        }

        for(int j=0; j<numeroElettori; j++){
            tipoElettore = ( (int)(Math.random()*2000)%3 );
            elettori[j] = new Elettore(seggioElettorale, j, tipoElettore);
            elettori[j].start();
        }
    }
}
