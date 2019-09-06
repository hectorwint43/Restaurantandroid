package com.example.restaurant2.Pedido;

public class Pedido {

    private String idpersona;
    private String nombre;
    private String apellidopaterno;
    private String apellidomaterno;
    private String idpedido;
    private String platillonombre;
    private String precio;

    public Pedido() { }

    public Pedido(String idpersona, String nombre, String apellidopaterno,String apellidomaterno,String idpedido, String platillonombre, String precio) {
        this.idpersona = idpersona;
        this.nombre = nombre;
        this.apellidopaterno = apellidopaterno;
        this.apellidomaterno = apellidomaterno;
        this.idpedido = idpedido;
        this.platillonombre = platillonombre;
        this.precio = precio;
    }

    public String getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(String idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidopaterno() {
        return apellidopaterno;
    }

    public void setApellidopaterno(String apellidopaterno) {
        this.apellidopaterno = apellidopaterno;
    }

    public String getApellidomaterno() {
        return apellidomaterno;
    }

    public void setApellidomaterno(String apellidomaterno) {
        this.apellidomaterno = apellidomaterno;
    }

    public String getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(String idpedido) {
        this.idpedido = idpedido;
    }

    public String getPlatillonombre() {
        return platillonombre;
    }

    public void setPlatillonombre(String platillonombre) {
        this.platillonombre = platillonombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

}
