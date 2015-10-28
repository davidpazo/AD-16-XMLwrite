package XML;


import java.io.Serializable;

/**
 *
 * @author oracle
 */
public class Products implements Serializable {
    String codigo;
    String descripcion;
    int prezo;

    public Products() {
    }

    public Products(String codigo, String descripcion, int prezo) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.prezo = prezo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrezo() {
        return prezo;
    }

    public void setPrezo(int prezo) {
        this.prezo = prezo;
    }
    
}
