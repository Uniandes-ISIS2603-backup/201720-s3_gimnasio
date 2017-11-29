package co.edu.uniandes.baco.gimnasio.dtos;

import java.util.Date;
import java.util.List;

public class Graphic {
    private List<Double> valores;
    private List<Date> ejerx;

    public Graphic(){
        //otravez
    }
    
    public Graphic(List<Double> valores, List<Date> ejerx) {
        this.valores = valores;
        this.ejerx = ejerx;
    }
    
    public List<Double> getValores() {
        return valores;
    }

    public void setValores(List<Double> valores) {
        this.valores = valores;
    }

    public List<Date> getEjerx() {
        return ejerx;
    }

    public void setEjerx(List<Date> ejerx) {
        this.ejerx = ejerx;
    }
}
