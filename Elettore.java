public class Elettore extends Thread{
    private SeggioElettorale seggioElettorale;
    public int idElettore;
    public int tipoElettore;
    //tipoElettore Rules:
    //  2 = Dirigente
    //  1 = Dipendente
    //  0 = Collaboratore

    //Costruttore
    public Elettore(SeggioElettorale seggio, int id, int tipo){
        this.seggioElettorale = seggio;
        this.idElettore = id;
        this.tipoElettore = tipo;
    }

    //Metodo toStringTipo
    public String toStringTipoElettore(int tipoElettore){
        String tipo = "";
        if (tipoElettore == 0) {
            tipo = "Collaboratore";
        } else if (tipoElettore == 1) {
            tipo = "Dipendente";
        } else if (tipoElettore == 2) {
            tipo = "Dirigente";
        }

        return tipo;
    }
    
    //Metodo Run
    public void run(){
        try {
            Thread.sleep( (int)(Math.random()*2000) ); //Per Simulare L'Arrivo Degli Elettori
        } catch (Exception e) {
            //TODO: handle exception
        }
        seggioElettorale.mettiInCodaIdentificazione(this);
        try {
            Thread.sleep( (int)(Math.random()*2000) ); //Per Simulare L'Identificazione
        } catch (Exception e) {
            //TODO: handle exception
        }
        seggioElettorale.mettiInCodaVotazione();
    }
}