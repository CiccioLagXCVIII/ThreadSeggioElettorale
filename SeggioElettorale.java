import java.util.LinkedList;
public class SeggioElettorale{
    private int maxPostiCodaIdentificazione = 10;
    private LinkedList<Elettore> codaIdentificazione = new LinkedList<Elettore>();
    private LinkedList<Elettore> codaVotazioneDirigenti = new LinkedList<Elettore>();
    private LinkedList<Elettore> codaVotazioneDipendenti = new LinkedList<Elettore>();
    private LinkedList<Elettore> codaVotazioneCollaboratori = new LinkedList<Elettore>();

    public Elettore elettoreTmp;
    public Elettore elettoreMaxPriorita;
    public Elettore elettoreMediaPriorita;
    public Elettore elettoreBassaPriorita;
    public int counterPriorita;

    public synchronized void mettiInCodaIdentificazione(Elettore elettore){
        System.out.println("Entra L'Elettore " + elettore.idElettore);

        if(codaIdentificazione.size() == maxPostiCodaIdentificazione){
            System.out.println("L'Elettore " + elettore.idElettore + " Cambia Seggio Perchè Non Trova Posto!");
        } else {
            codaIdentificazione.add(elettore);
            System.out.println("L'Elettore " + elettore.idElettore + " É In Coda Per L'Identificazione...");
            System.out.println("Elettori In Coda Per L'Identificazione: " + codaIdentificazione.size());
            notifyAll();
        }
    }

    public synchronized void mettiInCodaVotazione(){
        while(codaIdentificazione.size() == 0){
            try{
                System.out.println("Nessun Elettore Identificato Che Può Votare Attendo...");
                wait();
            } catch (Exception e){
                //TODO: Handle Exception
            }
        }

        if(codaIdentificazione.size() > 0){
            elettoreTmp = codaIdentificazione.removeFirst();
            if(elettoreTmp.tipoElettore == 0){
                System.out.println("L'Elettore " + elettoreTmp.idElettore + " (" + elettoreTmp.toStringTipoElettore(elettoreTmp.tipoElettore) + ") Aggiunto Alla codaVotazioneCollaboratori");
                codaVotazioneCollaboratori.add(elettoreTmp);
                notifyAll();
            } else if(elettoreTmp.tipoElettore == 1){
                System.out.println("L'Elettore " + elettoreTmp.idElettore + " (" + elettoreTmp.toStringTipoElettore(elettoreTmp.tipoElettore) + ") Aggiunto Alla codaVotazioneDipendenti");
                codaVotazioneDipendenti.add(elettoreTmp);
                notifyAll();
            } else if(elettoreTmp.tipoElettore == 2){
                System.out.println("L'Elettore " + elettoreTmp.idElettore + " (" + elettoreTmp.toStringTipoElettore(elettoreTmp.tipoElettore) + ") Aggiunto Alla codaVotazioneDirigenti");
                codaVotazioneDirigenti.add(elettoreTmp);
                notifyAll();
            }
            //System.out.println("Cabina #" + cabina.idCabina +": L'Elettore " + elettoreTmp.idElettore + " (" + elettoreTmp.toStringTipoElettore(elettoreTmp.tipoElettore) + ") " +" Aggiunto Alla Coda " + elettoreTmp.toStringTipoElettore(elettoreTmp.idElettore));      
        }
    }

    public synchronized Elettore faiVotare(Cabina cabina){
        while(codaVotazioneDirigenti.size() == 0 && codaVotazioneDipendenti.size() == 0 && codaVotazioneCollaboratori.size() == 0){
            try {
                System.out.println("Cabina #" + cabina.idCabina + ": Nessun Elettore In Attesa Di Votare...");
                wait();
            } catch (Exception e) {
                //TODO: handle exception
            }
        } 

        if(codaVotazioneDirigenti.size() > 0){
            counterPriorita++;
            elettoreMaxPriorita = codaVotazioneDirigenti.removeFirst();
            try{
                Thread.sleep(2500); //Simula La Durata Della Votazione
            } catch (Exception e){
                //TODO: Handle Exception
            }
            System.out.println("Cabina #" + cabina.idCabina + ": Il Cliente " + elettoreMaxPriorita.idElettore + " Ha Votato Ed Esce!" );
            return elettoreMaxPriorita;
        } else if(codaVotazioneDirigenti.size() == 0 || counterPriorita%3 == 0){
            counterPriorita++;
            elettoreMediaPriorita = codaVotazioneDipendenti.removeFirst();
            try{
                Thread.sleep(2500); //Simula La Durata Della Votazione
            } catch (Exception e){
                //TODO: Handle Exception
            }
            System.out.println("Cabina #" + cabina.idCabina + ": Il Cliente " + elettoreMediaPriorita.idElettore + " Ha Votato Ed Esce!" );
            return elettoreMediaPriorita;
        } else if(codaVotazioneDipendenti.size() == 0 || counterPriorita%6 == 0){
            counterPriorita++;
            elettoreBassaPriorita = codaVotazioneCollaboratori.removeFirst();
            try{
                Thread.sleep(2500); //Simula La Durata Della Votazione
            } catch (Exception e){
                //TODO: Handle Exception
            }
            System.out.println("Cabina #" + cabina.idCabina + ": Il Cliente " + elettoreBassaPriorita.idElettore + " Ha Votato Ed Esce!" );
            return elettoreBassaPriorita;
        }
        notifyAll();
        return null;
    }
}



