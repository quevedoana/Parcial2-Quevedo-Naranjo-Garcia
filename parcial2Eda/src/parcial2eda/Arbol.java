package parcial2eda;

/**
 *
 * @author Anitabonita
 */
public class Arbol {
    
    private Nodo raiz;
    
    public Arbol(){
        raiz = null;
    }
    
    /**
     * Metodo para validar que la cantidad de participantes sea la correcta para que el arbol sea simetrico
     * @param participantes Numero de participantes 
     * @return True si la cantidad de participantes es correcta, false si no lo es
     */
    public boolean cant_participantes(int participantes){
        if(participantes<=3){
            return false;
        }
        while(participantes > 1 && participantes%2 == 0) {
            participantes = participantes / 2;
        }
        boolean res = (participantes == 1) ? true:false;
        return res;
    }
    
    /**
     * Metodo para calcular la cantidad de etapas del torneo en base a la cantidad de participantes
     * @param participantes
     * @return Cantidad de etapas
     */
    public int cant_etapas(int participantes){
        int cont = 0;
        while(participantes > 1 && participantes%2 == 0) {
            participantes = participantes/2;
            cont++;
        }
        return cont;
    }
    
    /**
     * Metodo para construir el arbol (torneo) y cada nodo (partido) de este
     * @param etapas Cantidad de etapas que va a tener el torneo
     * @return Partido creado
     */
    public Nodo crear_torneo(int etapas){
        if(etapas == 0) return null;
        Nodo partido = new Nodo();
        
        partido.setIzq(crear_torneo(etapas-1));
        partido.setDer(crear_torneo(etapas-1));
        
        return partido;
    }
    
}
