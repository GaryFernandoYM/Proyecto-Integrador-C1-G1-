package pe.edu.upeu.modelo;

public class ReservaDetalleTO {
    
    public String idDetRent;
    public String idReserva;
    public String idProd;
    public int cantdad;
    public double Precio;
    public String DNI;

    
    public ReservaDetalleTO() {
    }
    
    public ReservaDetalleTO(String idDetRent, String idReserva, String idProd, int cantdad, double precio, String dNI) {
        this.idDetRent = idDetRent;
        this.idReserva = idReserva;
        this.idProd = idProd;
        this.cantdad = cantdad;
        Precio = precio;
        DNI = dNI;
    }

    public String getIdDetRent() {
        return idDetRent;
    }
    public void setIdDetRent(String idDetRent) {
        this.idDetRent = idDetRent;
    }
    public String getIdReserva() {
        return idReserva;
    }
    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }
    public String getIdProd() {
        return idProd;
    }
    public void setIdProd(String idProd) {
        this.idProd = idProd;
    }
    public int getCantdad() {
        return cantdad;
    }
    public void setCantdad(int cantdad) {
        this.cantdad = cantdad;
    }
    public double getPrecio() {
        return Precio;
    }
    public void setPrecio(double precio) {
        Precio = precio;
    }
    public String getDNI() {
        return DNI;
    }
    public void setDNI(String dNI) {
        DNI = dNI;
    }
    
    
}
