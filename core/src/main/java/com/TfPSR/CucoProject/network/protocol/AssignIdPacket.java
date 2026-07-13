package com.TfPSR.CucoProject.network.protocol;

public class AssignIdPacket {
    //Paquete de respuesta al cliente luego de un ConnectPacket
    public static final  int SIZE = 2; //Tiene 2 espacios para que ademas del id lleve la etiqueta del paquete
    public byte clientId;
}
