# ThreadSeggioElettorale

Presso una grande azienda sono indette le elezioni per la formazione delle rappresentanze sindacali. Gli elettori, appartenenti alle tre categorie **dirigenti**, **dipendenti**, **collaboratori** possono esprimere il proprio voto recandosi in uno degli **N seggi** disponibili. Ciascuno seggio è presidiato da una commissione che avrà il ruolo di identificare chi intende votare assicurandosi che ne abbia effettivamente il diritto.

I votanti si recano ad un seggio, scelto casualmente, e:
* Se la commissione è libera procedono alla identificazione
* Se il numero di utenti in attesa di identificazione c minore di **MAX** si mettono in coda
* Altrimenti vanno via dal seggio corrente e ne scelgono un altro

Al termine dell’identificazione, l’avente diritto verifica che una delle **M cabine elettorali** presenti all’interno del seggio sia libera. In caso positivo vota e va via, altrimenti resta in attesa. Le categorie di votanti hanno priorità crescenti secondo il ruolo ricoperto. Pertanto, i *dirigenti* potranno accedere alla cabina elettorale prima dei *dipendenti*, e questi ultimi prima dei *collaboratori*. 

Si modelli lo scenario descritto mediante thread in linguaggio Java usando il costrutto **monitor**. Si descriva la sincronizzazione tra thread, discutendo anche se la soluzione proposta può presentare rinvio indefinito o deadlock. In tal caso, si propongano delle soluzioni implemcntative per evitare i due fenomeni.
