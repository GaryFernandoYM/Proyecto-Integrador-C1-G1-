package pe.edu.upeu.modelo;

public class ProductosTO {
    public String idProductos;
    public String nhabitaciones;
    public String Estado;
    public String tipo;
    public double precio;
    public int camas;
    public String unidad;



    
    public ProductosTO() {
    }
    
    public ProductosTO(String idProductos, String nhabitaciones, String estado, String tipo, double precio, int camas,
            String unidad) {
        this.idProductos = idProductos;
        this.nhabitaciones = nhabitaciones;
        Estado = estado;
        this.tipo = tipo;
        this.precio = precio;
        this.camas = camas;
        this.unidad = unidad;
    }

    public String getIdProductos() {
        return idProductos;
    }
    public void setIdProductos(String idProductos) {
        this.idProductos = idProductos;
    }
    public String getNhabitaciones() {
        return nhabitaciones;
    }
    public void setNhabitaciones(String nhabitaciones) {
        this.nhabitaciones = nhabitaciones;
    }
    public String getEstado() {
        return Estado;
    }
    public void setEstado(String estado) {
        Estado = estado;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getCamas() {
        return camas;
    }
    public void setCamas(int camas) {
        this.camas = camas;
    }
    public String getUnidad() {
        return unidad;
    }
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    

}