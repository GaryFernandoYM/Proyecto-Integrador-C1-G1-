package pe.edu.upeu.modelo;

public class ReservaTO {
    public String idReserva;
    public String dni;
    public String fecha;
    public String origen;
    public double subtotal;
    public double descuento;
    public double totalimporte;

    
    public ReservaTO() {
    }

    
    public ReservaTO(String idReserva, String dni, String fecha, String origen, double subtotal, double descuento,
            double totalimporte) {
        this.idReserva = idReserva;
        this.dni = dni;
        this.fecha = fecha;
        this.origen = origen;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.totalimporte = totalimporte;
    }


    public String getIdReserva() {
        return idReserva;
    }
    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getOrigen() {
        return origen;
    }
    public void setOrigen(String origen) {
        this.origen = origen;
    }
    public double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    public double getDescuento() {
        return descuento;
    }
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    public double getTotalimporte() {
        return totalimporte;
    }
    public void setTotalimporte(double totalimporte) {
        this.totalimporte = totalimporte;
    }


    
    
}
