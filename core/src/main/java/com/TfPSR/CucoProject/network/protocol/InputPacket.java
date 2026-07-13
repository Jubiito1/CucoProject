package com.TfPSR.CucoProject.network.protocol;

public class InputPacket {
    //Aqui armamos el paquete que enviaremos por red, sera solamente las teclas presionadas y la posicion del mouse

    public static final int SIZE = 12;

    private double positionMouseX;
    private double positionMouseY;
    private boolean leftClick;
    private boolean rightClick;
    private boolean keyD;
    private boolean keyA;

}
