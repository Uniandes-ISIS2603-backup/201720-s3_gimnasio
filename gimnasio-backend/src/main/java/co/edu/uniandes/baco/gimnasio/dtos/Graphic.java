package co.edu.uniandes.baco.gimnasio.dtos;

import java.util.List;

public class Graphic {
    private List<Double> valores;
    private List<String> ejerx;

    public Graphic(){
        //otravez
    }
    
    public Graphic(List<Double> valores, List<String> ejerx) {
        this.valores = valores;
        this.ejerx = ejerx;
    }
    
    public List<Double> getValores() {
        return valores;
    }

    public void setValores(List<Double> valores) {
        this.valores = valores;
    }

    public List<String> getEjerx() {
        return ejerx;
    }

    public void setEjerx(List<String> ejerx) {
        this.ejerx = ejerx;
    }
}
