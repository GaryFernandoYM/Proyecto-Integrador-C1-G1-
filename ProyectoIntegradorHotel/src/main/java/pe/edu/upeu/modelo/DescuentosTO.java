package pe.edu.upeu.modelo;


public class DescuentosTO {
    public double montoVenta;
    public double montodescuento;

    public DescuentosTO() {
    }

    
    public DescuentosTO(double montoVenta, double montodescuento) {
        this.montoVenta = montoVenta;
        this.montodescuento = montodescuento;
    }


    public double getMontoVenta() {
        return montoVenta;
    }
    public void setMontoVenta(double montoVenta) {
        this.montoVenta = montoVenta;
    }
    public double getMontodescuento() {
        return montodescuento;
    }
    public void setMontodescuento(double montodescuento) {
        this.montodescuento = montodescuento;
    }

      
}
