public class Cabina extends Thread{
    private SeggioElettorale seggioElettorale;
    public int idCabina;

    //Costruttore
    public Cabina(SeggioElettorale seggio, int id){
        this.seggioElettorale = seggio;
        this.idCabina = id;
    }

    //Metodo Run
    public void run(){
        try{
            seggioElettorale.faiVotare(this);
        } catch (Exception e){
            //TODO: Handle Exception
        }
    }
}