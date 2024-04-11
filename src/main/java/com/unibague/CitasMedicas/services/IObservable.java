package com.unibague.CitasMedicas.services;


public interface IObservable {

    void agregarObservador(IObservador observador);
    void eliminarObservador(IObservador observador);
    public void notificar();
}
